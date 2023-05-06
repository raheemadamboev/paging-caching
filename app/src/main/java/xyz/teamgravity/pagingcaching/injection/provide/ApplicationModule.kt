package xyz.teamgravity.pagingcaching.injection.provide

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import xyz.teamgravity.pagingcaching.data.local.beer.constant.BeerDatabaseConst
import xyz.teamgravity.pagingcaching.data.local.beer.dao.BeerDao
import xyz.teamgravity.pagingcaching.data.local.beer.database.BeerDatabase
import xyz.teamgravity.pagingcaching.data.remote.beer.api.BeerApi
import xyz.teamgravity.pagingcaching.data.remote.beer.constant.BeerApiConst
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(): MoshiConverterFactory = MoshiConverterFactory.create()

    @Provides
    @Singleton
    fun provideBeerApi(
        converterFactory: Converter.Factory,
    ): BeerApi = Retrofit.Builder()
        .baseUrl(BeerApiConst.BASE_URL)
        .addConverterFactory(converterFactory)
        .build()
        .create()

    @Provides
    @Singleton
    fun provideBeerDatabase(application: Application): BeerDatabase = Room.databaseBuilder(
        context = application,
        klass = BeerDatabase::class.java,
        name = BeerDatabaseConst.NAME
    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideBeerDao(beerDatabase: BeerDatabase): BeerDao = beerDatabase.beerDao()
}