package com.valer0ld.artistfinder.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.valer0ld.artistfinder.ui.theme.AppTheme

@Composable
fun SettingsScreen(navController: NavController, viewModel: SettingsViewModel = viewModel()){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(start = 10.dp, end = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        viewModel.settings.forEach { header ->

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
                        SettingItem(setting, viewModel, Modifier.animateItem())
                    }
                }
            }
        }

        item{
            Spacer(Modifier.height(100.dp))
        }
    }
}

@Composable
fun SettingsHeaderItem(headerTitle: String, headerIcon: Int?) {
    Surface(
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.primary,
        shape = RoundedCornerShape(
            topStart = 20.dp,
            topEnd = 20.dp,

        ),
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 30.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
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
                style = MaterialTheme.typography.headlineSmall,
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

