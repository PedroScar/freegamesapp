package com.example.freegamesapp.model.repository

import com.example.freegamesapp.model.api.FreeGamesWebService
import com.example.freegamesapp.model.mappers.GameDetailsMapper
import com.example.freegamesapp.model.mappers.GameMapper
import com.example.freegamesapp.model.vo.GameDetailsVO
import com.example.freegamesapp.model.vo.GameVO

class FreeGamesRepository(
    private val service: FreeGamesWebService = FreeGamesWebService()
) {
    companion object {
        @Volatile
        private var instance: FreeGamesRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: FreeGamesRepository().also { instance = it }
        }
    }

    suspend fun getGamesList(): List<GameVO> {
        val gameMapper = GameMapper()
        return service.getGamesList().map { gameRaw -> gameMapper(gameRaw) }
    }

    suspend fun getGameDetails(gameId: Int): GameDetailsVO = GameDetailsMapper().invoke(
        service.getGamesDetails(gameId)
    )
}