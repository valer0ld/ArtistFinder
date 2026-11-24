package com.valer0ld.artistfinder.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.valer0ld.artistfinder.R
import com.valer0ld.artistfinder.domain.validation.error_enums.ArtistSearchError
import com.valer0ld.artistfinder.domain.validation.error_enums.ConfirmPasswordError
import com.valer0ld.artistfinder.domain.validation.error_enums.EmailError
import com.valer0ld.artistfinder.domain.validation.error_enums.ErrorEnums
import com.valer0ld.artistfinder.domain.validation.error_enums.PasswordError
import com.valer0ld.artistfinder.presentation.util.toMessage


@Composable
fun CustomTextField(
    placeholder: String,
    message: String,
    error: ErrorEnums?,
    onValueChanged: (String)->Unit,
    modifier: Modifier = Modifier
){

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        if (error != null) {
            Text(
                text = error.toMessage(),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(
                    start = 12.dp,
                    bottom = 4.dp
                )
            )
        }


        OutlinedTextField(
            value = message,
            onValueChange = { newText ->
                onValueChanged(newText)
            },
            isError = error != null,
            textStyle = MaterialTheme.typography.titleLarge,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                unfocusedBorderColor = MaterialTheme.colorScheme.secondaryContainer,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceBright,
                focusedContainerColor = MaterialTheme.colorScheme.surfaceBright,
                errorContainerColor = MaterialTheme.colorScheme.surfaceBright
            ),
            modifier = Modifier.fillMaxWidth().then(modifier),
            placeholder = {
                Text(
                    placeholder,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.outlineVariant
                )
            },
            singleLine = true
        )
    }
}
