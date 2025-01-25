package com.example.singup_app.di.modules

import com.example.singup_app.presentation.ViewModels.CreateNoteFragmentViewModel
import com.example.singup_app.presentation.ViewModels.LoginFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val crNFModule = module {
    viewModel{CreateNoteFragmentViewModel()}
    viewModel{ LoginFragmentViewModel() }
}