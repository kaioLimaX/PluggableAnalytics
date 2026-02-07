package com.example.interfaceexemple

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.interfaceexemple.presentation.main.MainScreen
import com.example.interfaceexemple.presentation.main.MainUIAction
import com.example.interfaceexemple.presentation.main.MainUIState
import com.example.interfaceexemple.presentation.main.MainViewModel
import com.example.interfaceexemple.ui.theme.InterfaceExempleTheme

class MainActivity : ComponentActivity() {

    val viewModel : MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InterfaceExempleTheme {
                MainScreen(viewModel = viewModel)
            }
        }
    }
}





