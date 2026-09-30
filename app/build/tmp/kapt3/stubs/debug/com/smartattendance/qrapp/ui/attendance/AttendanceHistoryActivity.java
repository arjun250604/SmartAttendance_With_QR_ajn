package com.smartattendance.qrapp.ui.attendance;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0014B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0012\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u0012\u0010\u0003\u001a\u00060\u0004R\u00020\u0000X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/smartattendance/qrapp/ui/attendance/AttendanceHistoryActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/smartattendance/qrapp/ui/attendance/AttendanceHistoryActivity$AttendanceAdapter;", "attendanceRepository", "Lcom/smartattendance/qrapp/data/repository/AttendanceRepository;", "authRepository", "Lcom/smartattendance/qrapp/data/repository/AuthRepository;", "binding", "Lcom/smartattendance/qrapp/databinding/ActivityAttendanceHistoryBinding;", "role", "", "loadHistory", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSupportNavigateUp", "", "AttendanceAdapter", "app_debug"})
public final class AttendanceHistoryActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.smartattendance.qrapp.databinding.ActivityAttendanceHistoryBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final com.smartattendance.qrapp.data.repository.AuthRepository authRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.smartattendance.qrapp.data.repository.AttendanceRepository attendanceRepository = null;
    private com.smartattendance.qrapp.ui.attendance.AttendanceHistoryActivity.AttendanceAdapter adapter;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String role = "student";
    
    public AttendanceHistoryActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void loadHistory() {
    }
    
    @java.lang.Override()
    public boolean onSupportNavigateUp() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u0010\u0012\f\u0012\n0\u0002R\u00060\u0000R\u00020\u00030\u0001:\u0001\u0015B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\tH\u0016J \u0010\n\u001a\u00020\u000b2\u000e\u0010\f\u001a\n0\u0002R\u00060\u0000R\u00020\u00032\u0006\u0010\r\u001a\u00020\tH\u0016J \u0010\u000e\u001a\n0\u0002R\u00060\u0000R\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\tH\u0016J\u0014\u0010\u0012\u001a\u00020\u000b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u0014R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/smartattendance/qrapp/ui/attendance/AttendanceHistoryActivity$AttendanceAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/smartattendance/qrapp/ui/attendance/AttendanceHistoryActivity$AttendanceAdapter$ViewHolder;", "Lcom/smartattendance/qrapp/ui/attendance/AttendanceHistoryActivity;", "(Lcom/smartattendance/qrapp/ui/attendance/AttendanceHistoryActivity;)V", "records", "", "Lcom/smartattendance/qrapp/data/model/AttendanceRecord;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "submitList", "list", "", "ViewHolder", "app_debug"})
    public final class AttendanceAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.smartattendance.qrapp.ui.attendance.AttendanceHistoryActivity.AttendanceAdapter.ViewHolder> {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.smartattendance.qrapp.data.model.AttendanceRecord> records = null;
        
        public AttendanceAdapter() {
            super();
        }
        
        public final void submitList(@org.jetbrains.annotations.NotNull()
        java.util.List<com.smartattendance.qrapp.data.model.AttendanceRecord> list) {
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public com.smartattendance.qrapp.ui.attendance.AttendanceHistoryActivity.AttendanceAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, int viewType) {
            return null;
        }
        
        @java.lang.Override()
        public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
        com.smartattendance.qrapp.ui.attendance.AttendanceHistoryActivity.AttendanceAdapter.ViewHolder holder, int position) {
        }
        
        @java.lang.Override()
        public int getItemCount() {
            return 0;
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/smartattendance/qrapp/ui/attendance/AttendanceHistoryActivity$AttendanceAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "b", "Lcom/smartattendance/qrapp/databinding/ItemAttendanceRecordBinding;", "(Lcom/smartattendance/qrapp/ui/attendance/AttendanceHistoryActivity$AttendanceAdapter;Lcom/smartattendance/qrapp/databinding/ItemAttendanceRecordBinding;)V", "getB", "()Lcom/smartattendance/qrapp/databinding/ItemAttendanceRecordBinding;", "app_debug"})
        public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
            @org.jetbrains.annotations.NotNull()
            private final com.smartattendance.qrapp.databinding.ItemAttendanceRecordBinding b = null;
            
            public ViewHolder(@org.jetbrains.annotations.NotNull()
            com.smartattendance.qrapp.databinding.ItemAttendanceRecordBinding b) {
                super(null);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.smartattendance.qrapp.databinding.ItemAttendanceRecordBinding getB() {
                return null;
            }
        }
    }
}