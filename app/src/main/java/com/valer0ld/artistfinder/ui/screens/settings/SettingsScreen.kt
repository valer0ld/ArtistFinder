package com.valer0ld.artistfinder.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.valer0ld.artistfinder.R
import com.valer0ld.artistfinder.ui.components.ReturnActionHeader
import com.valer0ld.artistfinder.ui.theme.AppTheme

@Composable
fun SettingsScreen(navController: NavController, viewModel: SettingsViewModel = viewModel()){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .windowInsetsPadding(WindowInsets.statusBars),
        contentPadding = PaddingValues(start = 10.dp, end = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        stickyHeader {
            ReturnActionHeader(R.drawable.arrow_back_24px, "Return arrow") {
                navController.navigate("profile/main") {
                    popUpTo("profile") {
                        inclusive = false
                    }
                    restoreState = true
                }
            }
        }

        viewModel.settings.forEach { header ->

            stickyHeader (key = header.title){
                SettingsHeaderItem(header.title, header.icon)
            }

            item{ Spacer(Modifier.height(10.dp)) }

            item (key = header.title + "_content"){
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    header.settingList.forEach { setting ->
                        SettingItem(setting, viewModel, Modifier.animateItem())
                    }
                }
            }
        }
    }
}

@Composable
fun SettingsHeaderItem(headerTitle: String, headerIcon: Int?) {
    Surface(
        tonalElevation = 4.dp,
        color = MaterialTheme.colorScheme.primary,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background) // надо исправить без костылей
            .padding(top = 30.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            if (headerIcon != null){
                Icon(
                    painter = painterResource(headerIcon),
                    contentDescription = "route",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(40.dp)
                )
            }
            Text(
                text = headerTitle,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.weight(1f).padding(start = 10.dp)
            )
        }
    }
}


@Preview
@Composable
fun SettingScreenPreview(){
    AppTheme{
        SettingsScreen(rememberNavController(), viewModel())
    }
}

