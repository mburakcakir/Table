package com.mburakcakir.network.di

import com.mburakcakir.model.leagues.LeaguesModel
import com.mburakcakir.model.standings.SeasonsModel
import com.mburakcakir.model.standings.StandingsModel
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