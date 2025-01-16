package com.gbl.lenenergopoweroutage.domain.useCase

import com.gbl.lenenergopoweroutage.domain.model.DataState
import com.gbl.lenenergopoweroutage.domain.model.Outage
import com.gbl.lenenergopoweroutage.domain.repository.OutageRepository
import com.gbl.lenenergopoweroutage.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import java.time.Instant

class GetOutagesUseCase(
    private val outageRepository: OutageRepository,
    private val settingsRepository: SettingsRepository,
) {
    suspend operator fun invoke(): Flow<DataState<List<Outage>>> {
        val addresses = settingsRepository.getAddressFilter().first()
        val currentDate = Instant.now().epochSecond
        return outageRepository.getOutages(addresses, currentDate)
    }
}