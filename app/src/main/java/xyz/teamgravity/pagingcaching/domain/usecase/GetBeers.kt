package xyz.teamgravity.pagingcaching.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import xyz.teamgravity.pagingcaching.domain.model.BeerModel
import xyz.teamgravity.pagingcaching.domain.repository.MainRepository

class GetBeers(
    private val repository: MainRepository,
) {

    ///////////////////////////////////////////////////////////////////////////
    // API
    ///////////////////////////////////////////////////////////////////////////

    operator fun invoke(): Flow<PagingData<BeerModel>> {
        return repository.getBeers()
    }
}