package com.smartattendance.qrapp.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.smartattendance.qrapp.R
import com.smartattendance.qrapp.data.repository.AuthRepository
import com.smartattendance.qrapp.databinding.ActivityRegisterBinding
import com.smartattendance.qrapp.util.Resource
import com.smartattendance.qrapp.util.toast
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val authRepository = AuthRepository()
    private var selectedRole = "student"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRoleSelection()
        setupDepartmentDropdown()

        binding.btnRegister.setOnClickListener { register() }
        binding.tvLogin.setOnClickListener { finish() }
    }

    private fun setupRoleSelection() {
        binding.rgRole.setOnCheckedChangeListener { _, checkedId ->
            selectedRole = if (checkedId == R.id.rbTeacher) "teacher" else "student"
            binding.tilRollNumber.isVisible = selectedRole == "student"
        }
    }

    private fun setupDepartmentDropdown() {
        val departments = arrayOf("Computer Science", "Electronics", "Mechanical", "Civil", "Information Technology", "Electrical")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, departments)
        binding.actvDepartment.setAdapter(adapter)
    }

    private fun register() {
        val name = binding.etName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val confirmPassword = binding.etConfirmPassword.text.toString().trim()
        val department = binding.actvDepartment.text.toString().trim()
        val rollNumber = binding.etRollNumber.text.toString().trim()

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || department.isEmpty()) {
            toast("Please fill all required fields")
            return
        }

        if (password != confirmPassword) {
            toast("Passwords do not match")
            return
        }

        if (password.length < 6) {
            toast("Password must be at least 6 characters")
            return
        }

        if (selectedRole == "student" && rollNumber.isEmpty()) {
            toast("Roll number is required for students")
            return
        }

        binding.progressBar.isVisible = true
        binding.btnRegister.isEnabled = false

        lifecycleScope.launch {
            when (val result = authRepository.register(name, email, password, selectedRole, rollNumber, department)) {
                is Resource.Success -> {
                    binding.progressBar.isVisible = false
                    toast("Registration successful!")
                    startActivity(Intent(this@RegisterActivity, LoginActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    })
                }
                is Resource.Error -> {
                    binding.progressBar.isVisible = false
                    binding.btnRegister.isEnabled = true
                    toast(result.message ?: "Registration failed")
                }
                else -> {}
            }
        }
    }
}
