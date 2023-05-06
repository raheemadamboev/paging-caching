package xyz.teamgravity.pagingcaching.data.local.beer.database

import androidx.room.Database
import androidx.room.RoomDatabase
import xyz.teamgravity.pagingcaching.data.local.beer.constant.BeerDatabaseConst
import xyz.teamgravity.pagingcaching.data.local.beer.dao.BeerDao
import xyz.teamgravity.pagingcaching.data.local.beer.entitiy.BeerEntity

@Database(
    version = BeerDatabaseConst.VERSION,
    entities = [BeerEntity::class],
    exportSchema = false
)
abstract class BeerDatabase : RoomDatabase() {

    abstract fun beerDao(): BeerDao
}