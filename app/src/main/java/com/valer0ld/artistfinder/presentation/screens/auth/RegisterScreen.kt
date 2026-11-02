package com.valer0ld.artistfinder.presentation.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valer0ld.artistfinder.presentation.components.ChildrenScreen
import com.valer0ld.artistfinder.presentation.screens.auth.component.RegisterPanel
import com.valer0ld.artistfinder.presentation.theme.AppTheme
import com.valer0ld.artistfinder.presentation.viewmodel.AuthViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(authViewModel: AuthViewModel, onBackArrowClick: ()->Unit){
    val focusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope()

    ChildrenScreen(
        onBackArrow = onBackArrowClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .pointerInput(Unit){
                    detectTapGestures {
                        focusManager.clearFocus()
                    }
                },
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Spacer(Modifier.height(60.dp))
            Text(
                text = "Регистрация",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(Modifier.height(20.dp))

            RegisterPanel {
                authViewModel.login("1","1")
                coroutineScope.launch {
                    delay(200)
                    onBackArrowClick()
                }
            }

        }
    }
}

@Composable
@Preview
fun RegisterScreenPreview(){
    AppTheme{
        ChildrenScreen(
            onBackArrow = {}
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Spacer(Modifier.height(60.dp))
                Text(
                    text = "Регистрация",
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.primary,
                )
                Spacer(Modifier.height(20.dp))

               RegisterPanel {  }

            }
        }
    }

}