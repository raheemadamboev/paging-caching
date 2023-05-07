package xyz.teamgravity.pagingcaching.data.source

import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import okio.IOException
import retrofit2.HttpException
import xyz.teamgravity.pagingcaching.data.local.beer.dao.BeerDao
import xyz.teamgravity.pagingcaching.data.local.beer.entitiy.BeerEntity
import xyz.teamgravity.pagingcaching.data.mapper.toEntity
import xyz.teamgravity.pagingcaching.data.remote.beer.api.BeerApi

class GetBeersSource(
    private val api: BeerApi,
    private val dao: BeerDao,
) : RemoteMediator<Int, BeerEntity>() {

    ///////////////////////////////////////////////////////////////////////////
    // API
    ///////////////////////////////////////////////////////////////////////////

    override suspend fun load(loadType: LoadType, state: PagingState<Int, BeerEntity>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(true)
                LoadType.APPEND -> {
                    val last = state.lastItemOrNull()
                    if (last == null) 1 else (last.id / state.config.pageSize) + 1
                }
            }

            val dtos = api.getBeers(
                page = page,
                perPage = state.config.pageSize
            )

            if (dtos.isNotEmpty()) {
                val entities = dtos.map { it.toEntity() }
                dao.insertBeers(
                    beers = entities,
                    clearCache = loadType == LoadType.REFRESH
                )
            }

            MediatorResult.Success(dtos.isEmpty())
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}