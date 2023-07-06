package com.anikinkirill.playground.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import com.adeo.kviewmodel.odyssey.setupWithViewModels
import com.anikinkirill.playground.MainRes
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun UserListScreen() {
    val rootController = LocalRootController.current
    rootController.setupWithViewModels()

    Scaffold(
        modifier = Modifier.fillMaxSize().background(color = Color.White),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = MainRes.string.user_list)
                }
            )
        }
    ) {
        StoredViewModel(factory = { UsersListViewModel() }) { viewModel ->

            val viewState = viewModel.viewStates().observeAsState()
            val viewAction = viewModel.viewActions().observeAsState()
            viewAction.value?.let { action ->
                when (action) {
                    is UsersListAction.NavigateToUserPostsScreen -> {
                        rootController.push(screen = "user_post", params = action.id)
                        viewModel.obtainEvent(UsersListEvent.ActionInvoked)
                    }
                }
            }

            LaunchedEffect(Unit) {
                viewModel.obtainEvent(viewEvent = UsersListEvent.LoadUsers)
            }

            when (val state = viewState.value) {
                is UsersListViewState.Data -> DataState(state.users) { event ->
                    viewModel.obtainEvent(viewEvent = event)
                }
                is UsersListViewState.Error -> ErrorState(state.errorMessage)
                is UsersListViewState.Idle -> IdleState()
                is UsersListViewState.Loading -> LoadingState()
            }
        }
    }
}

@Composable
private fun DataState(
    users: List<UserViewObject>,
    event: (UsersListEvent) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        itemsIndexed(items = users) { index, user ->
            val topPadding = if (index == 0) 16.dp else 0.dp
            val bottomPadding = if (index == users.lastIndex) 16.dp else 0.dp
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = topPadding, bottom = bottomPadding)
                    .padding(horizontal = 16.dp),
            ) {
                Text(
                    text = user.name,
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp)
                        .clickable {
                            event.invoke(UsersListEvent.UserClick(id = user.id))
                        },
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
private fun ErrorState(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = message)
    }
}

@Composable
private fun IdleState() {

}

@Composable
private fun LoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}