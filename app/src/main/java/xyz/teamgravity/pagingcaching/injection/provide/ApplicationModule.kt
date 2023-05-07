package xyz.teamgravity.pagingcaching.injection.provide

import android.app.Application
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
import xyz.teamgravity.pagingcaching.data.local.beer.entitiy.BeerEntity
import xyz.teamgravity.pagingcaching.data.remote.beer.api.BeerApi
import xyz.teamgravity.pagingcaching.data.remote.beer.constant.BeerApiConst
import xyz.teamgravity.pagingcaching.data.repository.MainRepositoryImp
import xyz.teamgravity.pagingcaching.data.source.GetBeersSource
import xyz.teamgravity.pagingcaching.domain.repository.MainRepository
import xyz.teamgravity.pagingcaching.domain.usecase.GetBeers
import xyz.teamgravity.pagingcaching.injection.name.GetBeersPager
import xyz.teamgravity.pagingcaching.injection.name.GetBeersPagingConfig
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideKotlinJsonAdapterFactory(): KotlinJsonAdapterFactory = KotlinJsonAdapterFactory()

    @Provides
    @Singleton
    fun provideMoshi(jsonAdapterFactory: JsonAdapter.Factory): Moshi = Moshi.Builder()
        .addLast(jsonAdapterFactory)
        .build()

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory = MoshiConverterFactory.create(moshi)

    @Provides
    @Singleton
    fun provideBeerApi(converterFactory: Converter.Factory): BeerApi = Retrofit.Builder()
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

    @Provides
    @Singleton
    fun provideGetBeersSource(
        beerApi: BeerApi,
        beerDao: BeerDao,
    ): GetBeersSource = GetBeersSource(
        api = beerApi,
        dao = beerDao
    )

    @Provides
    @Singleton
    @GetBeersPagingConfig
    fun provideGetBeersPagingConfig(): PagingConfig = PagingConfig(
        pageSize = BeerApiConst.GetBeers.LIMIT,
        enablePlaceholders = BeerApiConst.GetBeers.ENABLE_PLACEHOLDERS
    )

    @Provides
    @Singleton
    @GetBeersPager
    fun provideGetBeersPager(
        @GetBeersPagingConfig getBeersPagingConfig: PagingConfig,
        getBeersSource: GetBeersSource,
        beersDao: BeerDao,
    ): Pager<Int, BeerEntity> = Pager(
        config = getBeersPagingConfig,
        remoteMediator = getBeersSource,
        pagingSourceFactory = { beersDao.getBeers() }
    )

    @Provides
    @Singleton
    fun provideMainRepositoryImp(
        @GetBeersPager getBeersPager: Pager<Int, BeerEntity>,
    ): MainRepositoryImp = MainRepositoryImp(getBeersPager)

    @Provides
    @Singleton
    fun provideGetBeers(mainRepository: MainRepository): GetBeers = GetBeers(mainRepository)
}