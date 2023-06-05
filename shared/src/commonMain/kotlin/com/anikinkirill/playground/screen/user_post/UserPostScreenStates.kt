package com.anikinkirill.playground.screen.user_post

sealed class UserPostViewState {
    object Idle : UserPostViewState()

    object Loading : UserPostViewState()

    data class Data(val posts: List<UserPostViewObject>) : UserPostViewState()

    data class Error(val errorMessage: String) : UserPostViewState()
}

sealed class UserPostEvent {
    data class LoadPosts(val userId: Int): UserPostEvent()

    object ActionInvoked : UserPostEvent()
}

sealed class UserPostAction {
    object None : UserPostAction()
}