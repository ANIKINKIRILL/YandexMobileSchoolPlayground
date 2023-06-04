package com.anikinkirill.playground.navigation

import androidx.compose.runtime.Composable
import com.anikinkirill.playground.screen.UserListScreen
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder

@Composable
fun RootComposeBuilder.navigationGraph() {
    screen(name = "user_list") {
        UserListScreen()
    }

    screen(name = "user_post") {

    }
}