package com.valer0ld.artistfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.pager.HorizontalPager
import androidx.wear.compose.material3.IconButton
import com.example.compose.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                AppPreview()
            }
        }
    }
}

@Preview()
@Composable
fun AppPreview() {
    val artists = arrayOf("Воскресенье", "Земфира", "Аквариум", "ДДТ", "ЧИЖ")
    val currentArtist = remember { mutableStateOf(artists[0]) }
    AppTheme {
        val typography = MaterialTheme.typography
        val colorScheme = MaterialTheme.colorScheme
        Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = { MyBottomAppBar(MaterialTheme) },
            contentColor = colorScheme.tertiary, containerColor = colorScheme.onBackground){ innerPadding ->
            Box(Modifier.fillMaxSize().padding(innerPadding).
                background(colorScheme.onBackground), contentAlignment = Alignment.Center){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(text = currentArtist.value, style = typography.displayMedium,
                        color = colorScheme.background)
                    Box(contentAlignment = Alignment.Center) {
                        Column() {
                            var t = 0
                            for (i in 0..1) {
                                Row(Modifier.padding(start = 6.dp, end = 6.dp)) {
                                    for (y in 0..1) {
                                        if (artists[t] == currentArtist.value) t++
                                        val tmp = artists[t]
                                        Box(
                                            Modifier
                                                .size(200.dp)
                                                .padding(2.dp)
                                                .clip(shape = RoundedCornerShape(10.dp))
                                                .background(colorScheme.primary)
                                                .clickable {
                                                    currentArtist.value = tmp
                                                },
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(text = artists[t], style = typography.headlineMedium,
                                                    color = colorScheme.background)
                                            t++
                                        }

                                    }
                                }
                            }
                        }
                        Box(
                            Modifier
                                .size(130.dp)
                                .clip(shape = RoundedCornerShape(60.dp))
                                .background(colorScheme.tertiary)
                                .clickable{},
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Кнопка", style = typography.headlineSmall,
                                color = colorScheme.background)
                        }

                    }
                }
            }
        }
    }
}

@Composable
internal fun MyBottomAppBar(theme: MaterialTheme){
    BottomAppBar(containerColor = theme.colorScheme.onBackground, modifier = Modifier.height(70.dp).padding(bottom = 4.dp)
        .border(1.dp, color = theme.colorScheme.background, shape = RoundedCornerShape(10.dp))) {
        IconButton(onClick = {}, Modifier.weight(1f).padding(15.dp)){
            Icon(painter = painterResource(R.drawable.joystick_24px),
                modifier = Modifier.fillMaxSize(), contentDescription = null, tint = theme.colorScheme.background)
        }
        VerticalDivider(thickness = 2.dp)
        IconButton(onClick = {}, Modifier.weight(1f).padding(15.dp)){
            Icon(painter = painterResource(R.drawable.home_24px),
                modifier = Modifier.fillMaxSize(), contentDescription = null, tint = theme.colorScheme.background)
        }
        VerticalDivider(thickness = 2.dp)
        IconButton(onClick = {}, Modifier.weight(1f).padding(15.dp)){
            Icon(painter = painterResource(R.drawable.trophy_24px),
                modifier = Modifier.fillMaxSize(), contentDescription = null, tint = theme.colorScheme.background)
        }
    }
}