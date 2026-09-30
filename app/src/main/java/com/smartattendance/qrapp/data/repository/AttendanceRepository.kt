package com.smartattendance.qrapp.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.smartattendance.qrapp.data.model.AttendanceRecord
import com.smartattendance.qrapp.data.model.AttendanceSession
import com.smartattendance.qrapp.util.Resource
import kotlinx.coroutines.tasks.await

class AttendanceRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val sessionsRef = firestore.collection("sessions")
    private val recordsRef = firestore.collection("attendance_records")

    // ─── Teacher Functions ────────────────────────────────────────────────────

    suspend fun createSession(session: AttendanceSession): Resource<AttendanceSession> {
        return try {
            sessionsRef.document(session.sessionId).set(session).await()
            Resource.Success(session)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Failed to create session")
        }
    }

    suspend fun deactivateSession(sessionId: String): Resource<Boolean> {
        return try {
            sessionsRef.document(sessionId).update("active", false).await()
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Failed to deactivate session")
        }
    }

    suspend fun getSessionsByTeacher(teacherUid: String): Resource<List<AttendanceSession>> {
        return try {
            val snapshot = sessionsRef
                .whereEqualTo("teacherUid", teacherUid)
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .get()
                .await()
            val sessions = snapshot.toObjects(AttendanceSession::class.java)
            Resource.Success(sessions)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Failed to fetch sessions")
        }
    }

    suspend fun getAttendanceForSession(sessionId: String): Resource<List<AttendanceRecord>> {
        return try {
            val snapshot = recordsRef
                .whereEqualTo("sessionId", sessionId)
                .get()
                .await()
            val records = snapshot.toObjects(AttendanceRecord::class.java)
            Resource.Success(records)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Failed to fetch attendance")
        }
    }

    // ─── Student Functions ────────────────────────────────────────────────────

    suspend fun getSession(sessionId: String): Resource<AttendanceSession> {
        return try {
            val doc = sessionsRef.document(sessionId).get().await()
            val session = doc.toObject(AttendanceSession::class.java)
                ?: throw Exception("Session not found")
            Resource.Success(session)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Session fetch failed")
        }
    }

    suspend fun markAttendance(record: AttendanceRecord): Resource<AttendanceRecord> {
        return try {
            // Check for duplicate
            val existing = recordsRef
                .whereEqualTo("sessionId", record.sessionId)
                .whereEqualTo("studentUid", record.studentUid)
                .get()
                .await()
            if (!existing.isEmpty) {
                return Resource.Error("Attendance already marked for this session")
            }
            recordsRef.document(record.recordId).set(record).await()
            Resource.Success(record)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Failed to mark attendance")
        }
    }

    suspend fun getStudentAttendance(studentUid: String): Resource<List<AttendanceRecord>> {
        return try {
            val snapshot = recordsRef
                .whereEqualTo("studentUid", studentUid)
                .orderBy("markedAt", Query.Direction.DESCENDING)
                .get()
                .await()
            val records = snapshot.toObjects(AttendanceRecord::class.java)
            Resource.Success(records)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Failed to fetch attendance history")
        }
    }
}
