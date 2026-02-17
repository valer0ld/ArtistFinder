package com.valer0ld.artistfinder.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valer0ld.artistfinder.repo.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _isLoggedIn = MutableStateFlow<Boolean?>(null)
    val isLoggedIn: StateFlow<Boolean?> = _isLoggedIn.asStateFlow()

    init{
        checkAuthState()
    }

    fun checkAuthState(){
        viewModelScope.launch {
            authRepository.isLoggedIn.collect { loggedIn ->
                _isLoggedIn.value = loggedIn
            }
        }
    }

    fun login(token: String, userID: String){
        viewModelScope.launch {
            authRepository.saveLogin(token, userID)
        }
    }
}
