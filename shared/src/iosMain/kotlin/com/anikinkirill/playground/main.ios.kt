import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import com.anikinkirill.playground.navigation.navigationGraph
import com.anikinkirill.playground.theme.AppTheme
import platform.UIKit.UIViewController
import ru.alexgladkov.odyssey.compose.setup.OdysseyConfiguration
import ru.alexgladkov.odyssey.compose.setup.StartScreen
import ru.alexgladkov.odyssey.compose.setup.setNavigationContent

fun MainViewController(): UIViewController = ComposeUIViewController {
    val odysseyConfiguration = OdysseyConfiguration(
        startScreen = StartScreen.Custom("user_list")
    )

    AppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            setNavigationContent(odysseyConfiguration) {
                 navigationGraph()
            }
        }
    }
}