package com.example.freegamesapp.model.mappers

import com.example.freegamesapp.model.response.GameDetailsRaw
import com.example.freegamesapp.model.vo.GameDetailsVO
import com.example.freegamesapp.model.vo.GameRequirementsVO

class GameDetailsMapper : Function1<GameDetailsRaw, GameDetailsVO> {
    override fun invoke(p1: GameDetailsRaw): GameDetailsVO {
        return GameDetailsVO(
            title = p1.title ?: "",
            thumbnail = p1.thumbnail ?: "",
            status = p1.status ?: "",
            shortDescription = p1.shortDescription ?: "",
            description =p1.description ?: "",
            gameUrl = p1.gameUrl ?: "",
            genre = p1.genre ?: "",
            platform = p1.platform ?: "",
            publisher = p1.publisher ?: "",
            developer = p1.developer ?: "",
            releaseDate = p1.releaseDate ?: "",
            requirements = GameRequirementsVO(
                os = p1.minimumSystemRequirementsRaw?.os ?: "",
                processor = p1.minimumSystemRequirementsRaw?.processor ?: "",
                memory = p1.minimumSystemRequirementsRaw?.memory ?: "",
                graphics = p1.minimumSystemRequirementsRaw?.graphics ?: "",
                storage = p1.minimumSystemRequirementsRaw?.storage ?: ""
            ),
            screenshots = p1.screenshots?.map { it.image ?: "" } ?: ArrayList()
        )
    }
}