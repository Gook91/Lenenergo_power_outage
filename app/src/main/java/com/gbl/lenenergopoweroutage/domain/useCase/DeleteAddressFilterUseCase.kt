package com.gbl.lenenergopoweroutage.domain.useCase

import com.gbl.lenenergopoweroutage.domain.repository.SettingsRepository

class DeleteAddressFilterUseCase(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(address: String) = repository.deleteAddressFilter(address)
}