package com.valer0ld.artistfinder.presentation.screens.settings.components

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.valer0ld.artistfinder.domain.model.Setting
import com.valer0ld.artistfinder.domain.model.SettingColor
import com.valer0ld.artistfinder.domain.model.SettingId
import com.valer0ld.artistfinder.domain.model.SettingType
import com.valer0ld.artistfinder.presentation.viewmodel.SettingsUIState
import kotlinx.coroutines.flow.filter


@Composable
fun SettingItem(setting: Setting, state:  SettingsUIState, onSettingAction: (SettingId, Any?)->Unit, modifier: Modifier = Modifier){
    when (setting.type){
        SettingType.SWITCH -> SwitchSetting(setting, state, onSettingAction, modifier)
        SettingType.BUTTON -> ButtonSetting(setting, state, onSettingAction, modifier)
        SettingType.LIST -> ListSetting(setting, state, onSettingAction, modifier)
        SettingType.TEXTFIELD -> TextFieldSetting(setting, state , onSettingAction, modifier)
    }
}

@Composable
fun SwitchSetting(setting: Setting, state:  SettingsUIState, onSettingAction: (SettingId, Any?)->Unit, modifier: Modifier = Modifier){

    Row(
        modifier = modifier.fillMaxWidth().padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
    ){
        Text(
            text = setting.title,
            color = when (setting.color){
                SettingColor.DEFAULT -> MaterialTheme.colorScheme.primary
                SettingColor.SECOND -> MaterialTheme.colorScheme.tertiary
                SettingColor.DESTRUCTIVE -> MaterialTheme.colorScheme.error
            },
            style = MaterialTheme.typography.titleLarge
        )

        Switch(
            checked = state.values[setting.Id] as? Boolean ?: false,
            onCheckedChange = { isChecked ->
                onSettingAction(setting.Id, isChecked)
            }
        )

    }
}

@Composable
fun ButtonSetting(setting: Setting, state: SettingsUIState,onSettingAction: (SettingId, Any?)->Unit, modifier: Modifier = Modifier){
    val isEditingProfile = state.isEditingProfile

    if (((setting.Id != SettingId.SAVE_PROFILE && setting.Id != SettingId.QUIT_EDITING) || isEditingProfile)
        && (setting.Id != SettingId.EDIT_PROFILE || !isEditingProfile)) {
        Button(
            modifier = modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(10.dp),
            onClick = { ( onSettingAction)(setting.Id, null) },
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 6.dp,
                pressedElevation = 2.dp,
            ),
            enabled = !(setting.Id == SettingId.EDIT_PICTURE && !isEditingProfile),
            shape = RoundedCornerShape(30.dp),
            colors = when (setting.color) {
                SettingColor.DEFAULT -> ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )

                SettingColor.SECOND -> ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                )

                SettingColor.DESTRUCTIVE -> ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                )

            }
        ) {
            Text(
                text = setting.title,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
fun TextFieldSetting(setting: Setting, state:  SettingsUIState, onSettingAction: (SettingId, Any?)->Unit, modifier: Modifier = Modifier){

    OutlinedTextField(
        value = state.values[setting.Id] as? String ?: "",
        onValueChange = { newText ->
            onSettingAction(setting.Id, newText)
        },
        textStyle = MaterialTheme.typography.titleLarge,
        colors = when (setting.color){
            SettingColor.DEFAULT -> OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedTextColor = MaterialTheme.colorScheme.outlineVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceBright,
                focusedContainerColor = MaterialTheme.colorScheme.surfaceBright
            )
            SettingColor.SECOND -> OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedTextColor = MaterialTheme.colorScheme.outlineVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceBright,
                focusedContainerColor = MaterialTheme.colorScheme.surfaceBright
            )
            SettingColor.DESTRUCTIVE -> OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.errorContainer,
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                focusedTextColor = MaterialTheme.colorScheme.error,
                unfocusedTextColor = MaterialTheme.colorScheme.outlineVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceBright,
                focusedContainerColor = MaterialTheme.colorScheme.surfaceBright
            )
        },
        modifier = modifier.fillMaxWidth().padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        enabled = state.isEditingProfile,
        placeholder = { Text(setting.title, style = MaterialTheme.typography.titleLarge) }
    )
}

@Composable
fun ListSetting(setting: Setting, state:  SettingsUIState, onSettingAction: (SettingId, Any?)->Unit, modifier: Modifier = Modifier){
    val items by remember(setting.optionList) {
        derivedStateOf {
            setting.optionList ?: listOf("")
        }
    }

    val count = Int.MAX_VALUE
    val centerOffset = count / 2

    val currentValue = state.values[setting.Id] as? String ?: items.firstOrNull().orEmpty()

    val initialFirstVisibleItemIndex = remember(items, currentValue) {
        val selectedIndex = items.indexOf(currentValue).takeIf { it >= 0 } ?: 0
        centerOffset + selectedIndex
    }

    val listState = rememberLazyListState( initialFirstVisibleItemIndex = initialFirstVisibleItemIndex)
    val flingBehavior = rememberSnapFlingBehavior(listState)

    val centerIndex by remember {
        derivedStateOf { listState.firstVisibleItemIndex + 1 }
    }

    LaunchedEffect(listState) {
        snapshotFlow {listState.isScrollInProgress}
            .filter { !it }
            .collect {
                val selected = items[(centerIndex - centerOffset).mod(items.size)]

                if (selected != currentValue) {
                    onSettingAction(setting.Id, selected)
                }
            }
    }

    LaunchedEffect(currentValue) {
        val selectedIndex = items.indexOf(currentValue)
        if (selectedIndex >= 0){
            val targetIndex = centerOffset + selectedIndex

            if (targetIndex != listState.firstVisibleItemIndex){
                listState.scrollToItem(targetIndex)
            }
        }
    }

    val itemHeight = 40.dp

    Row(
        modifier = modifier.fillMaxWidth().padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
    ) {
        Text(
            text = setting.title,
            color = when (setting.color) {
                SettingColor.DEFAULT -> MaterialTheme.colorScheme.primary
                SettingColor.SECOND -> MaterialTheme.colorScheme.tertiary
                SettingColor.DESTRUCTIVE -> MaterialTheme.colorScheme.error
            },
            style = MaterialTheme.typography.titleLarge
        )
        LazyColumn(
            state = listState,
            flingBehavior = flingBehavior,
            modifier = Modifier.height(itemHeight * 3f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            items(
                count = count,
                key = {index -> index}
            ) { index ->

                val virtualIndex = (index - centerOffset).mod(items.size)
                val isCenter = index == centerIndex
                Box(
                    modifier = Modifier
                        .height(itemHeight),
                    contentAlignment = Alignment.Center
                )
                {
                    Text(
                    text = items[virtualIndex],
                    modifier = Modifier.alpha(if (isCenter) 1f else 0.4f),
                    color = if (isCenter) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

