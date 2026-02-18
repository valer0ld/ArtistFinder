package com.valer0ld.artistfinder.ui.screens.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valer0ld.artistfinder.ui.models.Artist
import com.valer0ld.artistfinder.ui.models.TempArtist
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameViewModel: ViewModel() {
    val artists = TempArtist.list
    val targetArtist = TempArtist.list[TempArtist.list.size-1]

    private val _currentArtist = MutableStateFlow<Artist>(artists[0])
    val currentArtist: StateFlow<Artist> = _currentArtist.asStateFlow()

    private val _currentStep =  MutableStateFlow<Int>(0)
    var currentStep: StateFlow<Int> = _currentStep.asStateFlow()

    val maxReturns = 3
    private val _currentReturns = MutableStateFlow<Int>(maxReturns)
    var currentReturns: StateFlow<Int> = _currentReturns.asStateFlow()

    private val _processingButtonId = MutableStateFlow<Int?>(null)
    val processingButtonId: StateFlow<Int?> = _processingButtonId.asStateFlow()


    fun onArtistButtonClick(buttonId: Int, artist: Artist, onWinGame: ()->Unit) {

        if (_processingButtonId.value != null) return

        _processingButtonId.value = buttonId

        viewModelScope.launch {
            try {
                processButton(artist, onWinGame)
                delay(300)
            } finally {
                _processingButtonId.value = null
            }
        }
    }

    fun onReturnClick(){
        _currentStep.value--
        _currentReturns.value--
    }

    fun onUpdateState(){
        _currentStep.value = 0
        _currentReturns.value = maxReturns
        _currentArtist.value = artists[0]
    }
    private fun processButton(artist: Artist, onWinGame: ()->Unit){
        if (artist == targetArtist) {
            onWinGame()
            onUpdateState()
        }
        else{
            _currentArtist.value = artist
            _currentStep.value++
        }
    }
}