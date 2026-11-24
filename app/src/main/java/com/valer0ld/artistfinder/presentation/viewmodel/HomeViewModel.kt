package com.valer0ld.artistfinder.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valer0ld.artistfinder.domain.manager.GameSessionManager
import com.valer0ld.artistfinder.domain.model.Artist
import com.valer0ld.artistfinder.domain.model.TempArtist
import com.valer0ld.artistfinder.domain.repository.CurrentGameRepository
import com.valer0ld.artistfinder.domain.repository.CurrentUserRepository
import com.valer0ld.artistfinder.domain.validation.error_enums.ArtistSearchError
import com.valer0ld.artistfinder.domain.validation.validators.validateArtist
import com.valer0ld.artistfinder.domain.validation.validators.validateEmail
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
    val searchStartArtist: String = "",
    val searchEndArtist: String = "",
    val searchStartList: List<Artist> = emptyList(),
    val searchEndList: List<Artist> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val startArtistError: ArtistSearchError? = null,
    val endArtistError: ArtistSearchError? = null
)

enum class SearchArtist{
    START,
    END
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val currentGameRepository: CurrentGameRepository,
    private val currentUserRepository: CurrentUserRepository,
    private val gameSessionManager: GameSessionManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState: StateFlow<HomeUIState> = _uiState.asStateFlow()

    init {
        loadDailyArtist()
    }

    fun onGameStart(isDaily: Boolean, onSuccess: ()->Unit){
        viewModelScope.launch {
            if (validateData()){
                if (isDaily) currentGameRepository.startGame(_uiState.value.dailyStartArtist, _uiState.value.dailyEndArtist, true)
                else {
                    val startArtist = Artist(_uiState.value.searchStartArtist, 0)
                    val endArtist = Artist(_uiState.value.searchEndArtist, 0)
                    currentGameRepository.startGame(startArtist, endArtist, false)
                }
                currentUserRepository.startGame(isDaily)
                gameSessionManager.reset()
                onSuccess()
            }
        }
    }

    fun validateData(): Boolean{
        val state = _uiState.value

        val first = validateArtist(state.searchStartArtist)
        val second = validateArtist(state.searchEndArtist)

        _uiState.update {
            it.copy(
                startArtistError = first,
                endArtistError = second
            )
        }

        return !(first != null || second != null)
    }

    fun onStartValueChanged(value: String){

        _uiState.update { state ->
            state.copy(
                searchStartArtist = value,
                startArtistError = if (state.startArtistError != null){
                    validateArtist(value)
                } else {
                    null
                }
            )
        }

        loadSearchList(SearchArtist.START, value)
    }

    fun onEndValueChanged(value: String){

        _uiState.update { state ->
            state.copy(
                searchEndArtist = value,
                endArtistError = if (state.endArtistError != null){
                    validateArtist(value)
                } else {
                    null
                }
            )
        }

        loadSearchList(SearchArtist.END, value)
    }

    fun loadSearchList(searchArtist: SearchArtist, value: String){
        when(searchArtist){
            SearchArtist.START -> {
                if (value != "") _uiState.update { it.copy (searchStartList = TempArtist.list) }
                else  _uiState.update { it.copy (searchStartList = emptyList()) }
            }
            SearchArtist.END -> {
                if (value != "") _uiState.update { it.copy (searchEndList = TempArtist.list) }
                else  _uiState.update { it.copy (searchEndList = emptyList()) }
            }
        }
    }

    fun randomArtists(){
        _uiState.update { it.copy (
            searchStartArtist = TempArtist.list[0].name,
            searchEndArtist = TempArtist.list[1].name
        ) }
    }

    fun loadDailyArtist(){
        viewModelScope.launch {
            _uiState.update { it.copy (isLoading = true, error = null)}
            try{
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