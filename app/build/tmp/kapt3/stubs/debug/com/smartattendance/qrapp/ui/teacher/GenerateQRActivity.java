package com.smartattendance.qrapp.ui.teacher;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0002J\u0012\u0010\u0010\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u000eH\u0014J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u000eH\u0002J\u0018\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/smartattendance/qrapp/ui/teacher/GenerateQRActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "attendanceRepository", "Lcom/smartattendance/qrapp/data/repository/AttendanceRepository;", "authRepository", "Lcom/smartattendance/qrapp/data/repository/AuthRepository;", "binding", "Lcom/smartattendance/qrapp/databinding/ActivityGenerateQrBinding;", "countDownTimer", "Landroid/os/CountDownTimer;", "currentSessionId", "", "deactivateSession", "", "generateQR", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onSupportNavigateUp", "", "setupDurationDropdown", "showQRCode", "bitmap", "Landroid/graphics/Bitmap;", "durationMs", "", "Companion", "app_debug"})
public final class GenerateQRActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.smartattendance.qrapp.databinding.ActivityGenerateQrBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final com.smartattendance.qrapp.data.repository.AuthRepository authRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.smartattendance.qrapp.data.repository.AttendanceRepository attendanceRepository = null;
    @org.jetbrains.annotations.Nullable()
    private android.os.CountDownTimer countDownTimer;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String currentSessionId;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<kotlin.Pair<java.lang.String, java.lang.Long>> DURATION_OPTIONS = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.smartattendance.qrapp.ui.teacher.GenerateQRActivity.Companion Companion = null;
    
    public GenerateQRActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupDurationDropdown() {
    }
    
    private final void generateQR() {
    }
    
    private final void showQRCode(android.graphics.Bitmap bitmap, long durationMs) {
    }
    
    private final void deactivateSession() {
    }
    
    @java.lang.Override()
    public boolean onSupportNavigateUp() {
        return false;
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/smartattendance/qrapp/ui/teacher/GenerateQRActivity$Companion;", "", "()V", "DURATION_OPTIONS", "", "Lkotlin/Pair;", "", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}