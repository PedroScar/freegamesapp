package com.example.freegamesapp.model.vo

data class GameDetailsVO(
    val title: String,
    val thumbnail: String,
    val status: String,
    val shortDescription: String,
    val description: String,
    val gameUrl: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val developer: String,
    val releaseDate: String,
    val requirements: GameRequirementsVO,
    val screenshots: List<String> = listOf()
)
