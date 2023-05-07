package xyz.teamgravity.pagingcaching.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import xyz.teamgravity.pagingcaching.domain.model.BeerModel

interface MainRepository {

    ///////////////////////////////////////////////////////////////////////////
    // GET
    ///////////////////////////////////////////////////////////////////////////

    fun getBeers(): Flow<PagingData<BeerModel>>
}