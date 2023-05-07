package xyz.teamgravity.pagingcaching.data.remote.beer.constant

object BeerApiConst {

    const val BASE_URL = "https://api.punkapi.com/v2/"

    object GetBeers {
        const val ENDPOINT = "beers"
        const val LIMIT = 10
        const val ENABLE_PLACEHOLDERS = false

        object Query {
            const val PAGE = "page"
            const val PER_PAGE = "per_page"
        }
    }
}