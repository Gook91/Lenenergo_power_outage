package com.gbl.lenenergopoweroutage.domain.useCase

import com.gbl.lenenergopoweroutage.domain.repository.SettingsRepository

class AddAddressFilterUseCase(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(address: String) = repository.addAddressFilter(address)
}