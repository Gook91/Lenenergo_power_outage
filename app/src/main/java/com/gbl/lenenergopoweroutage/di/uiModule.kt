package com.gbl.lenenergopoweroutage.di

import com.gbl.lenenergopoweroutage.ui.screen.main.ActivityViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { ActivityViewModel(get(), get(), get(), get()) }
}