package com.smartattendance.qrapp.ui.teacher

import android.graphics.Bitmap
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.smartattendance.qrapp.data.model.AttendanceSession
import com.smartattendance.qrapp.data.model.QRPayload
import com.smartattendance.qrapp.data.repository.AttendanceRepository
import com.smartattendance.qrapp.data.repository.AuthRepository
import com.smartattendance.qrapp.databinding.ActivityGenerateQrBinding
import com.smartattendance.qrapp.util.QRUtils
import com.smartattendance.qrapp.util.Resource
import com.smartattendance.qrapp.util.generateId
import com.smartattendance.qrapp.util.formatSecondsToMMSS
import com.smartattendance.qrapp.util.toast
import kotlinx.coroutines.launch

class GenerateQRActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGenerateQrBinding
    private val authRepository = AuthRepository()
    private val attendanceRepository = AttendanceRepository()
    private var countDownTimer: CountDownTimer? = null
    private var currentSessionId: String? = null

    companion object {
        private val DURATION_OPTIONS = listOf(
            "2 minutes" to 2 * 60 * 1000L,
            "5 minutes" to 5 * 60 * 1000L,
            "10 minutes" to 10 * 60 * 1000L,
            "15 minutes" to 15 * 60 * 1000L,
            "30 minutes" to 30 * 60 * 1000L
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenerateQrBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = "Generate Attendance QR"
            setDisplayHomeAsUpEnabled(true)
        }

        setupDurationDropdown()

        binding.btnGenerate.setOnClickListener { generateQR() }
        binding.btnDeactivate.setOnClickListener { deactivateSession() }
    }

    private fun setupDurationDropdown() {
        val items = DURATION_OPTIONS.map { it.first }
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, items)
        binding.actvDuration.setAdapter(adapter)
        binding.actvDuration.setText(items[1], false) // Default: 5 minutes
    }

    private fun generateQR() {
        val className = binding.etClassName.text.toString().trim()
        val subject = binding.etSubject.text.toString().trim()
        val classId = binding.etClassId.text.toString().trim()
        val durationText = binding.actvDuration.text.toString()

        if (className.isEmpty() || subject.isEmpty() || classId.isEmpty()) {
            toast("Please fill all fields")
            return
        }

        val durationMs = DURATION_OPTIONS.find { it.first == durationText }?.second
            ?: (5 * 60 * 1000L)

        lifecycleScope.launch {
            val userResult = authRepository.getCurrentUserData()
            if (userResult !is Resource.Success) {
                toast("Failed to get user data")
                return@launch
            }
            val user = userResult.data!!
            val now = System.currentTimeMillis()
            val sessionId = generateId()
            currentSessionId = sessionId

            val payload = QRPayload(
                sessionId = sessionId,
                classId = classId,
                className = className,
                subject = subject,
                teacherUid = user.uid,
                createdAt = now,
                expiresAt = now + durationMs
            )

            val session = AttendanceSession(
                sessionId = sessionId,
                classId = classId,
                className = className,
                subject = subject,
                teacherUid = user.uid,
                teacherName = user.name,
                qrPayload = QRUtils.encodePayload(payload),
                createdAt = now,
                expiresAt = now + durationMs,
                validDurationMs = durationMs,
                isActive = true,
                department = user.department
            )

            when (attendanceRepository.createSession(session)) {
                is Resource.Success -> {
                    val encoded = QRUtils.encodePayload(payload)
                    val bitmap = QRUtils.generateQRBitmap(encoded)
                    if (bitmap != null) {
                        showQRCode(bitmap, durationMs)
                    } else {
                        toast("Failed to generate QR image")
                    }
                }
                is Resource.Error -> toast("Failed to save session to Firestore")
                else -> {}
            }
        }
    }

    private fun showQRCode(bitmap: Bitmap, durationMs: Long) {
        binding.qrSection.isVisible = true
        binding.ivQrCode.setImageBitmap(bitmap)
        binding.btnDeactivate.isVisible = true

        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer(durationMs, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000L
                binding.tvCountdown.text = "Expires in: ${seconds.formatSecondsToMMSS()}"
            }

            override fun onFinish() {
                binding.tvCountdown.text = "QR Code Expired"
                binding.ivQrCode.alpha = 0.3f
                toast("QR code has expired")
            }
        }.start()
    }

    private fun deactivateSession() {
        val sessionId = currentSessionId ?: return
        lifecycleScope.launch {
            attendanceRepository.deactivateSession(sessionId)
            countDownTimer?.cancel()
            binding.tvCountdown.text = "Session Deactivated"
            binding.ivQrCode.alpha = 0.3f
            toast("Session deactivated successfully")
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }
}
