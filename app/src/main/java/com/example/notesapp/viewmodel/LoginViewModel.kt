package com.example.notesapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import kotlin.math.log

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

    fun closeAlert() {
        showAlert = false
    }
}