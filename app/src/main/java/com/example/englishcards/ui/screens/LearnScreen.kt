package com.example.englishcards.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.graphics.graphicsLayer
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
fun LearnScreen(words: List<Word>, title: String, progressKey: String, prefs: PreferencesManager, onBack: () -> Unit) {
    val shuffledWords = remember(words) { words.shuffled() }
    var index by rememberSaveable(progressKey) { mutableStateOf(prefs.getProgress(progressKey)) }
    var revealed by rememberSaveable(progressKey) { mutableStateOf(false) }
    var showResetDialog by remember { mutableStateOf(false) }

    val total = shuffledWords.size
    val finished = index >= total || total == 0

    LaunchedEffect(index) {
        if (index < total) prefs.saveProgress(progressKey, index)
    }

    if (showResetDialog) {
        AlertDialog(
            onDismissRequest = { showResetDialog = false },
            title = { Text("Сбросить прогресс?") },
            text = { Text("Вы уверены, что хотите начать этот раздел заново?") },
            confirmButton = {
                TextButton(onClick = {
                    prefs.resetProgress(progressKey); index = 0; revealed = false; showResetDialog = false
                }) { Text("Уверен", color = Color.Red) }
            },
            dismissButton = { TextButton(onClick = { showResetDialog = false }) { Text("Отмена") } }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = { IconButton(onClick = onBack) { Text("←", fontSize = 24.sp, color = Color.White) } },
                actions = {
                    if (!finished) {
                        val currentWord = shuffledWords[index]
                        var isDiff by remember { mutableStateOf(prefs.isDifficult(currentWord.en)) }
                        IconButton(onClick = { prefs.toggleDifficultWord(currentWord.en); isDiff = !isDiff }) {
                            Text(if (isDiff) "⭐" else "☆", fontSize = 24.sp, color = Color.White)
                        }
                        IconButton(onClick = { showResetDialog = true }) { Text("↺", fontSize = 26.sp, color = Color.White, fontWeight = FontWeight.Bold) }
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
                Text("🎉", fontSize = 64.sp)
                Spacer(Modifier.height(16.dp))
                Text("Уровень пройден!", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(32.dp))
                Button(onClick = onBack, modifier = Modifier.fillMaxWidth().height(56.dp), shape = RoundedCornerShape(16.dp)) { Text("К выбору", fontSize = 17.sp) }
            } else {
                val word = shuffledWords[index]
                Text("${index + 1} из $total", color = Color(0xFF6E6A75), fontSize = 14.sp)
                Spacer(Modifier.height(8.dp))
                LinearProgressIndicator(progress = { index.toFloat() / total }, modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)), color = Turquoise, trackColor = Color(0xFFE6DDF0))
                Spacer(Modifier.height(24.dp))

                // --- 3D АНИМАЦИЯ КАРТОЧКИ ---
                val rotation by animateFloatAsState(
                    targetValue = if (revealed) 180f else 0f,
                    animationSpec = tween(durationMillis = 400),
                    label = "card_rotation"
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp)
                        .graphicsLayer {
                            rotationY = rotation
                            cameraDistance = 12f * density
                        },
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Box(Modifier.fillMaxSize()) {
                        // Лицевая сторона (Английский)
                        if (rotation <= 90f) {
                            Column(
                                modifier = Modifier.fillMaxSize().padding(24.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(word.en, fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Purple, textAlign = TextAlign.Center)
                            }
                        } else {
                            // Задняя сторона (Русский) - переворачиваем текст обратно
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(24.dp)
                                    .graphicsLayer { rotationY = 180f },
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(word.ru, fontSize = 30.sp, color = Color(0xFF1F1B24), textAlign = TextAlign.Center)
                            }
                        }
                    }
                }
                // -----------------------------

                Spacer(Modifier.weight(1f))

                if (!revealed) {
                    Button(onClick = { revealed = true }, modifier = Modifier.fillMaxWidth().height(60.dp), shape = RoundedCornerShape(18.dp), colors = ButtonDefaults.buttonColors(containerColor = Turquoise, contentColor = Color.White)) {
                        Text("Ответ", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                } else {
                    Button(onClick = { index += 1; revealed = false }, modifier = Modifier.fillMaxWidth().height(60.dp), shape = RoundedCornerShape(18.dp), colors = ButtonDefaults.buttonColors(containerColor = Purple, contentColor = Color.White)) {
                        Text(if (index + 1 >= total) "Завершить" else "Продолжить", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
