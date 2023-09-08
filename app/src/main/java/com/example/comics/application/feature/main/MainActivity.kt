package com.example.comics.application.feature.main

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.comics.application.feature.descriptionScreen.DescriptionScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: MainViewModel by viewModels()

        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController,  startDestination = "main_screen"
            ) {
                composable("main_screen") {
                    MainScreen(navController, viewModel)
                }
                composable("description_screen/{itemId}") {
                    val itemId = it.arguments?.getString("itemId")
                    if (itemId != null) {
                        DescriptionScreen(itemId, viewModel)
                    }
                }
            }
        }
    }
}
