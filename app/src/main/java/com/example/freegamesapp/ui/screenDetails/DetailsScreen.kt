package com.example.freegamesapp.ui.screenDetails

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import com.example.freegamesapp.model.vo.GameDetailsVO
import com.example.freegamesapp.ui.*

@Composable
fun DetailsScreen(gameId: Int, navController: NavHostController) {
    val viewModel: DetailsViewModel = viewModel()
    viewModel.getGameDetails(gameId)

    val game = viewModel.gameDetails.value
    val showLoading = viewModel.showLoading.value

    GameScaffold(topBar = { GameDetailsAppBar(game.title) { navController.navigateUp() } }) {
        GameSurfaceDefault {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                if (!showLoading) {
                    GameDescriptionCard(game = game)
                    GameDetailsCard(game = game)
                    GameScreenshotsCard(images = game.screenshots)
                    GameSpecsCard(game = game)
                } else
                    DialogBoxLoading()
            }
        }
    }
}


@Composable
fun GameDetailsAppBar(title: String, iconClickAction: () -> Unit) {
    TopAppBar(
        title = { GameTitle(shouldAlign = false, text = title) },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 4.dp,
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back icon",
                tint = MaterialTheme.colors.primaryVariant,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .clickable(onClick = { iconClickAction.invoke() })
            )
        }
    )
}

@Composable
fun GameDescriptionCard(game: GameDetailsVO) {
    var isExpanded by remember { mutableStateOf(false) }

    GameCardDefault(animate = true, clickable = { isExpanded = !isExpanded }) {
        GameColumnDefault {
            GameImage(imageUrl = game.thumbnail)
            GameTextBody(
                topPadding = 8.dp,
                text = if (isExpanded)
                    game.description
                else
                    game.shortDescription
            )
            Icon(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .align(Alignment.CenterHorizontally),
                contentDescription = "Expand row icon",
                imageVector = if (isExpanded)
                    Icons.Filled.KeyboardArrowUp
                else
                    Icons.Filled.KeyboardArrowDown,
            )
        }
    }
}

@Composable
fun GameDetailsCard(game: GameDetailsVO) {
    GameCardDefault(animate = true) {
        GameColumnDefault {
            GameCardSubtitle(text = "Details")
            GameRowText(label = "Title: ", text = game.title)
            GameRowText(label = "Genre: ", text = game.genre)
            GameRowText(label = "Platform: ", text = game.platform)
            GameRowText(label = "Developer: ", text = game.developer)
            GameRowText(label = "Publisher: ", text = game.publisher)
            GameRowText(label = "Release date: ", text = game.releaseDate)
        }
    }
}

@Composable
fun GameSpecsCard(game: GameDetailsVO) {
    GameCardDefault(animate = true) {
        GameColumnDefault {
            GameCardSubtitle(text = "Minimum Requirements")
            GameRowText(label = "OS: ", text = game.requirements.os, topPadding = 8.dp)
            GameRowText(label = "Processor: ", text = game.requirements.processor)
            GameRowText(label = "Memory: ", text = game.requirements.memory)
            GameRowText(label = "Graphics: ", text = game.requirements.graphics)
            GameRowText(label = "Storage: ", text = game.requirements.storage)
        }
    }
}

@Composable
fun GameScreenshotsCard(images: List<String>) {
    GameCardDefault(animate = true) {
        GameColumnDefault(horizontalPadding = 0.dp) {
            GameCardSubtitle(text = "Screenshots")
            LazyRow {
                items(images) { image ->
                    GameScreenshot(image)
                }
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun GameScreenshot(image: String) {
    Image(
        painter = rememberImagePainter(
            data = image,
            builder = { size(OriginalSize) }),
        contentDescription = "Game image",
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .padding(top = 16.dp, start = 8.dp, end = 8.dp)
            .wrapContentHeight()
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .border(
                width = 2.dp,
                color = MaterialTheme.colors.onSurface,
                shape = MaterialTheme.shapes.large
            )
    )
}