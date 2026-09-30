package com.smartattendance.qrapp.ui.student

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.smartattendance.qrapp.data.model.AttendanceRecord
import com.smartattendance.qrapp.data.repository.AttendanceRepository
import com.smartattendance.qrapp.data.repository.AuthRepository
import com.smartattendance.qrapp.databinding.ActivityScanQrBinding
import com.smartattendance.qrapp.util.QRUtils
import com.smartattendance.qrapp.util.Resource
import com.smartattendance.qrapp.util.toast
import kotlinx.coroutines.launch
import java.util.UUID

class ScanQRActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScanQrBinding
    private val authRepository = AuthRepository()
    private val attendanceRepository = AttendanceRepository()
    private var isProcessing = false

    companion object {
        private const val CAMERA_PERMISSION_CODE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanQrBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = "Scan QR Code"
            setDisplayHomeAsUpEnabled(true)
        }

        checkCameraPermission()
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED) {
            startScanning()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE)
        }
    }

    private fun startScanning() {
        binding.barcodeScanner.decodeContinuous(object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult?) {
                result?.let {
                    if (!isProcessing) {
                        isProcessing = true
                        processQRCode(it.text)
                    }
                }
            }

            override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {}
        })
    }

    private fun processQRCode(rawText: String) {
        val payload = QRUtils.decodePayload(rawText)
        if (payload == null) {
            showResult(false, "Invalid QR Code", "This QR code is not from the Smart Attendance app.")
            return
        }

        if (QRUtils.isExpired(payload)) {
            showResult(false, "QR Code Expired", "This QR code has expired. Please ask your teacher to generate a new one.")
            return
        }

        lifecycleScope.launch {
            // Verify session is still active in Firestore
            when (val sessionResult = attendanceRepository.getSession(payload.sessionId)) {
                is Resource.Success -> {
                    val session = sessionResult.data!!
                    if (!session.isActive || System.currentTimeMillis() > session.expiresAt) {
                        showResult(false, "Session Expired", "The attendance session has ended.")
                        return@launch
                    }

                    // Get student info
                    when (val userResult = authRepository.getCurrentUserData()) {
                        is Resource.Success -> {
                            val student = userResult.data!!
                            val record = AttendanceRecord(
                                recordId = UUID.randomUUID().toString(), // Using standard UUID for ID generation
                                sessionId = payload.sessionId,
                                classId = payload.classId,
                                className = payload.className,
                                subject = payload.subject,
                                studentUid = student.uid,
                                studentName = student.name,
                                rollNumber = student.rollNumber,
                                department = student.department,
                                markedAt = System.currentTimeMillis(),
                                status = "present"
                            )
                            when (val markResult = attendanceRepository.markAttendance(record)) {
                                is Resource.Success -> showResult(
                                    true,
                                    "Attendance Marked!",
                                    "✅ ${payload.className} – ${payload.subject}\nYour attendance has been recorded."
                                )
                                is Resource.Error -> showResult(false, "Already Marked", markResult.message ?: "Error")
                                else -> {}
                            }
                        }
                        is Resource.Error -> showResult(false, "Error", "Failed to get student info")
                        else -> {}
                    }
                }
                is Resource.Error -> showResult(false, "Session Not Found", "Session does not exist in database.")
                else -> {}
            }
        }
    }

    private fun showResult(success: Boolean, title: String, message: String) {
        runOnUiThread {
            binding.barcodeScanner.isVisible = false
            binding.resultSection.isVisible = true
            binding.tvResultTitle.text = title
            binding.tvResultMessage.text = message
            binding.ivResultIcon.setImageResource(
                if (success) android.R.drawable.ic_dialog_info
                else android.R.drawable.ic_dialog_alert
            )
            binding.btnScanAgain.setOnClickListener {
                isProcessing = false
                binding.barcodeScanner.isVisible = true
                binding.resultSection.isVisible = false
                startScanning()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE && grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED) {
            startScanning()
        } else {
            toast("Camera permission is required to scan QR codes")
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        binding.barcodeScanner.resume()
    }

    override fun onPause() {
        super.onPause()
        binding.barcodeScanner.pause()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}