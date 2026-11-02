package com.valer0ld.artistfinder.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.valer0ld.artistfinder.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class ProfileUIState(
    val userName: String = "Имя пользователя",
    val userPicture: Int = R.drawable.webp_user_icon_black,
    val rating: Int = 1
)

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUIState())
    val uiState : StateFlow<ProfileUIState> = _uiState.asStateFlow()


}