package com.example.freegamesapp.model.api

import com.example.freegamesapp.model.response.GameDetailsRaw
import com.example.freegamesapp.model.response.GameRaw
import retrofit2.http.GET
import retrofit2.http.Query

interface FreeGamesApi {
    @GET("games")
    suspend fun getGamesList(): List<GameRaw>

    @GET("game")
    suspend fun getGameDetails(@Query("id") id: Int): GameDetailsRaw
}