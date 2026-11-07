package com.example.englishcards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.englishcards.data.LEVELS
import com.example.englishcards.ui.screens.HomeScreen
import com.example.englishcards.ui.screens.LearnScreen
import com.example.englishcards.ui.screens.LevelsScreen
import com.example.englishcards.ui.theme.EnglishCardsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnglishCardsTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    App()
                }
            }
        }
    }
}

private sealed interface Screen {
    data object Home : Screen
    data object Levels : Screen
    data class Learn(val levelIndex: Int) : Screen
}

@Composable
private fun App() {
    var screen by remember { mutableStateOf<Screen>(Screen.Home) }

    BackHandler(enabled = screen !is Screen.Home) {
        screen = if (screen is Screen.Learn) Screen.Levels else Screen.Home
    }

    when (val s = screen) {
        Screen.Home -> HomeScreen(onStart = { screen = Screen.Levels })
        Screen.Levels -> LevelsScreen(
            onLevelClick = { i -> screen = Screen.Learn(i) },
            onBack = { screen = Screen.Home }
        )
        is Screen.Learn -> LearnScreen(
            level = LEVELS[s.levelIndex],
            onBack = { screen = Screen.Levels }
        )
    }
}
