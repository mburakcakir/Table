package com.mburakcakir.network.di

import com.mburakcakir.network.model.LeaguesModel
import com.mburakcakir.network.model.SeasonsModel
import com.mburakcakir.network.model.StandingsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkApi {

    @GET("leagues")
    suspend fun getLeagues(): Response<LeaguesModel>

    @GET("/leagues/{league}/standings")
    suspend fun getStandings(
        @Path("league") league: String,
        @Query("season") season: Int,
        @Query("sort") sort: String
    ): Response<StandingsModel>

    @GET("/leagues/{league}/seasons")
    suspend fun getSeasons(
        @Path("league") league: String
    ): Response<SeasonsModel>
}