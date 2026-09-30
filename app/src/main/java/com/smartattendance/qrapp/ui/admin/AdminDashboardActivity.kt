package com.smartattendance.qrapp.ui.admin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartattendance.qrapp.data.model.User
import com.smartattendance.qrapp.data.repository.AuthRepository
import com.smartattendance.qrapp.databinding.ActivityAdminDashboardBinding
import com.smartattendance.qrapp.databinding.ItemUserBinding
import com.smartattendance.qrapp.ui.auth.LoginActivity
import com.smartattendance.qrapp.util.Resource
import com.smartattendance.qrapp.util.toast
import kotlinx.coroutines.launch

class AdminDashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminDashboardBinding
    private val authRepository = AuthRepository()
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter(
            onAssignRole = { user -> showAssignRoleDialog(user) },
            onDelete = { user -> confirmDeleteUser(user) }
        )
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        binding.rvUsers.adapter = adapter

        binding.btnCreateUser.setOnClickListener {
            startActivity(Intent(this, CreateUserActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            authRepository.logout()
            startActivity(Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
        }
    }

    override fun onResume() {
        super.onResume()
        loadUsers()
    }

    private fun loadUsers() {
        binding.progressBar.isVisible = true
        lifecycleScope.launch {
            when (val result = authRepository.getAllUsers()) {
                is Resource.Success -> {
                    binding.progressBar.isVisible = false
                    val users = result.data!!.filter { it.role != "admin" }
                    binding.tvUserCount.text = "${users.size} users"
                    if (users.isEmpty()) {
                        binding.tvEmpty.isVisible = true
                        binding.rvUsers.isVisible = false
                    } else {
                        binding.tvEmpty.isVisible = false
                        binding.rvUsers.isVisible = true
                        adapter.submitList(users)
                    }
                }
                is Resource.Error -> {
                    binding.progressBar.isVisible = false
                    toast(result.message ?: "Failed to load users")
                }
                else -> {}
            }
        }
    }

    private fun showAssignRoleDialog(user: User) {
        val roles = arrayOf("student", "teacher")
        var selectedRole = if (user.role in roles) user.role else "student"

        AlertDialog.Builder(this)
            .setTitle("Assign Role to ${user.name}")
            .setSingleChoiceItems(roles, roles.indexOf(selectedRole)) { _, which ->
                selectedRole = roles[which]
            }
            .setPositiveButton("Assign") { _, _ ->
                assignRole(user, selectedRole)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun assignRole(user: User, role: String) {
        lifecycleScope.launch {
            when (authRepository.updateUserRole(user.uid, role, user.department, user.rollNumber)) {
                is Resource.Success -> {
                    toast("${user.name} assigned as $role")
                    loadUsers()
                }
                is Resource.Error -> toast("Failed to assign role")
                else -> {}
            }
        }
    }

    private fun confirmDeleteUser(user: User) {
        AlertDialog.Builder(this)
            .setTitle("Delete User")
            .setMessage("Are you sure you want to delete ${user.name}?")
            .setPositiveButton("Delete") { _, _ -> deleteUser(user) }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun deleteUser(user: User) {
        lifecycleScope.launch {
            when (authRepository.deleteUser(user.uid)) {
                is Resource.Success -> {
                    toast("${user.name} deleted")
                    loadUsers()
                }
                is Resource.Error -> toast("Failed to delete user")
                else -> {}
            }
        }
    }

    // ─── User List Adapter ────────────────────────────────────────────────────

    inner class UserAdapter(
        private val onAssignRole: (User) -> Unit,
        private val onDelete: (User) -> Unit
    ) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

        private val users = mutableListOf<User>()

        fun submitList(list: List<User>) {
            users.clear()
            users.addAll(list)
            notifyDataSetChanged()
        }

        inner class ViewHolder(val b: ItemUserBinding) : RecyclerView.ViewHolder(b.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val b = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(b)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val user = users[position]
            with(holder.b) {
                tvUserName.text = user.name
                tvUserEmail.text = user.email
                tvUserDept.text = if (user.department.isNotEmpty()) user.department else "No department"
                tvRoleChip.text = if (user.role.isNotEmpty()) user.role.uppercase() else "PENDING"
                tvRoleChip.setBackgroundResource(
                    when (user.role) {
                        "teacher" -> com.smartattendance.qrapp.R.drawable.bg_role_teacher
                        "student" -> com.smartattendance.qrapp.R.drawable.bg_role_student
                        else      -> com.smartattendance.qrapp.R.drawable.bg_role_pending
                    }
                )
                tvRollNumber.isVisible = user.rollNumber.isNotEmpty()
                tvRollNumber.text = "Roll: ${user.rollNumber}"

                btnAssignRole.setOnClickListener { onAssignRole(user) }
                btnDelete.setOnClickListener { onDelete(user) }
            }
        }

        override fun getItemCount() = users.size
    }
}
