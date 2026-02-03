package com.valer0ld.artistfinder.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.valer0ld.artistfinder.ui.navigation.BottomNavigationBar
import com.valer0ld.artistfinder.ui.navigation.NavGraph

@Composable
fun Main(navController: NavHostController = rememberNavController()) {
    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = { BottomNavigationBar(navController) }
        , containerColor = MaterialTheme.colorScheme.onBackground){ innerPadding ->
        NavGraph(navController, modifier = Modifier.padding(innerPadding))
    }
}

@Preview
@Composable
fun MainPreview(){
    AppTheme(dynamicColor = false) {
        Main()
    }
}