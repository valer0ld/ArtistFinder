package com.valer0ld.artistfinder.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

@Composable
fun LazyColumnWithScrollbar(
    listState: LazyListState,
    isExpanded: Boolean = false,
    color: Color = MaterialTheme.colorScheme.onSurface,
    modifier: Modifier = Modifier, content: @Composable (BoxScope.()->Unit)
){
    Box(modifier = modifier){

        content()

        LazyColumnScrollbar(
            listState,
            isExpanded,
            color,
            modifier = Modifier
                .align(Alignment.CenterEnd)
        )
    }
}

@Composable
fun LazyColumnScrollbar(listState: LazyListState, isExpanded: Boolean, color: Color, modifier: Modifier = Modifier){
    val layoutInfo = listState.layoutInfo
    val viewport = layoutInfo.viewportSize.height.toFloat()
    val totalItems = layoutInfo.totalItemsCount
    val visible = layoutInfo.visibleItemsInfo

    if (totalItems == 0 || visible.isEmpty() || totalItems <= visible.size) return

    val averageItemSize = visible
        .map { it.size }
        .average()
        .toFloat()
        .coerceAtLeast(1f)

    val totalContentHeight = averageItemSize * totalItems
    val scrollPx = listState.firstVisibleItemIndex * averageItemSize +
            listState.firstVisibleItemScrollOffset
    val maxScroll = (totalContentHeight - viewport).coerceAtLeast(1f)
    val progress = (scrollPx / maxScroll).coerceIn(0f, 1f)

    val thumbRatio = (viewport / totalContentHeight).coerceIn(0.05f, 1f) // возможно потом стоит исправить, чтобы не пересчитывал при изменении состояния

    val animateProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 50)
    )


    Box(
        modifier = modifier
            .fillMaxHeight()
            .padding(end = 2.dp)
            .width(4.dp)
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(thumbRatio)
                .align(Alignment.TopStart)
                .offset{
                    val track = listState.layoutInfo.viewportSize.height.toFloat()
                    val thumb = track * thumbRatio
                    val max = (track - thumb).coerceAtLeast(0f)

                    IntOffset(0, (max * animateProgress).toInt())
                }
                .clip(RoundedCornerShape(2.dp))
                .background(
                    color = color.copy(0.4f)
                )
        )
    }
}