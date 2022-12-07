package com.example.freegamesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.freegamesapp.ui.theme.FreeGamesAppTheme
import com.example.freegamesapp.utils.FreeGamesAppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { FreeGamesAppTheme { FreeGamesAppNavigation() } }
    }
}