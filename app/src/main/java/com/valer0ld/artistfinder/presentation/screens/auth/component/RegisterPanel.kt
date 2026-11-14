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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.valer0ld.artistfinder.presentation.components.CustomTextField
import com.valer0ld.artistfinder.presentation.viewmodel.RegisterViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds


@Composable
fun RegisterPanel(viewModel: RegisterViewModel = hiltViewModel(), onRegister: ()->Unit){
    val coroutineScope = rememberCoroutineScope()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) onRegister()
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
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
            CustomTextField("Имя пользователя",state.userName, viewModel::onUserNameChange, Modifier)
            CustomTextField("Почта", state.email, viewModel::onEmailChange,Modifier)
            CustomTextField("Пароль", state.password, viewModel::onPasswordChange,Modifier)
            CustomTextField("Повторите пароль", state.repeatPassword, viewModel::onRepeatPasswordChange,Modifier)
            Spacer(Modifier.height(20.dp))
            Button(
                onClick = { viewModel.register() },
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
                    text = "Подтвердить",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}
