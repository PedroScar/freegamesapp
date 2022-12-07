package com.example.freegamesapp.ui.screenDetails

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freegamesapp.model.mappers.GameDetailsMapper
import com.example.freegamesapp.model.repository.FreeGamesRepository
import com.example.freegamesapp.model.response.GameDetailsRaw
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val repository: FreeGamesRepository = FreeGamesRepository.getInstance()
) : ViewModel() {

    val gameDetails = mutableStateOf(GameDetailsMapper().invoke(GameDetailsRaw()))
    val showLoading = mutableStateOf(true)

    fun getGameDetails(gameId: Int) = viewModelScope.launch(Dispatchers.IO) {
        gameDetails.value = repository.getGameDetails(gameId)
        showLoading.value = false
    }
}