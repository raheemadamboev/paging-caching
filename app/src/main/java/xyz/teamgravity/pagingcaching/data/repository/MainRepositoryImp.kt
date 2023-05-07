package xyz.teamgravity.pagingcaching.data.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import xyz.teamgravity.pagingcaching.data.local.beer.entitiy.BeerEntity
import xyz.teamgravity.pagingcaching.data.mapper.toModel
import xyz.teamgravity.pagingcaching.domain.model.BeerModel
import xyz.teamgravity.pagingcaching.domain.repository.MainRepository

class MainRepositoryImp(
    private val getBeersPager: Pager<Int, BeerEntity>,
) : MainRepository {

    ///////////////////////////////////////////////////////////////////////////
    // GET
    ///////////////////////////////////////////////////////////////////////////

    override fun getBeers(): Flow<PagingData<BeerModel>> {
        return getBeersPager.flow.map { entities -> entities.map { entity -> entity.toModel() } }
    }
}