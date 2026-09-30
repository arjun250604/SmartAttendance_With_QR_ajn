package com.smartattendance.qrapp.data.model

data class AttendanceSession(
    val sessionId: String = "",
    val classId: String = "",
    val className: String = "",
    val subject: String = "",
    val teacherUid: String = "",
    val teacherName: String = "",
    val qrPayload: String = "",           // JSON-encoded payload embedded in QR
    val createdAt: Long = System.currentTimeMillis(),
    val expiresAt: Long = 0L,            // createdAt + validDurationMs
    val validDurationMs: Long = 5 * 60 * 1000L,  // 5 minutes default
    val isActive: Boolean = true,
    val department: String = ""
)
