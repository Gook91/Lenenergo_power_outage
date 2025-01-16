package com.gbl.lenenergopoweroutage.di

import com.gbl.lenenergopoweroutage.domain.useCase.AddAddressFilterUseCase
import com.gbl.lenenergopoweroutage.domain.useCase.DeleteAddressFilterUseCase
import com.gbl.lenenergopoweroutage.domain.useCase.GetAddressFilterUseCase
import com.gbl.lenenergopoweroutage.domain.useCase.GetOutagesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { AddAddressFilterUseCase(get()) }
    single { DeleteAddressFilterUseCase(get()) }
    single { GetAddressFilterUseCase(get()) }

    single { GetOutagesUseCase(get(), get()) }
}