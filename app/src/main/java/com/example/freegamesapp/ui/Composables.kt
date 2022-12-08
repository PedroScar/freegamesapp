package com.example.freegamesapp.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import com.example.freegamesapp.utils.conditional

@Composable
fun GameCardDefault(
    animate: Boolean = false,
    clickable: () -> Unit = {},
    content: @Composable () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.small,
        backgroundColor = MaterialTheme.colors.background,
        elevation = 4.dp,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .conditional(condition = animate) { animateContentSize() }
            .clickable { clickable.invoke() },
        content = content
    )
}

@Composable
fun GameColumnDefault(
    padding: Dp = 16.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        content = content,
        modifier = Modifier
            .padding(top = padding, bottom = padding, start = padding, end = padding)
            .fillMaxWidth()
            .wrapContentHeight(),
    )
}

@Composable
fun GameSurfaceDefault(content: @Composable () -> Unit) {
    Surface(
        color = MaterialTheme.colors.surface,
        modifier = Modifier.fillMaxSize(),
        content = content
    )
}

@Composable
fun GameRowText(label: String, text: String, topPadding: Dp = 0.dp) {
    Row(
        modifier = Modifier
            .padding(top = topPadding)
            .fillMaxWidth()
    ) {
        GameTextSubtitle(text = label)
        GameTextBody(text = text)
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun GameImage(imageUrl: String) {
    Column {
        Image(
            painter = rememberImagePainter(
                data = imageUrl,
                builder = { size(OriginalSize) }),
            contentDescription = "Game image",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
        )
    }
}

@Composable
fun GameTitle(text: String) {
    Column(modifier = Modifier.wrapContentSize()) {
        Text(
            text = text,
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.primaryVariant,
            modifier = Modifier.wrapContentSize()
        )
    }
}

@Composable
fun GameScaffold(topBar: @Composable () -> Unit, content: @Composable ColumnScope.() -> Unit = {}) {
    Scaffold(topBar = topBar) {
        Column(
            content = content,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        )
    }
}

@Composable
fun GameTextSubtitle(text: String, topPadding: Dp = 0.dp) {
    Text(
        text = text,
        style = MaterialTheme.typography.subtitle1,
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(top = topPadding)
    )
}

@Composable
fun GameTextBody(text: String, topPadding: Dp = 0.dp) {
    Text(
        text = text,
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.secondary,
        modifier = Modifier.padding(top = topPadding)
    )
}

@Composable
fun GameCardSubtitle(text: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = text,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.primaryVariant,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun DialogBoxLoading() {
    Dialog({}) {
        Surface(
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colors.background
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(start = 56.dp, end = 56.dp, top = 32.dp),
            ) {
                ProgressIndicatorLoading()
                Spacer(modifier = Modifier.height(32.dp))
                GameCardSubtitle(text = "Please wait...")
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun ProgressIndicatorLoading() {
    val infiniteTransition = rememberInfiniteTransition()

    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(animation = keyframes { durationMillis = 600 })
    )

    CircularProgressIndicator(
        progress = 1f,
        strokeWidth = 2.dp,
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .size(80.dp)
            .rotate(angle)
            .border(
                width = 12.dp,
                shape = CircleShape,
                brush = Brush.sweepGradient(
                    listOf(
                        Color.White,
                        MaterialTheme.colors.secondary.copy(alpha = 0.1f),
                        MaterialTheme.colors.secondary
                    )
                )
            ),
    )
}
