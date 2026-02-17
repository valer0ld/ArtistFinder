package com.valer0ld.artistfinder.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier


@Composable
fun CustomTextField(placeholder: String, modifier: Modifier = Modifier){
    val message = remember { mutableStateOf("") }
    OutlinedTextField(
        value = message.value,
        onValueChange = {message.value = it},
        textStyle = MaterialTheme.typography.titleMedium,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
            unfocusedBorderColor = MaterialTheme.colorScheme.secondaryContainer,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.outlineVariant,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceBright,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceBright
        ),
        modifier = Modifier.fillMaxWidth().then(modifier),
        placeholder = {Text(placeholder, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.outlineVariant)},

    )
}
