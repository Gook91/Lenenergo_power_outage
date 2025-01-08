package com.gbl.lenenergopoweroutage.di

import com.gbl.lenenergopoweroutage.data.remote.OutageRemoteDataSource
import com.gbl.lenenergopoweroutage.data.remote.connection.InsecureConnection
import com.gbl.lenenergopoweroutage.data.remote.connection.SiteConnection
import com.gbl.lenenergopoweroutage.data.remote.mapper.JsoupDocumentMapper
import com.gbl.lenenergopoweroutage.data.remote.mapper.RemoteMappers
import org.koin.dsl.module

val remoteModule = module {
    single<SiteConnection> { InsecureConnection() }
    single<JsoupDocumentMapper> { RemoteMappers }
    single { OutageRemoteDataSource(get(), get()) }
}