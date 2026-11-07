package com.example.englishcards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.englishcards.data.LEVELS
import com.example.englishcards.data.PreferencesManager
import com.example.englishcards.data.Word
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
    data object DifficultModeSelect : Screen
    data class Levels(val isQuiz: Boolean) : Screen
    data class Learn(val words: List<Word>, val title: String, val progressKey: String) : Screen
    data class Quiz(val words: List<Word>, val title: String, val progressKey: String) : Screen
}

@Composable
private fun App() {
    val context = LocalContext.current
    val prefs = remember { PreferencesManager(context) }
    var screen by remember { mutableStateOf<Screen>(Screen.Home) }

    // Собираем сложные слова из всех уровней
    val allWords = LEVELS.flatMap { it.words }
    val difficultWords = prefs.getDifficultWords().mapNotNull { en ->
        allWords.find { it.en == en }
    }

    BackHandler(enabled = screen !is Screen.Home) {
        screen = when (screen) {
            is Screen.Learn -> if (screen.progressKey == "DIFFICULT") Screen.DifficultModeSelect else Screen.Levels(false)
            is Screen.Quiz -> if (screen.progressKey == "DIFFICULT") Screen.DifficultModeSelect else Screen.Levels(true)
            is Screen.Levels -> Screen.ModeSelect
            is Screen.DifficultModeSelect -> Screen.ModeSelect
            else -> Screen.Home
        }
    }

    when (val s = screen) {
        Screen.Home -> HomeScreen(onStart = { screen = Screen.ModeSelect })
        
        Screen.ModeSelect -> ModeScreen(
            onCardsClick = { screen = Screen.Levels(isQuiz = false) },
            onQuizClick = { screen = Screen.Levels(isQuiz = true) },
            onDifficultClick = { screen = Screen.DifficultModeSelect },
            onBack = { screen = Screen.Home }
        )

        Screen.DifficultModeSelect -> DifficultModeScreen(
            onCardsClick = { 
                screen = Screen.Learn(difficultWords, "Сложные слова", "DIFFICULT") 
            },
            onQuizClick = { 
                screen = Screen.Quiz(difficultWords, "Квиз: Сложные", "DIFFICULT") 
            },
            onBack = { screen = Screen.ModeSelect }
        )
        
        is Screen.Levels -> LevelsScreen(
            onLevelClick = { i -> 
                val level = LEVELS[i]
                if (s.isQuiz) {
                    screen = Screen.Quiz(level.words, "Квиз · ${level.code}", level.code)
                } else {
                    screen = Screen.Learn(level.words, "${level.code} · ${level.title}", level.code)
                }
            },
            onBack = { screen = Screen.ModeSelect }
        )
        
        is Screen.Learn -> LearnScreen(
            words = s.words,
            title = s.title,
            progressKey = s.progressKey,
            prefs = prefs,
            onBack = { 
                screen = if (s.progressKey == "DIFFICULT") Screen.DifficultModeSelect else Screen.Levels(false) 
            }
        )
        
        is Screen.Quiz -> QuizScreen(
            words = s.words,
            title = s.title,
            progressKey = s.progressKey,
            prefs = prefs,
            onBack = { 
                screen = if (s.progressKey == "DIFFICULT") Screen.DifficultModeSelect else Screen.Levels(true) 
            }
        )
    }
}
