package com.smartattendance.qrapp.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.smartattendance.qrapp.data.repository.AuthRepository
import com.smartattendance.qrapp.databinding.ActivityLoginBinding
import com.smartattendance.qrapp.ui.admin.AdminDashboardActivity
import com.smartattendance.qrapp.ui.student.StudentDashboardActivity
import com.smartattendance.qrapp.ui.teacher.TeacherDashboardActivity
import com.smartattendance.qrapp.util.Resource
import com.smartattendance.qrapp.util.toast
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val authRepository = AuthRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener { login() }

        // Hide "Don't have an account" — Admin creates all accounts
        binding.tvRegister.isVisible = false
    }

    private fun login() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            toast("Please enter your email and password")
            return
        }

        binding.progressBar.isVisible = true
        binding.btnLogin.isEnabled = false

        lifecycleScope.launch {
            when (val result = authRepository.login(email, password)) {
                is Resource.Success -> {
                    binding.progressBar.isVisible = false
                    val user = result.data!!
                    val dest: Class<*> = when (user.role) {
                        "admin"   -> AdminDashboardActivity::class.java
                        "teacher" -> TeacherDashboardActivity::class.java
                        "student" -> StudentDashboardActivity::class.java
                        else      -> PendingActivity::class.java
                    }
                    startActivity(Intent(this@LoginActivity, dest))
                    finishAffinity()
                }
                is Resource.Error -> {
                    binding.progressBar.isVisible = false
                    binding.btnLogin.isEnabled = true
                    toast(result.message ?: "Login failed")
                }
                else -> {}
            }
        }
    }
}
