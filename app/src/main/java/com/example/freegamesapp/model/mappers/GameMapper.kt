package com.example.freegamesapp.model.mappers

import com.example.freegamesapp.model.response.GameRaw
import com.example.freegamesapp.model.vo.GameVO

class GameMapper : Function1<GameRaw, GameVO> {
    override fun invoke(p1: GameRaw): GameVO {
        return GameVO(
            id = p1.id ?: 0,
            title = p1.title ?: "",
            thumbnail = p1.thumbnail ?: "",
            genre = p1.genre ?: "",
            platform = p1.platform ?: "",
        )
    }
}