package com.example.abal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.abal.presentation.HomeScreen
import com.example.abal.presentation.HomeViewModel
import com.example.abal.ui.theme.ABalTheme
import com.example.lcore.domain.usescases.CalculateDistanceUseCase

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            //TODO: This should be injected with DI
            val viewModel = HomeViewModel(CalculateDistanceUseCase())

            ABalTheme {
                HomeScreen(viewModel = viewModel)
            }
        }
    }
}


