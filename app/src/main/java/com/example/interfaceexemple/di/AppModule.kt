package com.example.interfaceexemple.di

import com.example.interfaceexemple.presentation.main.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel{
        MainViewModel()
    }

}