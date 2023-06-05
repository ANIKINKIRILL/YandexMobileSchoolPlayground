package com.anikinkirill.playground.screen

sealed class UsersListEvent {
    object LoadUsers : UsersListEvent()

    data class UserClick(
        val name: String,
    ) : UsersListEvent()

    object ActionInvoked : UsersListEvent()
}

sealed class UsersListAction {
    data class NavigateToUserPostsScreen(
        val name: String,
    ) : UsersListAction()
}

sealed class UsersListViewState {
    object Idle : UsersListViewState()

    object Loading : UsersListViewState()

    data class Data(val users: List<UserViewObject>) : UsersListViewState()

    data class Error(
        val errorMessage: String
    ) : UsersListViewState()
}