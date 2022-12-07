package com.example.freegamesapp.model.api

import com.example.freegamesapp.model.response.GameDetailsRaw
import com.example.freegamesapp.model.response.GameRaw
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class FreeGamesWebService {
    private var api: FreeGamesApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.freetogame.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(createHttpClient())
            .build()

        api = retrofit.create(FreeGamesApi::class.java)
    }

    private fun createHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()

        interceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(interceptor)

        httpClient.addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val request: Request = chain.request().newBuilder()
                    .addHeader(
                        "X-RapidAPI-Key",
                        "652d9d1cdfmsh5de4b0fcf7e16dcp1e6e6ajsn7c9b48a113ba"
                    )
                    .addHeader("X-RapidAPI-Host", "free-to-play-games-database.p.rapidapi.com")
                    .build()
                return chain.proceed(request)
            }
        })

        return httpClient.build()
    }

    suspend fun getGamesList(): List<GameRaw> = api.getGamesList()

    suspend fun getGamesDetails(gameId: Int): GameDetailsRaw = api.getGameDetails(gameId)
}