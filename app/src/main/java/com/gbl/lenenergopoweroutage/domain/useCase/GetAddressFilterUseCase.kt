package com.gbl.lenenergopoweroutage.domain.useCase

import com.gbl.lenenergopoweroutage.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class GetAddressFilterUseCase(
    private val repository: SettingsRepository
) {
    operator fun invoke(): Flow<List<String>> = repository.getAddressFilter()
}