package com.valer0ld.artistfinder.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valer0ld.artistfinder.domain.model.Artist
import com.valer0ld.artistfinder.domain.model.TempArtist
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class GameUIState(
    val targetArtist: Artist = Artist("", 0),
    val currentArtist: Artist = Artist("", 0),
    val similarArtists: List<Artist> = emptyList(),
    val currentStep: Int = 0,
    val maxReturns: Int = 3,
    val currentReturns: Int = maxReturns,
    val processingButtonId: Int? = null
)

@HiltViewModel
class GameViewModel @Inject constructor(): ViewModel() {

    private val _uiState = MutableStateFlow(GameUIState())
    val uiState: StateFlow<GameUIState> = _uiState.asStateFlow()

    fun onArtistButtonClick(buttonId: Int, artist: Artist, onWinGame: ()->Unit) {

        if (_uiState.value.processingButtonId != null) return

        _uiState.update { it.copy (processingButtonId = buttonId) }

        viewModelScope.launch {
            try {
                processButton(artist, onWinGame)
                delay(200)
            } finally {
                _uiState.update { it.copy (processingButtonId = null) }
            }
        }
    }

    fun onReturnClick(){
        _uiState.update { it.copy (
            currentStep = it.currentStep - 1,
            currentReturns = it.currentReturns - 1
        ) }
    }

    fun onUpdateState(){
        _uiState.update { it.copy (
            currentStep = 0,
            currentReturns = it.maxReturns,
            similarArtists = emptyList()
        ) }
    }
    private fun processButton(artist: Artist, onWinGame: ()->Unit){
        if (artist == _uiState.value.targetArtist) {
            onWinGame()
            onUpdateState()
        }
        else{
            _uiState.update { it.copy (
                currentArtist = artist,
                currentStep =  it.currentStep + 1,
                similarArtists = artist.similarArtist ?: emptyList()
            ) }
        }
    }
}