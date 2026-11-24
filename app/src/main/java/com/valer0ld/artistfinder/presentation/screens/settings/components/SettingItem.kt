package com.valer0ld.artistfinder.presentation.screens.settings.components

import android.util.Log
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.valer0ld.artistfinder.R
import com.valer0ld.artistfinder.domain.model.AppLanguage
import com.valer0ld.artistfinder.domain.model.AppSettings
import com.valer0ld.artistfinder.domain.model.Setting
import com.valer0ld.artistfinder.domain.model.SettingColor
import com.valer0ld.artistfinder.domain.model.SettingId
import com.valer0ld.artistfinder.domain.model.SettingType
import com.valer0ld.artistfinder.domain.model.SettingsEnum
import com.valer0ld.artistfinder.domain.validation.error_enums.ArtistSearchError
import com.valer0ld.artistfinder.domain.validation.error_enums.ConfirmPasswordError
import com.valer0ld.artistfinder.domain.validation.error_enums.EmailError
import com.valer0ld.artistfinder.domain.validation.error_enums.PasswordError
import com.valer0ld.artistfinder.presentation.util.toMessage
import com.valer0ld.artistfinder.presentation.viewmodel.SettingsUIState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first


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
            text = stringResource(setting.title),
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
                text = stringResource(setting.title),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
fun TextFieldSetting(setting: Setting, state:  SettingsUIState, onSettingAction: (SettingId, Any?)->Unit, modifier: Modifier = Modifier){
    val error = when(setting.Id){
        SettingId.EDIT_EMAIL -> state.emailError
        SettingId.EDIT_PASSWORD -> state.passwordError
        SettingId.CONFIRM_PASSWORD -> state.confirmPasswordError
        else -> null
    }

    OutlinedTextField(
        value = state.values[setting.Id] as? String ?: "",
        onValueChange = { newText ->
            onSettingAction(setting.Id, newText)
        },
        isError = error != null,
        supportingText = {
            error?.let {
                Text(text = error.toMessage())
            }
        },
        textStyle = MaterialTheme.typography.titleLarge,
        colors = when (setting.color){
            SettingColor.DEFAULT -> OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceBright,
                focusedContainerColor = MaterialTheme.colorScheme.surfaceBright,
                errorContainerColor = MaterialTheme.colorScheme.surfaceBright
            )
            SettingColor.SECOND -> OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceBright,
                focusedContainerColor = MaterialTheme.colorScheme.surfaceBright,
                errorContainerColor = MaterialTheme.colorScheme.surfaceBright
            )
            SettingColor.DESTRUCTIVE -> OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.errorContainer,
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                focusedTextColor = MaterialTheme.colorScheme.error,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceBright,
                focusedContainerColor = MaterialTheme.colorScheme.surfaceBright,
                errorContainerColor = MaterialTheme.colorScheme.surfaceBright
            )
        },
        modifier = modifier.fillMaxWidth().padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        enabled = state.isEditingProfile,
        placeholder = {
            Text(
                stringResource(setting.title),
                style = MaterialTheme.typography.titleLarge,
                color = if (state.isEditingProfile) MaterialTheme.colorScheme.outlineVariant else MaterialTheme.colorScheme.surfaceDim
            )
        },
        singleLine = true
    )
}

@Composable
fun ListSetting(setting: Setting, state:  SettingsUIState, onSettingAction: (SettingId, Any?)->Unit, modifier: Modifier = Modifier){
    val items by remember(setting.optionList) {
        derivedStateOf {
            setting.optionList ?: listOf()
        }
    }

    val count = Int.MAX_VALUE
    val centerOffset = count / 2

    val currentValue = state.values[setting.Id] ?: items.first()

    val initialFirstVisibleItemIndex = remember(items, currentValue) {
        Log.d("currentLang", "current = $currentValue")
        val selectedIndex = items.indexOf(currentValue).coerceAtLeast(0)
        centerOffset + selectedIndex - 1
    }

    val listState = rememberLazyListState( initialFirstVisibleItemIndex = initialFirstVisibleItemIndex)
    val flingBehavior = rememberSnapFlingBehavior(listState)

    val centerIndex by remember {
        derivedStateOf { listState.firstVisibleItemIndex + 1 }
    }

    LaunchedEffect(listState, items) {
        snapshotFlow {
            listState.isScrollInProgress
        }
            .filter { it }
            .collectLatest {
                snapshotFlow {
                    listState.isScrollInProgress
                }.first { !it }

                val selectedIndex = (
                        listState.firstVisibleItemIndex + 1 - centerOffset
                        ).mod(items.size)

                onSettingAction(setting.Id, items[selectedIndex])
            }
    }

    LaunchedEffect(currentValue) {
        val selectedIndex = items.indexOf(currentValue)
        if (selectedIndex >= 0){
            val targetIndex = centerOffset + selectedIndex - 1

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
            text = stringResource(setting.title),
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
                    text = stringResource(items[virtualIndex].displaySetting),
                    modifier = Modifier.alpha(if (isCenter) 1f else 0.4f),
                    color = if (isCenter) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

