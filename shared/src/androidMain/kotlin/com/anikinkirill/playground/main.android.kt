package com.anikinkirill.playground

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import com.anikinkirill.playground.navigation.navigationGraph
import ru.alexgladkov.odyssey.compose.setup.OdysseyConfiguration
import ru.alexgladkov.odyssey.compose.setup.StartScreen
import ru.alexgladkov.odyssey.compose.setup.setNavigationContent

@Composable
fun MainView(activity: ComponentActivity) {
    val odysseyConfiguration = OdysseyConfiguration(
        canvas = activity,
        startScreen = StartScreen.Custom("user_list")
    )

    setNavigationContent(odysseyConfiguration, onApplicationFinish = {
        activity.finishAffinity()
    }) {
        navigationGraph()
    }
}