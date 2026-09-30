package com.smartattendance.qrapp.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\fJ\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\"\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\"\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00130\t2\u0006\u0010\u0017\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\"\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\t2\u0006\u0010\u0019\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\u001c\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00140\t2\u0006\u0010\u001b\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010\u001cR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/smartattendance/qrapp/data/repository/AttendanceRepository;", "", "()V", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "recordsRef", "Lcom/google/firebase/firestore/CollectionReference;", "sessionsRef", "createSession", "Lcom/smartattendance/qrapp/util/Resource;", "Lcom/smartattendance/qrapp/data/model/AttendanceSession;", "session", "(Lcom/smartattendance/qrapp/data/model/AttendanceSession;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deactivateSession", "", "sessionId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAttendanceForSession", "", "Lcom/smartattendance/qrapp/data/model/AttendanceRecord;", "getSession", "getSessionsByTeacher", "teacherUid", "getStudentAttendance", "studentUid", "markAttendance", "record", "(Lcom/smartattendance/qrapp/data/model/AttendanceRecord;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class AttendanceRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.CollectionReference sessionsRef = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.CollectionReference recordsRef = null;
    
    public AttendanceRepository() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object createSession(@org.jetbrains.annotations.NotNull()
    com.smartattendance.qrapp.data.model.AttendanceSession session, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.smartattendance.qrapp.util.Resource<com.smartattendance.qrapp.data.model.AttendanceSession>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deactivateSession(@org.jetbrains.annotations.NotNull()
    java.lang.String sessionId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.smartattendance.qrapp.util.Resource<java.lang.Boolean>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getSessionsByTeacher(@org.jetbrains.annotations.NotNull()
    java.lang.String teacherUid, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.smartattendance.qrapp.util.Resource<java.util.List<com.smartattendance.qrapp.data.model.AttendanceSession>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAttendanceForSession(@org.jetbrains.annotations.NotNull()
    java.lang.String sessionId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.smartattendance.qrapp.util.Resource<java.util.List<com.smartattendance.qrapp.data.model.AttendanceRecord>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getSession(@org.jetbrains.annotations.NotNull()
    java.lang.String sessionId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.smartattendance.qrapp.util.Resource<com.smartattendance.qrapp.data.model.AttendanceSession>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object markAttendance(@org.jetbrains.annotations.NotNull()
    com.smartattendance.qrapp.data.model.AttendanceRecord record, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.smartattendance.qrapp.util.Resource<com.smartattendance.qrapp.data.model.AttendanceRecord>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getStudentAttendance(@org.jetbrains.annotations.NotNull()
    java.lang.String studentUid, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.smartattendance.qrapp.util.Resource<java.util.List<com.smartattendance.qrapp.data.model.AttendanceRecord>>> $completion) {
        return null;
    }
}