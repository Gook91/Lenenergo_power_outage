package com.gbl.lenenergopoweroutage.data

import com.gbl.lenenergopoweroutage.data.local.OutageLocalDataSource
import com.gbl.lenenergopoweroutage.data.remote.OutageRemoteDataSource
import com.gbl.lenenergopoweroutage.domain.model.DataState
import com.gbl.lenenergopoweroutage.domain.model.Outage
import com.gbl.lenenergopoweroutage.domain.repository.OutageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch

class OutageRepositoryImpl(
    private val remoteDataSource: OutageRemoteDataSource,
    private val localDataSource: OutageLocalDataSource,
) : OutageRepository {

    override fun getOutages(
        addresses: List<String>,
        date: Long?
    ): Flow<DataState<List<Outage>>> = channelFlow {

        // Getting cached data
        val localJob = launch {
            val localData = localDataSource.getOutages()
            send(DataState.Cached(localData))
        }

        // Getting online data
        launch {
            try {
                val remoteData = remoteDataSource.getOutages()

                // Cancel getting local job if we already have actual online data
                localJob.cancel()
                send(DataState.Online(remoteData))

                // Save online data in cache
                localDataSource.addOutages(remoteData)

            } catch (exception: Exception) {
                // Sending error if can't getting online data
                send(DataState.Error(exception))
            }
        }
    }
}
