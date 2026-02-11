package com.valer0ld.artistfinder.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valer0ld.artistfinder.R

@Composable
fun ReturnActionHeader(modifier: Modifier = Modifier, onReturnClick: ()->Unit){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .then(modifier)
            .height(70.dp),
        contentAlignment = Alignment.CenterStart
    ){
        BouncingIconButton(
            icon = R.drawable.arrow_back_24px,
            label = "Return arrow",
            size = 50.dp,
            color = MaterialTheme.colorScheme.onBackground,
            onIconClick = onReturnClick
        )
    }
}

@Preview
@Composable
fun ReturnActionHeaderPreview(){
    ReturnActionHeader(Modifier, { })
}
