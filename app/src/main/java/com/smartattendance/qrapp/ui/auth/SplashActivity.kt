package com.smartattendance.qrapp.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.smartattendance.qrapp.data.repository.AuthRepository
import com.smartattendance.qrapp.databinding.ActivitySplashBinding
import com.smartattendance.qrapp.ui.admin.AdminDashboardActivity
import com.smartattendance.qrapp.ui.student.StudentDashboardActivity
import com.smartattendance.qrapp.ui.teacher.TeacherDashboardActivity
import com.smartattendance.qrapp.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val authRepository = AuthRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            delay(2000)
            checkAuth()
        }
    }

    private suspend fun checkAuth() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            when (val result = authRepository.getCurrentUserData()) {
                is Resource.Success -> {
                    val user = result.data!!
                    val dest: Class<*> = when (user.role) {
                        "admin"   -> AdminDashboardActivity::class.java
                        "teacher" -> TeacherDashboardActivity::class.java
                        "student" -> StudentDashboardActivity::class.java
                        else      -> PendingActivity::class.java
                    }
                    startActivity(Intent(this@SplashActivity, dest))
                    finish()
                }
                else -> {
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                    finish()
                }
            }
        } else {
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }
    }
}
