package com.valer0ld.artistfinder.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.valer0ld.artistfinder.ui.models.TextConfig

@Composable
fun TitleAndContentText(title: TextConfig, content: TextConfig){
    Column(horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = title.text,
            style = title.style,
            color = title.color)

        Text(text = content.text,
            style = content.style,
            color = content.color)
    }
}