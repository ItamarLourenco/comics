package com.example.comics.application.feature.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.comics.application.feature.comics.ComicsDescriptionScreen
import com.example.comics.application.feature.comics.ComicsMainScreen
import com.example.comics.application.feature.comics.ComicsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val comicsViewModel: ComicsViewModel by viewModels()

        setContent {
            val navController = rememberNavController()
            SetupNavigation(navController, comicsViewModel)
        }
    }
}

@Composable
fun SetupNavigation(navController: NavHostController, comicsViewModel: ComicsViewModel) {
    NavHost(navController = navController, startDestination = "comics_main_screen") {
        composable("comics_main_screen") {
            ComicsMainScreen(
                navController = navController,
                comicsViewModel = comicsViewModel
            )
        }
        composable("comics_description_screen/{itemId}") { navBackStackEntry ->
            ComicsDescriptionScreen(
                navController=navController,
                params = navBackStackEntry.arguments,
                comicsViewModel = comicsViewModel
            )
        }
    }
}






