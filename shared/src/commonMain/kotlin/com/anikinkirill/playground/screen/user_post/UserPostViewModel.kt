package com.anikinkirill.playground.screen.user_post

import com.adeo.kviewmodel.BaseSharedViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserPostViewModel : BaseSharedViewModel<UserPostViewState, UserPostAction, UserPostEvent>(
    initialState = UserPostViewState.Idle
), KoinComponent {

    override fun obtainEvent(viewEvent: UserPostEvent) {
        when (viewEvent) {
            is UserPostEvent.ActionInvoked -> viewAction = null
            is UserPostEvent.LoadPosts -> loadPosts(viewEvent.userId)
        }
    }

    private fun loadPosts(userId: Int) {
        viewModelScope.launch {
            viewState = UserPostViewState.Loading
            viewState = try {
                // загрузка постов
                UserPostViewState.Data(posts = emptyList())
            } catch (error: Throwable) {
                UserPostViewState.Error(
                    errorMessage =
                    error.message ?: "Some unexpected error"
                )
            }
        }
    }
}