package com.valer0ld.artistfinder.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valer0ld.artistfinder.ui.theme.AppTheme



@Composable
fun AnimatedStepsList(expanded: Boolean, step: List<String>){
    AnimatedContent(
        targetState = expanded,
        transitionSpec = {
            slideInVertically { -it } + fadeIn() togetherWith
                    slideOutVertically { -it } + fadeOut()
        },
        label = "steps"
    ) { isExpanded ->
        if (isExpanded) {
            Column{
                step.forEachIndexed { index, item ->
                    StepsItem(index, item)
                }
            }
        } else {
            Spacer(Modifier.height(0.dp))
        }
    }
}

@Composable
fun StepsItem(index: Int, artist: String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ){
        Text(
            text = index.toString(),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.tertiaryContainer, shape = CircleShape)
                .padding(3.dp)
                .size(20.dp),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.width(10.dp))
        Text(
            text = artist,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.tertiary,
        )
    }
}

@Preview
@Composable
fun StepsItemPreview(){
    AppTheme{
        StepsItem(1,"Воскресенье")
    }
}