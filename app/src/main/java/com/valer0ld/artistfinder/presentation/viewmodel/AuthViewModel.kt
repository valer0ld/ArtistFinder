package com.valer0ld.artistfinder.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valer0ld.artistfinder.domain.repository.AuthRepository
import com.valer0ld.artistfinder.domain.repository.CurrentUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val currentUserRepository: CurrentUserRepository
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

                if (loggedIn) currentUserRepository.refreshUser()
            }
        }
    }
}