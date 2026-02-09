package com.valer0ld.artistfinder.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ReturnActionHeader(icon: Int, label: String, onReturnClick: ()->Unit){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(10.dp),
        contentAlignment = Alignment.CenterStart
    ){
        BouncingIconButton(
            icon = icon,
            label = label,
            size = 40.dp,
            color = MaterialTheme.colorScheme.onBackground,
            onIconClick = onReturnClick
        )
    }
}
