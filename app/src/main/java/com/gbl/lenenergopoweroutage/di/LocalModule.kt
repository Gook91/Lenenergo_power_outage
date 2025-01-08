package com.gbl.lenenergopoweroutage.di

import com.gbl.lenenergopoweroutage.data.local.AddressFilterLocalDataSource
import com.gbl.lenenergopoweroutage.data.local.OutageLocalDataSource
import com.gbl.lenenergopoweroutage.data.local.db.AppDatabase
import org.koin.dsl.module

val localModule = module {
    single { AppDatabase.getInstance(get()) }

    single { get<AppDatabase>().outageDao() }
    single { OutageLocalDataSource(get()) }

    single { get<AppDatabase>().addressFilterDao() }
    single { AddressFilterLocalDataSource(get()) }
}