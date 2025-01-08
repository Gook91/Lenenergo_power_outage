package com.gbl.lenenergopoweroutage.di

import com.gbl.lenenergopoweroutage.data.OutageRepositoryImpl
import com.gbl.lenenergopoweroutage.data.SettingsRepositoryImpl
import com.gbl.lenenergopoweroutage.domain.repository.OutageRepository
import com.gbl.lenenergopoweroutage.domain.repository.SettingsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<OutageRepository> { OutageRepositoryImpl(get(), get()) }
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
}