package com.smartattendance.qrapp.ui.attendance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartattendance.qrapp.data.model.AttendanceRecord
import com.smartattendance.qrapp.data.repository.AttendanceRepository
import com.smartattendance.qrapp.data.repository.AuthRepository
import com.smartattendance.qrapp.databinding.ActivityAttendanceHistoryBinding
import com.smartattendance.qrapp.databinding.ItemAttendanceRecordBinding
import com.smartattendance.qrapp.util.Resource
import com.smartattendance.qrapp.util.toFormattedDateTime
import com.smartattendance.qrapp.util.toast
import kotlinx.coroutines.launch

class AttendanceHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAttendanceHistoryBinding
    private val authRepository = AuthRepository()
    private val attendanceRepository = AttendanceRepository()
    private lateinit var adapter: AttendanceAdapter
    private var role = "student"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAttendanceHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        role = intent.getStringExtra("role") ?: "student"

        supportActionBar?.apply {
            title = "Attendance History"
            setDisplayHomeAsUpEnabled(true)
        }

        adapter = AttendanceAdapter()
        binding.rvAttendance.layoutManager = LinearLayoutManager(this)
        binding.rvAttendance.adapter = adapter

        loadHistory()
    }

    private fun loadHistory() {
        binding.progressBar.isVisible = true
        lifecycleScope.launch {
            val uid = authRepository.currentUser?.uid ?: return@launch
            val result = when (role) {
                "teacher" -> {
                    // For teachers, aggregate all records from their sessions
                    val sessionsResult = attendanceRepository.getSessionsByTeacher(uid)
                    if (sessionsResult is Resource.Success) {
                        val allRecords = mutableListOf<AttendanceRecord>()
                        sessionsResult.data!!.forEach { session ->
                            val recs = attendanceRepository.getAttendanceForSession(session.sessionId)
                            if (recs is Resource.Success) allRecords.addAll(recs.data!!)
                        }
                        Resource.Success(allRecords)
                    } else {
                        Resource.Error<List<AttendanceRecord>>("Failed to load sessions")
                    }
                }
                else -> attendanceRepository.getStudentAttendance(uid)
            }

            binding.progressBar.isVisible = false
            when (result) {
                is Resource.Success -> {
                    val records = result.data!!
                    if (records.isEmpty()) {
                        binding.tvEmpty.isVisible = true
                        binding.rvAttendance.isVisible = false
                    } else {
                        binding.tvEmpty.isVisible = false
                        binding.rvAttendance.isVisible = true
                        adapter.submitList(records)
                    }
                }
                is Resource.Error -> toast(result.message ?: "Error loading history")
                else -> {}
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    // ─── Inner Adapter ────────────────────────────────────────────────────────

    inner class AttendanceAdapter : RecyclerView.Adapter<AttendanceAdapter.ViewHolder>() {

        private val records = mutableListOf<AttendanceRecord>()

        fun submitList(list: List<AttendanceRecord>) {
            records.clear()
            records.addAll(list)
            notifyDataSetChanged()
        }

        inner class ViewHolder(val b: ItemAttendanceRecordBinding) : RecyclerView.ViewHolder(b.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val b = ItemAttendanceRecordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(b)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val record = records[position]
            with(holder.b) {
                tvClassName.text = "${record.className} – ${record.subject}"
                tvStudentName.text = if (role == "teacher") "${record.studentName} (${record.rollNumber})" else record.studentName
                tvDateTime.text = record.markedAt.toFormattedDateTime()
                tvStatus.text = record.status.uppercase()
                tvStatus.setBackgroundResource(
                    if (record.status == "present")
                        com.smartattendance.qrapp.R.drawable.bg_status_present
                    else
                        com.smartattendance.qrapp.R.drawable.bg_status_absent
                )
            }
        }

        override fun getItemCount() = records.size
    }
}
