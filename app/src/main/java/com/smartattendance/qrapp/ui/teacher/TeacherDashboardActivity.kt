package com.smartattendance.qrapp.ui.teacher

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.smartattendance.qrapp.data.repository.AuthRepository
import com.smartattendance.qrapp.data.repository.AttendanceRepository
import com.smartattendance.qrapp.databinding.ActivityTeacherDashboardBinding
import com.smartattendance.qrapp.ui.attendance.AttendanceHistoryActivity
import com.smartattendance.qrapp.ui.auth.LoginActivity
import com.smartattendance.qrapp.util.Resource
import com.smartattendance.qrapp.util.toast
import kotlinx.coroutines.launch

class TeacherDashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTeacherDashboardBinding
    private val authRepository = AuthRepository()
    private val attendanceRepository = AttendanceRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeacherDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadUserData()
        setupClickListeners()
    }

    override fun onResume() {
        super.onResume()
        loadSessionStats()
    }

    private fun loadUserData() {
        lifecycleScope.launch {
            when (val result = authRepository.getCurrentUserData()) {
                is Resource.Success -> {
                    val user = result.data!!
                    binding.tvWelcome.text = "Welcome, ${user.name}"
                    binding.tvDepartment.text = user.department
                    binding.tvRole.text = "Teacher"
                }
                is Resource.Error -> toast(result.message ?: "Failed to load profile")
                else -> {}
            }
        }
    }

    private fun loadSessionStats() {
        lifecycleScope.launch {
            val uid = authRepository.currentUser?.uid ?: return@launch
            when (val result = attendanceRepository.getSessionsByTeacher(uid)) {
                is Resource.Success -> {
                    val sessions = result.data!!
                    binding.tvTotalSessions.text = sessions.size.toString()
                    val activeSessions = sessions.count { it.isActive && System.currentTimeMillis() < it.expiresAt }
                    binding.tvActiveSessions.text = activeSessions.toString()
                }
                else -> {}
            }
        }
    }

    private fun setupClickListeners() {
        binding.cardGenerateQR.setOnClickListener {
            startActivity(Intent(this, GenerateQRActivity::class.java))
        }
        binding.cardViewHistory.setOnClickListener {
            startActivity(Intent(this, AttendanceHistoryActivity::class.java).apply {
                putExtra("role", "teacher")
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
