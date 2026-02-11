package com.valer0ld.artistfinder.ui.screens.settings

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.valer0ld.artistfinder.ui.models.Setting
import com.valer0ld.artistfinder.ui.models.SettingColor
import com.valer0ld.artistfinder.ui.models.SettingId
import com.valer0ld.artistfinder.ui.models.SettingPreset
import com.valer0ld.artistfinder.ui.models.SettingType
import com.valer0ld.artistfinder.ui.theme.AppTheme
import kotlinx.coroutines.flow.filter


@Composable
fun SettingItem(setting: Setting, viewModel: SettingsViewModel, modifier: Modifier = Modifier){
    when (setting.type){
        SettingType.SWITCH -> SwitchSetting(setting, viewModel, modifier)
        SettingType.BUTTON -> ButtonSetting(setting, viewModel, modifier)
        SettingType.LIST -> ListSetting(setting, viewModel, modifier)
        SettingType.TEXTFIELD -> TextFieldSetting(setting, viewModel, modifier)
    }
}

@Composable
fun SwitchSetting(setting: Setting, viewModel: SettingsViewModel, modifier: Modifier = Modifier){
    val checkedState = remember{ mutableStateOf(false) }

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
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = it
                (viewModel::onSettingAction)(setting.settingId)
            }
        )

    }
}

@Composable
fun ButtonSetting(setting: Setting, viewModel: SettingsViewModel, modifier: Modifier = Modifier){
    if ((setting.settingId != SettingId.SAVE_PROFILE || viewModel.isEditingProfile)
        && (setting.settingId != SettingId.EDIT_PROFILE || !viewModel.isEditingProfile)) {
        Button(
            modifier = modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(10.dp),
            onClick = { (viewModel::onSettingAction)(setting.settingId) },
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 6.dp,
                pressedElevation = 2.dp,
            ),
            enabled = !(setting.settingId == SettingId.EDIT_PICTURE && !viewModel.isEditingProfile),
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
fun TextFieldSetting(setting: Setting, viewModel: SettingsViewModel, modifier: Modifier = Modifier){
    val message = remember { mutableStateOf("") }

    OutlinedTextField(
        value = message.value,
        onValueChange = { (viewModel::onSettingAction)(setting.settingId) },
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
        enabled = viewModel.isEditingProfile,
        placeholder = { Text(setting.title, style = MaterialTheme.typography.titleLarge) }
    )
}

@Composable
fun ListSetting(setting: Setting, viewModel: SettingsViewModel, modifier: Modifier = Modifier){
    val items by remember(setting.optionList) {
        derivedStateOf {
            setting.optionList ?: listOf("")
        }
    }

    val count = Int.MAX_VALUE
    val centerOffset = count / 2

    val listState = rememberLazyListState( initialFirstVisibleItemIndex = centerOffset)
    val flingBehavior = rememberSnapFlingBehavior(listState)

    val centerIndex by remember {
        derivedStateOf { listState.firstVisibleItemIndex + 1 }
    }

    LaunchedEffect(listState) {
        snapshotFlow {listState.isScrollInProgress}
            .filter { !it }
            .collect {
                val selected = items[(centerIndex - centerOffset).mod(items.size)]
                (viewModel::onSettingAction)(setting.settingId)
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


@Preview(showBackground = true)
@Composable
fun SettingComponentPreview(){
    AppTheme{
        SettingItem( SettingPreset.listSetting(
            settingId = SettingId.LANGUAGE,
            title = "Язык",
            list = listOf("Русский", "Английский")), viewModel())
    }
}
