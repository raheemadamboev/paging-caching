package xyz.teamgravity.pagingcaching.data.remote.beer.api

import retrofit2.http.GET
import retrofit2.http.Query
import xyz.teamgravity.pagingcaching.data.remote.beer.constant.BeerApiConst.GetBeers
import xyz.teamgravity.pagingcaching.data.remote.beer.dto.BeerDto

interface BeerApi {

    @GET(GetBeers.ENDPOINT)
    suspend fun getBeers(
        @Query(GetBeers.Query.PAGE) page: Int,
        @Query(GetBeers.Query.PER_PAGE) perPage: Int,
    ): List<BeerDto>
}