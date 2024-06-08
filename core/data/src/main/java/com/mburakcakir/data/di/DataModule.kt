package com.mburakcakir.data.di

import com.mburakcakir.data.datasource.TableDataSource
import com.mburakcakir.data.repository.TableRepository
import com.mburakcakir.network.di.NetworkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideLeaguesDataSource(networkApi: NetworkApi) = TableDataSource(networkApi)

    @Provides
    fun provideLeaguesRepository(dataSource: TableDataSource) = TableRepository(dataSource)
}