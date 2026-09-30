package com.smartattendance.qrapp.data.model

data class AttendanceRecord(
    val recordId: String = "",
    val sessionId: String = "",
    val classId: String = "",
    val className: String = "",
    val subject: String = "",
    val studentUid: String = "",
    val studentName: String = "",
    val rollNumber: String = "",
    val department: String = "",
    val markedAt: Long = System.currentTimeMillis(),
    val status: String = "present"  // "present" or "absent"
)
