package com.anikinkirill.playground.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import com.anikinkirill.playground.MainRes

@Composable
fun UserListScreen() {
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
                        // TODO навигация на экран со списком постов пользователя
                        viewModel.obtainEvent(UsersListEvent.ActionInvoked)
                    }
                }
            }

            when (val state = viewState.value) {
                is UsersListViewState.Data -> DataState(state.users)
                is UsersListViewState.Error -> ErrorState(state.errorMessage)
                is UsersListViewState.Idle -> IdleState()
                is UsersListViewState.Loading -> LoadingState()
            }
        }
    }
}

@Composable
private fun DataState(users: List<String>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        this.items(items = users) { user ->
            Text(
                text = user,
            )
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