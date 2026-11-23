package com.valer0ld.artistfinder.presentation.screens.game.components

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.valer0ld.artistfinder.R
import com.valer0ld.artistfinder.presentation.components.BouncingIconButton
import com.valer0ld.artistfinder.presentation.model.CounterIconItems
import com.valer0ld.artistfinder.presentation.theme.AppTheme
import com.valer0ld.artistfinder.presentation.viewmodel.GameBarViewModel
import kotlinx.coroutines.launch

@Composable
fun GameBottomBar(
    modifier: Modifier = Modifier,
    viewModel: GameBarViewModel = hiltViewModel(),
    onHomeClick: ()->Unit
){
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    val scope = rememberCoroutineScope()

    var isLeaving by remember {
        mutableStateOf(false)
    }

    fun cancelAndLeave(){
        if (isLeaving) return

        isLeaving = true

        scope.launch {
            try {
                viewModel.onCancelGame()
                onHomeClick()
                isLeaving = false
            } catch (e: Exception){
                isLeaving = false
            }
        }
    }

    BackHandler {
        cancelAndLeave()
    }

    Surface(
        shape = RoundedCornerShape(30.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .then(modifier)
            .fillMaxWidth()
            .height(70.dp),
        shadowElevation = 4.dp
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            BouncingIconButton(
                icon = R.drawable.close_24px,
                label = "close",
                color = MaterialTheme.colorScheme.error,
                size = 60.dp,
                modifier = Modifier.padding(horizontal = 30.dp),
                onIconClick = {
                    cancelAndLeave()
                    //viewModel.onUpdateState()
                }
            )
            Row(modifier = Modifier.padding(horizontal = 30.dp)) {

                val enabled = (state.currentReturns > 0 && state.currentStep > 0)

                Icon(
                    painter = painterResource(CounterIconItems.IconItems[state.currentReturns].image),
                    contentDescription = CounterIconItems.IconItems[state.currentReturns].title,
                    tint = if (enabled) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.outlineVariant,
                    modifier = Modifier.padding(vertical = 5.dp).size(30.dp)
                )

                BouncingIconButton(
                    icon = R.drawable.replay_24px,
                    label = "return",
                    color = if (enabled) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.outlineVariant,
                    size = 60.dp,
                    enabled = enabled,
                    onIconClick = { viewModel.onReturnClick() }
                )
            }
        }
    }
}
