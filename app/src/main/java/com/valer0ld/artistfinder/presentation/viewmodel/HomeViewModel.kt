package com.valer0ld.artistfinder.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valer0ld.artistfinder.domain.model.Artist
import com.valer0ld.artistfinder.domain.model.TempArtist
import com.valer0ld.artistfinder.domain.model.TempListOfGames
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUIState(
    val dailyStartArtist: Artist = Artist("", 0),
    val dailyEndArtist: Artist = Artist("", 0),
    val searchStartArtist: Artist = Artist("", 0),
    val searchEndArtist: Artist = Artist("", 0),
    val searchStartList: List<Artist> = emptyList(),
    val searchEndList: List<Artist> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

enum class SearchArtist{
    START,
    END
}

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState: StateFlow<HomeUIState> = _uiState.asStateFlow()

    init {
        loadDailyArtist()
    }

    fun onStartValueChanged(value: String){
        val newArtist = Artist(value, 0)
        _uiState.update { it.copy (searchStartArtist = newArtist) }
        loadSearchList(SearchArtist.START, value)
    }

    fun onEndValueChanged(value: String){
        val newArtist = Artist(value, 0)
        _uiState.update { it.copy (searchEndArtist = newArtist) }
        loadSearchList(SearchArtist.END, value)
    }

    fun loadSearchList(searchArtist: SearchArtist, value: String){
        when(searchArtist){
            SearchArtist.START -> {
                if (value != "") _uiState.update { it.copy (searchStartList = emptyList()) }
            }
            SearchArtist.END -> {
                if (value != "") _uiState.update { it.copy (searchEndList = TempArtist.list) }
            }
        }
    }

    fun randomArtists(){
        _uiState.update { it.copy (
            searchStartArtist = TempArtist.list[0],
            searchEndArtist = TempArtist.list[1]
        ) }
    }

    fun loadDailyArtist(){
        viewModelScope.launch {
            _uiState.update { it.copy (isLoading = true, error = null)}
            try{
                val list = TempListOfGames.list
                _uiState.update {
                    it.copy(
                        dailyStartArtist = TempArtist.list[0],
                        dailyEndArtist = TempArtist.list[1],
                        isLoading = false
                    )
                }
            } catch (e: Exception){
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Неизвестная ошибка"
                    )
                }
            }
        }
    }

}