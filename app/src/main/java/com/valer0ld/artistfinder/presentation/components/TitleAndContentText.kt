package com.valer0ld.artistfinder.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.valer0ld.artistfinder.presentation.model.TextConfig

@Composable
fun TitleAndContentText(title: TextConfig, content: TextConfig, modifier: Modifier = Modifier){
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = title.text,
                style = title.style,
                color = title.color)

            Text(text = content.text,
                style = content.style,
                color = content.color)
        }
    }

}