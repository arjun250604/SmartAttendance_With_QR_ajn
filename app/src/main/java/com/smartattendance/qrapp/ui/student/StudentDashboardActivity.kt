package com.smartattendance.qrapp.ui.student

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.smartattendance.qrapp.data.repository.AuthRepository
import com.smartattendance.qrapp.data.repository.AttendanceRepository
import com.smartattendance.qrapp.databinding.ActivityStudentDashboardBinding
import com.smartattendance.qrapp.ui.attendance.AttendanceHistoryActivity
import com.smartattendance.qrapp.ui.auth.LoginActivity
import com.smartattendance.qrapp.util.Resource
import com.smartattendance.qrapp.util.toast
import kotlinx.coroutines.launch

class StudentDashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentDashboardBinding
    private val authRepository = AuthRepository()
    private val attendanceRepository = AttendanceRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadUserData()
        setupClickListeners()
    }

    override fun onResume() {
        super.onResume()
        loadAttendanceStats()
    }

    private fun loadUserData() {
        lifecycleScope.launch {
            when (val result = authRepository.getCurrentUserData()) {
                is Resource.Success -> {
                    val user = result.data!!
                    binding.tvWelcome.text = "Welcome, ${user.name}"
                    binding.tvRollNumber.text = "Roll No: ${user.rollNumber}"
                    binding.tvDepartment.text = user.department
                    binding.tvRole.text = "Student"
                }
                is Resource.Error -> toast(result.message ?: "Failed to load profile")
                else -> {}
            }
        }
    }

    private fun loadAttendanceStats() {
        lifecycleScope.launch {
            val uid = authRepository.currentUser?.uid ?: return@launch
            when (val result = attendanceRepository.getStudentAttendance(uid)) {
                is Resource.Success -> {
                    val records = result.data!!
                    binding.tvTotalClasses.text = records.size.toString()
                    val percentage = if (records.isEmpty()) 0
                    else (records.count { it.status == "present" } * 100 / records.size)
                    binding.tvAttendancePercentage.text = "$percentage%"
                    binding.progressAttendance.progress = percentage
                }
                else -> {}
            }
        }
    }

    private fun setupClickListeners() {
        binding.cardScanQR.setOnClickListener {
            startActivity(Intent(this, ScanQRActivity::class.java))
        }
        binding.cardViewHistory.setOnClickListener {
            startActivity(Intent(this, AttendanceHistoryActivity::class.java).apply {
                putExtra("role", "student")
            })
        }
        binding.btnLogout.setOnClickListener {
            authRepository.logout()
            startActivity(Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
        }
    }
}
