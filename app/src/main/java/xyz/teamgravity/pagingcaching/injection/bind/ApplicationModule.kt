package xyz.teamgravity.pagingcaching.injection.bind

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Converter
import retrofit2.converter.moshi.MoshiConverterFactory
import xyz.teamgravity.pagingcaching.data.repository.MainRepositoryImp
import xyz.teamgravity.pagingcaching.domain.repository.MainRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApplicationModule {

    @Binds
    @Singleton
    abstract fun bindJsonAdapterFactory(kotlinJsonAdapterFactory: KotlinJsonAdapterFactory): JsonAdapter.Factory

    @Binds
    @Singleton
    abstract fun bindConverterFactory(moshiConverterFactory: MoshiConverterFactory): Converter.Factory

    @Binds
    @Singleton
    abstract fun bindMainRepository(mainRepositoryImp: MainRepositoryImp): MainRepository
}