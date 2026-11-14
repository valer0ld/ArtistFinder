package com.valer0ld.artistfinder.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valer0ld.artistfinder.R
import com.valer0ld.artistfinder.domain.model.SettingId
import com.valer0ld.artistfinder.domain.repository.CurrentUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProfileUIState(
    val userName: String = "",
    val userPicture: String = "",
    val basePicture: Int = R.drawable.webp_user_icon_black,
    val rating: Int = 1
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val currentUserRepository: CurrentUserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUIState())
    val uiState : StateFlow<ProfileUIState> = _uiState.asStateFlow()

    init{
        viewModelScope.launch {
            currentUserRepository.observeUser().collect { user ->
                if (user == null) return@collect

                _uiState.update {
                    it.copy(
                        userName = user.userName,
                        userPicture = user.avatarUrl,
                        rating = user.bestRating
                    )
                }
            }
        }
    }

}