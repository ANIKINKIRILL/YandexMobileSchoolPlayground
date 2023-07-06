package com.anikinkirill.playground.screen

import com.adeo.kviewmodel.BaseSharedViewModel
import com.anikinkirill.playground.domain.GetUsersListUseCase
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UsersListViewModel : BaseSharedViewModel<UsersListViewState, UsersListAction, UsersListEvent>(
    initialState = UsersListViewState.Idle
), KoinComponent {

    private val getUsersListUseCase: GetUsersListUseCase by inject()

    override fun obtainEvent(viewEvent: UsersListEvent) {
        when (viewEvent) {
            is UsersListEvent.ActionInvoked -> viewAction = null
            is UsersListEvent.LoadUsers -> loadUsers()
            is UsersListEvent.UserClick -> onUserClick(viewEvent.id)
        }
    }

    private fun loadUsers() {
        viewModelScope.launch {
            viewState = UsersListViewState.Loading
            viewState = try {
                val users = getUsersListUseCase.execute()
                val viewObjects = users.map { userDomain ->
                    UserViewObject(
                        id = userDomain.id,
                        name = userDomain.name,
                    )
                }
                UsersListViewState.Data(users = viewObjects)
            } catch (error: Throwable) {
                UsersListViewState.Error(
                    errorMessage =
                    error.message ?: "Some unexpected error"
                )
            }
        }
    }

    private fun onUserClick(userId: Int) {
        // TODO
    }

}