package com.anikinkirill.playground.navigation

import androidx.compose.runtime.Composable
import com.anikinkirill.playground.screen.UserListScreen
import com.anikinkirill.playground.screen.posts.UserPostScreen
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder

@Composable
fun RootComposeBuilder.navigationGraph() {
    screen(name = "user_list") {
        UserListScreen()
    }

    screen(name = "user_post") { params ->
        val userId = params as? Int
        UserPostScreen(userId = userId ?: -1)
    }
}