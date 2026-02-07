package com.example.interfaceexemple.presentation.main

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

@Composable
fun MainScreen(viewModel: MainViewModel){
    val uistate = viewModel.uistate.collectAsState()
    MainContent(uistate = uistate.value, action = viewModel)

}

@Composable
fun MainContent(uistate: MainUIState, action: MainUIAction) {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()
            .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Nome: ${uistate.nome}")
            Text(text = "Idade: ${uistate.idade}")
            Button(
                onClick = { action.updateNome("João") }
            ) {
                Text(text = "alterar nome")
            }
            Button(
                modifier = Modifier.padding(top = 5.dp),
                onClick = { action.updateIdade(2) }
            ) {
                Text(text = "alterar idade")
            }


        }
    }
}