package com.example.interfaceexemple.presentation.main

import androidx.lifecycle.ViewModel
import com.example.interfaceexemple.analytics.facade.Analytics
import com.example.interfaceexemple.analytics.domain.events.MainAnalyticsEvents
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel(), MainUIAction {

    private val _uiState = MutableStateFlow(MainUIState())
    val uistate : StateFlow<MainUIState> = _uiState.asStateFlow()

    override fun updateNome(nome : String) {
        _uiState.value = _uiState.value.copy(nome = nome)
        Analytics(MainAnalyticsEvents.NameChanged(newName = nome))
    }

    override fun updateIdade(value: Int) {
        _uiState.value = _uiState.value.copy(idade = value)
        Analytics(MainAnalyticsEvents.IdadeChanged(idade = value))
    }
}