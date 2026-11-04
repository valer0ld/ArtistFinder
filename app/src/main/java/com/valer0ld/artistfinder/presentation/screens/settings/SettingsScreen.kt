package com.valer0ld.artistfinder.presentation.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.valer0ld.artistfinder.domain.model.AppSettings
import com.valer0ld.artistfinder.presentation.components.ChildrenScreen
import com.valer0ld.artistfinder.presentation.screens.settings.components.SettingItem
import com.valer0ld.artistfinder.presentation.screens.settings.components.SettingsHeaderItem
import com.valer0ld.artistfinder.presentation.theme.AppTheme
import com.valer0ld.artistfinder.presentation.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel(), onBackArrow: ()->Unit){
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current

    ChildrenScreen(onBackArrow) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit){
                    detectTapGestures {
                        focusManager.clearFocus()
                    }
                },
            contentPadding = PaddingValues(start = 10.dp, end = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            AppSettings.list.forEach { header ->

                stickyHeader (key = header.title){
                    SettingsHeaderItem(header.title, header.icon)
                }

                item (key = header.title + "_content"){
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(
                                bottomStart = 20.dp,
                                bottomEnd = 20.dp,
                            ))
                            .background(MaterialTheme.colorScheme.surfaceVariant),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        header.settingList.forEach { setting ->
                            SettingItem(setting, state, viewModel::onSettingAction, Modifier.animateItem())
                        }
                    }
                }
            }
            item{
                Spacer(Modifier.height(100.dp))
            }
        }
    }
}

@Preview
@Composable
fun SettingScreenPreview(){
    AppTheme{
        SettingsScreen(viewModel(), {})
    }
}

