package com.smartattendance.qrapp.data.model

data class User(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val role: String = "",          // "admin", "teacher", "student", or "" (pending)
    val rollNumber: String = "",    // for students
    val department: String = "",
    val isApproved: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
