package com.valer0ld.artistfinder.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExpandedAnimatedComponent(expanded: Boolean, content: @Composable (AnimatedContentScope.()->Unit)){
    AnimatedContent(
        targetState = expanded,
        transitionSpec = {
            slideInVertically { -it } + fadeIn() togetherWith
                    slideOutVertically { -it } + fadeOut()
        },
        label = "steps"
    ) { isExpanded ->
        if (isExpanded) {
            content()
        } else {
            Spacer(Modifier.height(0.dp))
        }
    }
}