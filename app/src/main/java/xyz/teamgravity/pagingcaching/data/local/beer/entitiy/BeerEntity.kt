package xyz.teamgravity.pagingcaching.data.local.beer.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey
import xyz.teamgravity.pagingcaching.data.local.beer.constant.BeerDatabaseConst

@Entity(tableName = BeerDatabaseConst.TABLE_BEER)
data class BeerEntity(

    @PrimaryKey
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val firstBrewed: String,
    val imageUrl: String,
)
