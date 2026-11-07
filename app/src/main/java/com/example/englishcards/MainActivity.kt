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
import com.example.englishcards.data.CustomWordsManager
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
    data object MyWords : Screen
    data class Levels(val isQuiz: Boolean) : Screen
    data class Learn(val words: List<Word>, val title: String, val progressKey: String) : Screen
    data class Quiz(val words: List<Word>, val title: String, val progressKey: String) : Screen
    data class CustomWordsPlay(val isQuiz: Boolean) : Screen
}

@Composable
private fun App() {
    val context = LocalContext.current
    val prefs = remember { PreferencesManager(context) }
    val customWordsManager = remember { CustomWordsManager(context) }
    var screen by remember { mutableStateOf<Screen>(Screen.Home) }

    val allWords = LEVELS.flatMap { it.words }
    val difficultWords = prefs.getDifficultWords().mapNotNull { en -> allWords.find { it.en == en } }
    
    // Получаем актуальный список своих слов при переходе на экран
    val myWords = remember(screen) { customWordsManager.getCustomWords() }

    val currentScreen = screen

    BackHandler(enabled = currentScreen !is Screen.Home) {
        screen = when (currentScreen) {
            is Screen.Learn -> if (currentScreen.progressKey == "DIFFICULT") Screen.DifficultModeSelect else Screen.Levels(false)
            is Screen.Quiz -> if (currentScreen.progressKey == "DIFFICULT") Screen.DifficultModeSelect else Screen.Levels(true)
            is Screen.Levels -> Screen.ModeSelect
            is Screen.DifficultModeSelect -> Screen.ModeSelect
            is Screen.MyWords -> Screen.ModeSelect
            is Screen.CustomWordsPlay -> Screen.MyWords
            else -> Screen.Home
        }
    }

    when (val s = screen) {
        Screen.Home -> HomeScreen(onStart = { screen = Screen.ModeSelect })
        
        Screen.ModeSelect -> ModeScreen(
            onCardsClick = { screen = Screen.Levels(isQuiz = false) },
            onQuizClick = { screen = Screen.Levels(isQuiz = true) },
            onDifficultClick = { screen = Screen.DifficultModeSelect },
            onMyWordsClick = { screen = Screen.MyWords },
            onBack = { screen = Screen.Home }
        )

        Screen.DifficultModeSelect -> DifficultModeScreen(
            onCardsClick = { screen = Screen.Learn(difficultWords, "Сложные слова", "DIFFICULT") },
            onQuizClick = { screen = Screen.Quiz(difficultWords, "Квиз: Сложные", "DIFFICULT") },
            onBack = { screen = Screen.ModeSelect }
        )

        Screen.MyWords -> MyWordsScreen(
            customWordsManager = customWordsManager,
            onPlayClick = { screen = Screen.CustomWordsPlay(isQuiz = false) },
            onBack = { screen = Screen.ModeSelect }
        )

        is Screen.CustomWordsPlay -> DifficultModeScreen(
            onCardsClick = { screen = Screen.Learn(myWords, "Мои слова", "CUSTOM") },
            onQuizClick = { screen = Screen.Quiz(myWords, "Квиз: Мои слова", "CUSTOM") },
            onBack = { screen = Screen.MyWords }
        )
        
        is Screen.Levels -> LevelsScreen(
            onLevelClick = { i -> 
                val level = LEVELS[i]
                if (s.isQuiz) screen = Screen.Quiz(level.words, "Квиз · ${level.code}", level.code)
                else screen = Screen.Learn(level.words, "${level.code} · ${level.title}", level.code)
            },
            onBack = { screen = Screen.ModeSelect }
        )
        
        is Screen.Learn -> LearnScreen(
            words = s.words, title = s.title, progressKey = s.progressKey, prefs = prefs,
            onBack = { 
                screen = when(s.progressKey) {
                    "DIFFICULT" -> Screen.DifficultModeSelect
                    "CUSTOM" -> Screen.MyWords
                    else -> Screen.Levels(false)
                }
            }
        )
        
        is Screen.Quiz -> QuizScreen(
            words = s.words, title = s.title, progressKey = s.progressKey, prefs = prefs,
            onBack = { 
                screen = when(s.progressKey) {
                    "DIFFICULT" -> Screen.DifficultModeSelect
                    "CUSTOM" -> Screen.MyWords
                    else -> Screen.Levels(true)
                }
            }
        )
    }
}
