package com.valer0ld.artistfinder.ui.screens.profile_main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.valer0ld.artistfinder.R
import com.valer0ld.artistfinder.ui.theme.AppTheme

@Composable
fun ProfileMain(onGamesClick: ()->Unit, onSettingsClick: ()->Unit){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ){
        UserProfileViewer("Данил Колбасенко", R.drawable.webp_user_icon_black, 2)
        Spacer(modifier = Modifier.height(30.dp))
        OptionButton("Мои игры", R.drawable.list_24px, onGamesClick)
        Spacer(modifier = Modifier.height(20.dp))
        OptionButton("Настройки", R.drawable.settings_24px, onSettingsClick)
    }
}

@Composable
fun UserProfileViewer(userName: String, userPicture: Int, rating: Int){
    Box() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .background(MaterialTheme.colorScheme.tertiaryContainer)
                .windowInsetsPadding(WindowInsets.statusBars),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            AsyncImage(
                model = userPicture,
                contentDescription = "default profile picture",
                modifier = Modifier.size(150.dp),
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = userName,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = "#${rating} место в рейтинге",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(10.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors =
                            listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.08f)
                            )
                    )
                )
        )
    }
}

@Composable
fun OptionButton(option: String, icon: Int, onOptionChose: ()->Unit){
    Button(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth()
            .height(70.dp),
        onClick = onOptionChose,
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant

        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 6.dp,
            pressedElevation = 2.dp,
        ),
    ){
        Icon(
            painter = painterResource(icon),
            contentDescription = option,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(40.dp)

        )
        Text(
            text = option,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.weight(1f).padding(horizontal = 20.dp)
        )
    }
}

@Preview
@Composable
fun ProfileMainPreview(){
    AppTheme{
        ProfileMain({},{})
    }
}