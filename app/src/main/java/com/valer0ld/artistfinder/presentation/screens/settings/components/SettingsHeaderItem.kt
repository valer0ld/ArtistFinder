package com.valer0ld.artistfinder.presentation.screens.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


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