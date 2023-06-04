package com.anikinkirill.playground.screen

import com.adeo.kviewmodel.BaseSharedViewModel
import kotlinx.coroutines.launch

class UsersListViewModel : BaseSharedViewModel<UsersListViewState, UsersListAction, UsersListEvent>(
    initialState = UsersListViewState.Idle
) {

    override fun obtainEvent(viewEvent: UsersListEvent) {
        when (viewEvent) {
            is UsersListEvent.ActionInvoked -> viewAction = null
            is UsersListEvent.LoadUsers -> loadUsers()
            is UsersListEvent.UserClick -> onUserClick(viewEvent.name)
        }
    }

    private fun loadUsers() {
        // TODO
    }

    private fun onUserClick(name: String) {
        // TODO
    }

}