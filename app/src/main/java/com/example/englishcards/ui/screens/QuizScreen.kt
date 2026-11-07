package com.example.englishcards.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.englishcards.data.PreferencesManager
import com.example.englishcards.data.Word
import com.example.englishcards.ui.theme.Purple
import com.example.englishcards.ui.theme.Turquoise

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(words: List<Word>, title: String, prefs: PreferencesManager, onBack: () -> Unit) {
    val shuffledWords = remember(words) { words.shuffled() }
    var index by rememberSaveable { mutableStateOf(0) }
    var selectedAnswer by rememberSaveable { mutableStateOf<String?>(null) }

    val total = shuffledWords.size
    val finished = index >= total || total == 0

    val currentWord = if (!finished) shuffledWords[index] else Word("", "")
    val options = remember(index) {
        if (finished) emptyList() else {
            val wrongOptions = shuffledWords.filter { it.ru != currentWord.ru }.shuffled().take(3)
            (wrongOptions + currentWord).shuffled()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = { IconButton(onClick = onBack) { Text("←", fontSize = 24.sp, color = Color.White) } },
                actions = {
                    if (!finished) {
                        // ФИКС ЗВЕЗДОЧКИ
                        var isDiff by remember(currentWord.en) { mutableStateOf(prefs.isDifficult(currentWord.en)) }
                        IconButton(onClick = { 
                            prefs.toggleDifficultWord(currentWord.en)
                            isDiff = !isDiff 
                        }) {
                            Text(if (isDiff) "⭐" else "☆", fontSize = 24.sp, color = Color.White)
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Purple, titleContentColor = Color.White)
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            if (total == 0) {
                Spacer(Modifier.height(80.dp))
                Text("Список пуст", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(32.dp))
                Button(onClick = onBack, modifier = Modifier.fillMaxWidth().height(56.dp)) { Text("Назад") }
            } else if (finished) {
                Spacer(Modifier.height(60.dp))
                Text("🏆", fontSize = 64.sp)
                Spacer(Modifier.height(16.dp))
                Text("Квиз пройден!", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(32.dp))
                Button(onClick = onBack, modifier = Modifier.fillMaxWidth().height(56.dp), shape = RoundedCornerShape(16.dp)) { Text("К выбору", fontSize = 17.sp) }
            } else {
                Text("${index + 1} из $total", color = Color(0xFF6E6A75), fontSize = 14.sp)
                Spacer(Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = { index.toFloat() / total }, 
                    modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)), 
                    color = Turquoise, trackColor = Color(0xFFE6DDF0)
                )
                Spacer(Modifier.height(30.dp))

                Text("Как переводится слово?", fontSize = 16.sp, color = Color(0xFF6E6A75))
                Spacer(Modifier.height(10.dp))
                Text(currentWord.en, fontSize = 42.sp, fontWeight = FontWeight.Bold, color = Purple)
                
                Spacer(Modifier.height(40.dp))

                options.forEach { option ->
                    val isCorrect = option.ru == currentWord.ru
                    val isSelected = selectedAnswer == option.ru
                    
                    val bgColor = when {
                        selectedAnswer == null -> Color.White
                        isCorrect -> Color(0xFF4CAF50)
                        isSelected -> Color(0xFFF44336)
                        else -> Color.White.copy(alpha = 0.5f)
                    }
                    val textColor = if (selectedAnswer != null && (isCorrect || isSelected)) Color.White else Color(0xFF1F1B24)

                    Button(
                        onClick = { if (selectedAnswer == null) selectedAnswer = option.ru },
                        modifier = Modifier.fillMaxWidth().height(60.dp).padding(vertical = 4.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = bgColor, contentColor = textColor),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = if (selectedAnswer == null) 2.dp else 0.dp)
                    ) {
                        Text(option.ru, fontSize = 18.sp, fontWeight = FontWeight.Medium, textAlign = TextAlign.Center)
                    }
                }

                Spacer(Modifier.weight(1f))

                if (selectedAnswer != null) {
                    Button(
                        onClick = {
                            index += 1
                            selectedAnswer = null
                        },
                        modifier = Modifier.fillMaxWidth().height(60.dp),
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Purple)
                    ) {
                        Text(if (index + 1 >= total) "Завершить" else "Следующее слово", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
