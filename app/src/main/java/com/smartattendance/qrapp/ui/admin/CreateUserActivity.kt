package com.smartattendance.qrapp.ui.admin

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.smartattendance.qrapp.data.repository.AuthRepository
import com.smartattendance.qrapp.databinding.ActivityCreateUserBinding
import com.smartattendance.qrapp.util.Resource
import com.smartattendance.qrapp.util.toast
import kotlinx.coroutines.launch

class CreateUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateUserBinding
    private val authRepository = AuthRepository()
    private var selectedRole = "student"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = "Create User Account"
            setDisplayHomeAsUpEnabled(true)
        }

        setupRoleToggle()
        setupDepartmentDropdown()

        binding.btnCreate.setOnClickListener { createUser() }
    }

    private fun setupRoleToggle() {
        binding.rgRole.setOnCheckedChangeListener { _, checkedId ->
            selectedRole = if (checkedId == com.smartattendance.qrapp.R.id.rbTeacher) "teacher" else "student"
            binding.tilRollNumber.isVisible = selectedRole == "student"
        }
    }

    private fun setupDepartmentDropdown() {
        val departments = arrayOf(
            "Computer Science", "Electronics", "Mechanical",
            "Civil", "Information Technology", "Electrical", "Mathematics", "Physics"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, departments)
        binding.actvDepartment.setAdapter(adapter)
    }

    private fun createUser() {
        val name = binding.etName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val department = binding.actvDepartment.text.toString().trim()
        val rollNumber = binding.etRollNumber.text.toString().trim()

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || department.isEmpty()) {
            toast("Please fill all required fields")
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
        binding.btnCreate.isEnabled = false

        lifecycleScope.launch {
            when (val result = authRepository.adminCreateUser(
                name = name,
                email = email,
                password = password,
                role = selectedRole,
                rollNumber = rollNumber,
                department = department
            )) {
                is Resource.Success -> {
                    binding.progressBar.isVisible = false
                    toast("Account created for $name as $selectedRole")
                    finish()
                }
                is Resource.Error -> {
                    binding.progressBar.isVisible = false
                    binding.btnCreate.isEnabled = true
                    toast(result.message ?: "Failed to create account")
                }
                else -> {}
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
