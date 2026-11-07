package com.valer0ld.artistfinder.presentation.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import com.valer0ld.artistfinder.domain.model.Artist
import com.valer0ld.artistfinder.presentation.components.CustomTextField
import kotlinx.coroutines.launch

@Composable
fun SearchArtist(name: String, list: List<Artist>, onValueChanged: (String)->Unit){
    var isFocused by remember { mutableStateOf(false) }
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    Column{
        CustomTextField("Старт", name, onValueChanged,
            Modifier.onFocusChanged { focusState ->
                isFocused = focusState.isFocused

                if (focusState.isFocused) {
                    coroutineScope.launch {
                        bringIntoViewRequester.bringIntoView()
                    }
                }
            })
        if (isFocused && list.isNotEmpty()) {
            SearchList(
                list, onArtistClick = { name ->
                    onValueChanged(name)
                    focusManager.clearFocus()
                }
            )
        }
    }

}