package com.valer0ld.artistfinder.presentation.screens.rating.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun TableHeader(){
    TableItem(color = MaterialTheme.colorScheme.primaryContainer){
        RatingHeaderText("№", modifier = Modifier.weight(1f))
        VerticalDivider(color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.3f))
        RatingHeaderText("Пользователь", modifier = Modifier.weight(5f))
        VerticalDivider(color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.3f))
        RatingHeaderText("Баллы", modifier = Modifier.weight(2f))
    }
}

@Composable
fun TableItem( modifier: Modifier = Modifier, color: Color = Color.Transparent, content: @Composable RowScope.()->Unit ){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(color)
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        content()
    }
}
