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
import com.example.englishcards.ui.screens.*
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
    data object ModeSelect : Screen
    data class Levels(val isQuiz: Boolean) : Screen
    data class Learn(val levelIndex: Int) : Screen
    data class Quiz(val levelIndex: Int) : Screen
}

@Composable
private fun App() {
    var screen by remember { mutableStateOf<Screen>(Screen.Home) }

    // Обработка кнопки "Назад" на телефоне
    BackHandler(enabled = screen !is Screen.Home) {
        screen = when (screen) {
            is Screen.Learn -> Screen.Levels(false)
            is Screen.Quiz -> Screen.Levels(true)
            is Screen.Levels -> Screen.ModeSelect
            else -> Screen.Home
        }
    }

    when (val s = screen) {
        Screen.Home -> HomeScreen(onStart = { screen = Screen.ModeSelect })
        
        Screen.ModeSelect -> ModeScreen(
            onCardsClick = { screen = Screen.Levels(isQuiz = false) },
            onQuizClick = { screen = Screen.Levels(isQuiz = true) },
            onBack = { screen = Screen.Home }
        )
        
        is Screen.Levels -> LevelsScreen(
            onLevelClick = { i -> 
                if (s.isQuiz) screen = Screen.Quiz(i) else screen = Screen.Learn(i) 
            },
            onBack = { screen = Screen.ModeSelect }
        )
        
        is Screen.Learn -> LearnScreen(
            level = LEVELS[s.levelIndex],
            onBack = { screen = Screen.Levels(isQuiz = false) }
        )
        
        is Screen.Quiz -> QuizScreen(
            level = LEVELS[s.levelIndex],
            onBack = { screen = Screen.Levels(isQuiz = true) }
        )
    }
}
