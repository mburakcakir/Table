package com.mburakcakir.domain.di

import com.mburakcakir.data.repository.TableRepository
import com.mburakcakir.domain.usecase.LeaguesUseCase
import com.mburakcakir.domain.usecase.SeasonsUseCase
import com.mburakcakir.domain.usecase.StandingsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideLeaguesUseCase(repository: TableRepository) = LeaguesUseCase(repository)

    @Provides
    fun provideStandingsUseCase(repository: TableRepository) = StandingsUseCase(repository)

    @Provides
    fun provideSeasonsUseCase(repository: TableRepository) = SeasonsUseCase(repository)
}