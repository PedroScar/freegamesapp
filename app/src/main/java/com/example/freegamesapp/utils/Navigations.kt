package com.example.freegamesapp.utils

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.freegamesapp.ui.screenDetails.DetailsScreen
import com.example.freegamesapp.ui.screenMain.MainListScreen

@Composable
fun FreeGamesAppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = ScreenEnum.Main.path) {
        composable(
            route = ScreenEnum.Main.path
        ) {
            MainListScreen { gameId ->
                navController.navigate("${ScreenEnum.Details.path}/$gameId")
            }
        }
        composable(
            route = "${ScreenEnum.Details.path}/{${ScreenEnum.Details.argument}}",
            arguments = listOf(
                navArgument(
                    name = ScreenEnum.Details.argument,
                    builder = { type = ScreenEnum.Details.type })
            )
        ) { stackEntry ->
            DetailsScreen(stackEntry.arguments!!.getInt(ScreenEnum.Details.argument), navController)
        }
    }
}