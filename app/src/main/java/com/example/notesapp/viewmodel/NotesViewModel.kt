package com.example.notesapp.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth

class NotesViewModel: ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth

    fun signOut() {
        auth.signOut()
    }
}