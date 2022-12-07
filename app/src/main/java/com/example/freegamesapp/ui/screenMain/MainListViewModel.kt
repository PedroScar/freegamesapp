package com.example.freegamesapp.ui.screenMain

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freegamesapp.model.repository.FreeGamesRepository
import com.example.freegamesapp.model.vo.GameVO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainListViewModel(
    private val repository: FreeGamesRepository = FreeGamesRepository.getInstance()
) : ViewModel() {

    var gamesList = emptyList<GameVO>()
    val showGames = mutableStateOf(emptyList<GameVO>())
    val showLoading = mutableStateOf(true)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            gamesList = getGamesList()
            showGames.value = gamesList
            showLoading.value = false
        }
    }

    private suspend fun getGamesList(): List<GameVO> = repository.getGamesList()

    fun filterGames(text: String) {
        showGames.value = gamesList.filter { game ->
            text.isEqual(game.genre) || text.isEqual(game.title) || text.isEqual(game.platform)
        }
    }

    private fun String.isEqual(text: String): Boolean {
        return text.lowercase().contains(this.lowercase())
    }
}

