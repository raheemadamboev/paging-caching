package xyz.teamgravity.pagingcaching.data.remote.beer.api

import retrofit2.http.GET
import retrofit2.http.Query
import xyz.teamgravity.pagingcaching.data.remote.beer.constant.BeerApiConst
import xyz.teamgravity.pagingcaching.data.remote.beer.dto.BeerDto

interface BeerApi {

    @GET
    suspend fun getBeers(
        @Query(BeerApiConst.Query.PAGE) page: Int,
        @Query(BeerApiConst.Query.PER_PAGE) perPage: Int,
    ): List<BeerDto>
}