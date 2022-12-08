package com.example.freegamesapp.ui.screenMain

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.freegamesapp.model.vo.GameVO
import com.example.freegamesapp.ui.*
import com.example.freegamesapp.ui.theme.FreeGamesAppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainListScreen(cardClick: (Int) -> Unit) {
    val viewModel: MainListViewModel = viewModel()

    val games = viewModel.showGames.value
    val showLoading = viewModel.showLoading.value

    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    BackHandler(sheetState.isVisible) { coroutineScope.launch { sheetState.hide() } }

    GameScaffold(
        topBar = {
            GameMainAppBar(
                iconClick = {
                    coroutineScope.launch {
                        if (sheetState.isVisible) sheetState.hide() else sheetState.show()
                    }
                },
                onTextChange = { searchText ->
                    viewModel.filterGames(searchText)
                })
        }) {
        GameSurfaceDefault {
            if (!showLoading)
                LazyColumn {
                    items(games) { game ->
                        GameCard(game) { cardClick.invoke(game.id) }
                    }
                }
            else
                DialogBoxLoading()
        }
    }
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = { BottomSheet() },
        sheetShape = RoundedCornerShape(topEnd = 8.dp, topStart = 8.dp),
        sheetElevation = 8.dp,
        content = {})
}

@Composable
fun BottomSheet() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        GameCardSubtitle(text = "About")
        GameRowText(label = "Developer: ", text = "Pedro Scarpellini", topPadding = 16.dp)
        GameRowText(label = "Contact: ", text = "pedro.scarpellini@gmail.com")
        GameRowText(label = "API: ", text = "Created using the FreeToGame API")
    }
}

@Composable
fun GameMainAppBar(iconClick: () -> Unit, onTextChange: (String) -> Unit) {
    var text by rememberSaveable { mutableStateOf("") }

    TopAppBar(backgroundColor = MaterialTheme.colors.surface) {
        Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Info Icon",
                tint = MaterialTheme.colors.primaryVariant,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .align(Alignment.CenterVertically)
                    .clickable(onClick = { iconClick.invoke() })
            )
            BasicTextField(
                value = text,
                singleLine = true,
                onValueChange = {
                    text = it
                    onTextChange.invoke(it)
                },
                textStyle = MaterialTheme.typography.h6.copy(
                    color = MaterialTheme.colors.primaryVariant,
                    textAlign = TextAlign.Start
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colors.background),
                decorationBox = { innerTextField ->
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .fillMaxSize()
                    ) {
                        innerTextField()
                    }
                }
            )
        }
    }
}

@Composable
fun GameCard(game: GameVO, cardClick: (Int) -> Unit) {
    GameCardDefault(
        animate = false,
        clickable = { cardClick.invoke(game.id) }
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            GameImage(imageUrl = game.thumbnail)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = game.title,
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.primaryVariant,
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxWidth()
                )
                Column(
                    modifier = Modifier
                        .weight(1.3f)
                        .wrapContentSize()
                ) {
                    GameTextBody(text = game.genre)
                    GameTextBody(text = game.platform)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    FreeGamesAppTheme {
        GameCard(
            GameVO(
                id = 0,
                title = "String",
                thumbnail = "String",
                genre = "String",
                platform = "String",
            )
        ) { }
    }
}