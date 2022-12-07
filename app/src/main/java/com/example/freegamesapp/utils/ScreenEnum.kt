package com.example.freegamesapp.utils

import androidx.navigation.NavType

enum class ScreenEnum(
    val path: String,
    val argument: String,
    val type: NavType<*> = NavType.IntType
) {
    Main(path = "main", argument = ""),
    Details(path = "details", argument = "gameId")
}