package com.example.myweather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _isUserLoggedIn = MutableStateFlow(firebaseAuth.currentUser != null)
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn

    private val _isUserLoggedOut = MutableStateFlow(false)
    val isUserLoggedOut: StateFlow<Boolean> = _isUserLoggedOut

    private val authStateListener = FirebaseAuth.AuthStateListener { auth ->
        val isLoggedIn = auth.currentUser != null
        _isUserLoggedIn.value = isLoggedIn
        if (isLoggedIn) _isUserLoggedOut.value = false
    }

    init {
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    override fun onCleared() {
        super.onCleared()
        firebaseAuth.removeAuthStateListener(authStateListener)
    }

    fun login(email: String, password: String) {
        _isLoading.value = true
        _errorMessage.value = null

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _isLoading.value = false
                if (!task.isSuccessful) {
                    _errorMessage.value = task.exception?.message
                }
            }
    }

    fun register(email: String, password: String) {
        _isLoading.value = true
        _errorMessage.value = null

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _isLoading.value = false
                if (!task.isSuccessful) {
                    _errorMessage.value = task.exception?.message
                }
            }
    }

    fun signOut() {
        firebaseAuth.signOut()
        _isUserLoggedOut.value = true
    }
}
