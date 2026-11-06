package com.valer0ld.artistfinder.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun CustomTextField(placeholder: String, message: String, onValueChanged: (String)->Unit, modifier: Modifier = Modifier){
    OutlinedTextField(
        value = message,
        onValueChange = { newText ->
            onValueChanged(newText)
        },
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
