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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.valer0ld.artistfinder.presentation.components.CustomTextField


@Composable
fun LoggInPanel(
    onLoggIn: ()->Unit
){
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
            CustomTextField("Почта", "", {}, Modifier)
            CustomTextField("Пароль", "", {},Modifier)
            Spacer(Modifier.height(5.dp))
            Button(
                onClick = onLoggIn,
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
                    text = "Войти",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}