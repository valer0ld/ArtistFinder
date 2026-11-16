package com.valer0ld.artistfinder.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.valer0ld.artistfinder.domain.model.Artist
import com.valer0ld.artistfinder.presentation.components.CustomTextField
import com.valer0ld.artistfinder.presentation.components.ExpandedAnimatedComponent
import kotlinx.coroutines.launch

@Composable
fun SearchArtist(name: String, placeholder: String, list: List<Artist>, onValueChanged: (String)->Unit){
    var isFocused by remember { mutableStateOf(false) }
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    Column(modifier = Modifier.bringIntoViewRequester(bringIntoViewRequester)){
        CustomTextField(placeholder, name, onValueChanged,
            Modifier
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused

                    if (focusState.isFocused) {
                        coroutineScope.launch {
                            bringIntoViewRequester.bringIntoView()
                        }
                    }

                }
                .background(
                    color = if (isFocused && list.isNotEmpty()) MaterialTheme.colorScheme.tertiary else  Color.Transparent,
                    shape = RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp)
                )
        )
        ExpandedAnimatedComponent(isFocused && list.isNotEmpty()) {
            SearchList(
                list, onArtistClick = { name ->
                    onValueChanged(name)
                    focusManager.clearFocus()
                }
            )
        }
    }

}