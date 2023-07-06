package com.anikinkirill.playground.screen.posts

import com.adeo.kviewmodel.BaseSharedViewModel
import com.anikinkirill.playground.domain.posts.GetUserPostsByIdUseCase
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserPostViewModel : BaseSharedViewModel<UserPostViewState, UserPostAction, UserPostEvent>(
    initialState = UserPostViewState.Idle
), KoinComponent {

    private val getUserPostsByIdUseCase: GetUserPostsByIdUseCase by inject()

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
                val posts = getUserPostsByIdUseCase.execute(userId = userId)
                val postsViewObject = posts.map { post ->
                    UserPostViewObject(
                        title = post.title,
                        body = post.body,
                    )
                }
                UserPostViewState.Data(posts = postsViewObject)
            } catch (error: Throwable) {
                UserPostViewState.Error(
                    errorMessage =
                    error.message ?: "Some unexpected error"
                )
            }
        }
    }
}