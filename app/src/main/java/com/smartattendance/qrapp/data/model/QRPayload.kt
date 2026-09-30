package com.smartattendance.qrapp.data.model

data class QRPayload(
    val sessionId: String = "",
    val classId: String = "",
    val className: String = "",
    val subject: String = "",
    val teacherUid: String = "",
    val createdAt: Long = 0L,
    val expiresAt: Long = 0L
)
