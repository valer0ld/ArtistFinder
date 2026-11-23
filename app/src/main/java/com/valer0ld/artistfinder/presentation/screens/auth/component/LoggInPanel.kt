package com.valer0ld.artistfinder.presentation.screens.auth.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.valer0ld.artistfinder.R
import com.valer0ld.artistfinder.presentation.components.CustomTextField
import com.valer0ld.artistfinder.presentation.viewmodel.LoggInViewModel


@Composable
fun LoggInPanel(viewModel: LoggInViewModel = hiltViewModel()){
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(20.dp),
        shadowElevation = 6.dp,
        color = MaterialTheme.colorScheme.surfaceVariant
    ){
        Column(
            modifier = Modifier.padding(15.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CustomTextField(stringResource(R.string.email), state.email, viewModel::onEmailChange, Modifier)
            CustomTextField(stringResource(R.string.password), state.password, viewModel::onPasswordChange,Modifier)
            Spacer(Modifier.height(5.dp))
            Button(
                onClick = { viewModel.login() },
                shape = RoundedCornerShape(30.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 6.dp,
                    pressedElevation = 2.dp,
                ),
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = stringResource(R.string.enter),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}