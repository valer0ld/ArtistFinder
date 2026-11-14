package com.valer0ld.artistfinder.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valer0ld.artistfinder.domain.repository.CurrentGameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WinBarViewModel @Inject constructor(
    private val currentGameRepository: CurrentGameRepository
) : ViewModel() {

    fun onUpdateState() {
        viewModelScope.launch {
            currentGameRepository.clear()
        }
    }

}