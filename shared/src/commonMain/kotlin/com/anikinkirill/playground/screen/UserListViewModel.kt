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
            is UsersListEvent.UserClick -> onUserClick(viewEvent.name)
        }
    }

    private fun loadUsers() {
        viewModelScope.launch {
            viewState = UsersListViewState.Loading
            viewState = try {
                val users = getUsersListUseCase.execute()
                UsersListViewState.Data(users = users.map { it.name })
            } catch (error: Throwable) {
                UsersListViewState.Error(
                    errorMessage =
                    error.message ?: "Some unexpected error"
                )
            }
        }
    }

    private fun onUserClick(name: String) {
        // TODO
    }

}