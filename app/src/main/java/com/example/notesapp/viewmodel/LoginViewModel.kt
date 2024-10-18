package com.example.notesapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.model.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth;
    var showAlert by mutableStateOf(false)

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            onSuccess()
                        } else {
                            Log.e(
                                "LoginViewModel",
                                "Error logging in Firebase " + task.exception?.localizedMessage
                            )
                            showAlert = true
                        }
                    }
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Error logging in " + e.localizedMessage)
            }
        }

    }

    fun createUser(email: String, password: String, username: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            saveUser(username)
                            onSuccess()
                        } else {
                            Log.e(
                                "LoginViewModel",
                                "Error register in Firebase " + task.exception?.localizedMessage
                            )
                            showAlert = true
                        }
                    }
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Error logging in " + e.localizedMessage)
            }
        }

    }

    private fun saveUser(userName: String) {
        val id = auth.currentUser?.uid
        val email = auth.currentUser?.email

        val user = UserModel(userId = id.toString(), email = email.toString(), userName = userName)

        FirebaseFirestore.getInstance().collection("users")
            .add(user)
            .addOnSuccessListener {
                Log.d("LoginViewModel", "User saved")
            }
            .addOnFailureListener {
                Log.e("LoginViewModel", "Error saving user")
            }
    }

    fun closeAlert() {
        showAlert = false
    }
}