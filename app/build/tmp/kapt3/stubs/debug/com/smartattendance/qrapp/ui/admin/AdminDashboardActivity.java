package com.smartattendance.qrapp.ui.admin;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0017B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\u0011\u001a\u00020\nH\u0002J\u0012\u0010\u0012\u001a\u00020\n2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\b\u0010\u0015\u001a\u00020\nH\u0014J\u0010\u0010\u0016\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u0012\u0010\u0003\u001a\u00060\u0004R\u00020\u0000X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/smartattendance/qrapp/ui/admin/AdminDashboardActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/smartattendance/qrapp/ui/admin/AdminDashboardActivity$UserAdapter;", "authRepository", "Lcom/smartattendance/qrapp/data/repository/AuthRepository;", "binding", "Lcom/smartattendance/qrapp/databinding/ActivityAdminDashboardBinding;", "assignRole", "", "user", "Lcom/smartattendance/qrapp/data/model/User;", "role", "", "confirmDeleteUser", "deleteUser", "loadUsers", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "showAssignRoleDialog", "UserAdapter", "app_debug"})
public final class AdminDashboardActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.smartattendance.qrapp.databinding.ActivityAdminDashboardBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final com.smartattendance.qrapp.data.repository.AuthRepository authRepository = null;
    private com.smartattendance.qrapp.ui.admin.AdminDashboardActivity.UserAdapter adapter;
    
    public AdminDashboardActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    private final void loadUsers() {
    }
    
    private final void showAssignRoleDialog(com.smartattendance.qrapp.data.model.User user) {
    }
    
    private final void assignRole(com.smartattendance.qrapp.data.model.User user, java.lang.String role) {
    }
    
    private final void confirmDeleteUser(com.smartattendance.qrapp.data.model.User user) {
    }
    
    private final void deleteUser(com.smartattendance.qrapp.data.model.User user) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u0010\u0012\f\u0012\n0\u0002R\u00060\u0000R\u00020\u00030\u0001:\u0001\u0018B-\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u00a2\u0006\u0002\u0010\tJ\b\u0010\f\u001a\u00020\rH\u0016J \u0010\u000e\u001a\u00020\u00072\u000e\u0010\u000f\u001a\n0\u0002R\u00060\u0000R\u00020\u00032\u0006\u0010\u0010\u001a\u00020\rH\u0016J \u0010\u0011\u001a\n0\u0002R\u00060\u0000R\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\rH\u0016J\u0014\u0010\u0015\u001a\u00020\u00072\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u0017R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/smartattendance/qrapp/ui/admin/AdminDashboardActivity$UserAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/smartattendance/qrapp/ui/admin/AdminDashboardActivity$UserAdapter$ViewHolder;", "Lcom/smartattendance/qrapp/ui/admin/AdminDashboardActivity;", "onAssignRole", "Lkotlin/Function1;", "Lcom/smartattendance/qrapp/data/model/User;", "", "onDelete", "(Lcom/smartattendance/qrapp/ui/admin/AdminDashboardActivity;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "users", "", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "submitList", "list", "", "ViewHolder", "app_debug"})
    public final class UserAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.smartattendance.qrapp.ui.admin.AdminDashboardActivity.UserAdapter.ViewHolder> {
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<com.smartattendance.qrapp.data.model.User, kotlin.Unit> onAssignRole = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<com.smartattendance.qrapp.data.model.User, kotlin.Unit> onDelete = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.smartattendance.qrapp.data.model.User> users = null;
        
        public UserAdapter(@org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super com.smartattendance.qrapp.data.model.User, kotlin.Unit> onAssignRole, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super com.smartattendance.qrapp.data.model.User, kotlin.Unit> onDelete) {
            super();
        }
        
        public final void submitList(@org.jetbrains.annotations.NotNull()
        java.util.List<com.smartattendance.qrapp.data.model.User> list) {
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public com.smartattendance.qrapp.ui.admin.AdminDashboardActivity.UserAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, int viewType) {
            return null;
        }
        
        @java.lang.Override()
        public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
        com.smartattendance.qrapp.ui.admin.AdminDashboardActivity.UserAdapter.ViewHolder holder, int position) {
        }
        
        @java.lang.Override()
        public int getItemCount() {
            return 0;
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/smartattendance/qrapp/ui/admin/AdminDashboardActivity$UserAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "b", "Lcom/smartattendance/qrapp/databinding/ItemUserBinding;", "(Lcom/smartattendance/qrapp/ui/admin/AdminDashboardActivity$UserAdapter;Lcom/smartattendance/qrapp/databinding/ItemUserBinding;)V", "getB", "()Lcom/smartattendance/qrapp/databinding/ItemUserBinding;", "app_debug"})
        public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
            @org.jetbrains.annotations.NotNull()
            private final com.smartattendance.qrapp.databinding.ItemUserBinding b = null;
            
            public ViewHolder(@org.jetbrains.annotations.NotNull()
            com.smartattendance.qrapp.databinding.ItemUserBinding b) {
                super(null);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.smartattendance.qrapp.databinding.ItemUserBinding getB() {
                return null;
            }
        }
    }
}