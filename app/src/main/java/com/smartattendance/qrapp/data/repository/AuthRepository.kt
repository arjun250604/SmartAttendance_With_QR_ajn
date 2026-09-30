package com.smartattendance.qrapp.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.smartattendance.qrapp.data.model.User
import com.smartattendance.qrapp.util.Resource
import kotlinx.coroutines.tasks.await

class AuthRepository {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    val currentUser get() = auth.currentUser

    // ─── Admin creates a new account for a user ────────────────────────────────

    /**
     * Admin creates a user with a specific role.
     * Uses a secondary FirebaseAuth instance so admin doesn't get signed out.
     */
    suspend fun adminCreateUser(
        name: String,
        email: String,
        password: String,
        role: String,
        rollNumber: String,
        department: String
    ): Resource<User> {
        return try {
            // Use a secondary auth instance so admin stays logged in
            val secondaryApp = com.google.firebase.FirebaseApp.getApps(
                com.google.firebase.FirebaseApp.getInstance().applicationContext
            ).firstOrNull { it.name == "secondaryAuth" }

            val secondaryAuth = if (secondaryApp != null) {
                FirebaseAuth.getInstance(secondaryApp)
            } else {
                val options = com.google.firebase.FirebaseApp.getInstance().options
                val secondary = com.google.firebase.FirebaseApp.initializeApp(
                    com.google.firebase.FirebaseApp.getInstance().applicationContext,
                    options,
                    "secondaryAuth"
                )
                FirebaseAuth.getInstance(secondary)
            }

            val result = secondaryAuth.createUserWithEmailAndPassword(email, password).await()
            val uid = result.user?.uid ?: throw Exception("UID not found")
            secondaryAuth.signOut()

            val user = User(
                uid = uid,
                name = name,
                email = email,
                role = role,
                rollNumber = rollNumber,
                department = department,
                isApproved = true
            )
            firestore.collection("users").document(uid).set(user).await()
            Resource.Success(user)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Failed to create user")
        }
    }

    // ─── Self registration & login ──────────────────────────────────────────

    suspend fun register(
        name: String,
        email: String,
        password: String,
        role: String = "",
        rollNumber: String = "",
        department: String = ""
    ): Resource<User> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val uid = result.user?.uid ?: throw Exception("UID not found")
            val user = User(
                uid = uid,
                name = name,
                email = email,
                role = role,
                rollNumber = rollNumber,
                department = department,
                isApproved = true
            )
            firestore.collection("users").document(uid).set(user).await()
            Resource.Success(user)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Registration failed")
        }
    }

    suspend fun login(email: String, password: String): Resource<User> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            val uid = result.user?.uid ?: throw Exception("UID not found")
            val doc = firestore.collection("users").document(uid).get().await()

            val user = if (doc.exists()) {
                doc.toObject(User::class.java) ?: User(
                    uid = uid,
                    name = doc.getString("name") ?: result.user?.displayName ?: email.substringBefore("@"),
                    email = doc.getString("email") ?: email,
                    role = doc.getString("role") ?: (if (email.contains("admin", ignoreCase = true)) "admin" else ""),
                    rollNumber = doc.getString("rollNumber") ?: "",
                    department = doc.getString("department") ?: "",
                    isApproved = doc.getBoolean("isApproved") ?: (email.contains("admin", ignoreCase = true))
                )
            } else {
                // Document does not exist in Firestore yet: auto-create it
                val isFirstOrAdmin = email.contains("admin", ignoreCase = true) || isUsersCollectionEmpty()
                val role = if (isFirstOrAdmin) "admin" else ""
                val newUser = User(
                    uid = uid,
                    name = result.user?.displayName?.takeIf { it.isNotBlank() } ?: email.substringBefore("@").replaceFirstChar { it.uppercase() },
                    email = email,
                    role = role,
                    rollNumber = "",
                    department = "",
                    isApproved = role.isNotEmpty()
                )
                firestore.collection("users").document(uid).set(newUser).await()
                newUser
            }
            Resource.Success(user)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Login failed")
        }
    }

    suspend fun getCurrentUserData(): Resource<User> {
        return try {
            val currentUser = auth.currentUser ?: throw Exception("Not logged in")
            val uid = currentUser.uid
            val doc = firestore.collection("users").document(uid).get().await()

            val user = if (doc.exists()) {
                doc.toObject(User::class.java) ?: User(
                    uid = uid,
                    name = doc.getString("name") ?: currentUser.displayName ?: currentUser.email?.substringBefore("@") ?: "User",
                    email = doc.getString("email") ?: currentUser.email ?: "",
                    role = doc.getString("role") ?: (if (currentUser.email?.contains("admin", ignoreCase = true) == true) "admin" else ""),
                    rollNumber = doc.getString("rollNumber") ?: "",
                    department = doc.getString("department") ?: "",
                    isApproved = doc.getBoolean("isApproved") ?: (currentUser.email?.contains("admin", ignoreCase = true) == true)
                )
            } else {
                val email = currentUser.email ?: ""
                val isFirstOrAdmin = email.contains("admin", ignoreCase = true) || isUsersCollectionEmpty()
                val role = if (isFirstOrAdmin) "admin" else ""
                val newUser = User(
                    uid = uid,
                    name = currentUser.displayName?.takeIf { it.isNotBlank() } ?: email.substringBefore("@").replaceFirstChar { it.uppercase() },
                    email = email,
                    role = role,
                    rollNumber = "",
                    department = "",
                    isApproved = role.isNotEmpty()
                )
                firestore.collection("users").document(uid).set(newUser).await()
                newUser
            }
            Resource.Success(user)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Failed to fetch user")
        }
    }

    private suspend fun isUsersCollectionEmpty(): Boolean {
        return try {
            firestore.collection("users").limit(1).get().await().isEmpty
        } catch (e: Exception) {
            false
        }
    }

    // ─── Admin: list all users ─────────────────────────────────────────────────

    suspend fun getAllUsers(): Resource<List<User>> {
        return try {
            val snapshot = firestore.collection("users").get().await()
            val users = snapshot.documents.mapNotNull { d ->
                d.toObject(User::class.java) ?: User(
                    uid = d.id,
                    name = d.getString("name") ?: "Unknown",
                    email = d.getString("email") ?: "",
                    role = d.getString("role") ?: "",
                    rollNumber = d.getString("rollNumber") ?: "",
                    department = d.getString("department") ?: "",
                    isApproved = d.getBoolean("isApproved") ?: false
                )
            }
            Resource.Success(users)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Failed to fetch users")
        }
    }

    /**
     * Admin updates a user's role, department, and rollNumber in Firestore.
     */
    suspend fun updateUserRole(
        uid: String,
        role: String,
        department: String,
        rollNumber: String
    ): Resource<Boolean> {
        return try {
            val updates = hashMapOf<String, Any>(
                "role" to role,
                "department" to department,
                "rollNumber" to rollNumber,
                "isApproved" to true
            )
            firestore.collection("users").document(uid).update(updates).await()
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Failed to update role")
        }
    }

    /**
     * Admin deletes a user document from Firestore.
     * Note: Firebase Auth account deletion requires Admin SDK; this only removes Firestore data.
     */
    suspend fun deleteUser(uid: String): Resource<Boolean> {
        return try {
            firestore.collection("users").document(uid).delete().await()
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Failed to delete user")
        }
    }

    fun logout() {
        auth.signOut()
    }
}
