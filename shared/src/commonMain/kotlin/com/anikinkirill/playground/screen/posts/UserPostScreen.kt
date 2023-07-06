package com.anikinkirill.playground.screen.posts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import com.anikinkirill.playground.MainRes
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun UserPostScreen(userId: Int) {
    val rootController = LocalRootController.current

    StoredViewModel(factory = { UserPostViewModel() }) { viewModel ->
        val viewState = viewModel.viewStates().observeAsState()
        val viewAction = viewModel.viewActions().observeAsState()
        LaunchedEffect(Unit) {
            viewModel.obtainEvent(viewEvent = UserPostEvent.LoadPosts(userId = userId))
        }
        Scaffold(
            modifier = Modifier.fillMaxSize().background(color = Color.White),
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = MainRes.string.user_post.format(userId.toString()))
                    },
                    navigationIcon = {
                        IconButton(onClick = { rootController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back button",
                            )
                        }
                    }
                )
            }
        ) {
            when (val state = viewState.value) {
                is UserPostViewState.Data -> DataState(state.posts)
                is UserPostViewState.Error -> ErrorState(state.errorMessage)
                is UserPostViewState.Idle -> {}
                is UserPostViewState.Loading -> LoadingState()
            }
        }
    }
}

@Composable
private fun LoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun DataState(
    posts: List<UserPostViewObject>,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        itemsIndexed(items = posts) { index, post ->
            val topPadding = if (index == 0) 16.dp else 0.dp
            val bottomPadding = if (index == posts.lastIndex) 16.dp else 0.dp
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = topPadding, bottom = bottomPadding)
                    .padding(horizontal = 16.dp),
            ) {
                Column {
                    val title = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                        ) {
                            append(text = "Title: ")
                        }
                        append(text = post.title)
                    }
                    Text(
                        text = title,
                        modifier = Modifier.padding(start = 4.dp, top = 10.dp, bottom = 10.dp),
                        textAlign = TextAlign.Start,
                    )
                    Divider(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.LightGray,
                        thickness = 1.dp
                    )
                    Text(
                        text = post.body,
                        modifier = Modifier.padding(
                            start = 4.dp,
                            top = 10.dp,
                            bottom = 10.dp,
                            end = 4.dp
                        ),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@Composable
private fun ErrorState(
    message: String,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = message)
    }
}