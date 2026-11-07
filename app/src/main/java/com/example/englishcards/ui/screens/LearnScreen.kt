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
import com.example.englishcards.data.Level
import com.example.englishcards.ui.theme.Purple
import com.example.englishcards.ui.theme.Turquoise

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearnScreen(level: Level, onBack: () -> Unit) {
    var index by rememberSaveable(level.code) { mutableStateOf(0) }
    var revealed by rememberSaveable(level.code) { mutableStateOf(false) }

    val total = level.words.size
    val finished = index >= total

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("${level.code} · ${level.title}") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Text("←", fontSize = 24.sp, color = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Purple,
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (finished) {
                Spacer(Modifier.height(60.dp))
                Text("🎉", fontSize = 64.sp)
                Spacer(Modifier.height(16.dp))
                Text("Уровень пройден!", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Text(
                    "Ты повторил(а) все $total слов уровня ${level.code}",
                    textAlign = TextAlign.Center,
                    color = Color(0xFF6E6A75)
                )
                Spacer(Modifier.height(32.dp))
                Button(
                    onClick = onBack,
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("К выбору уровня", fontSize = 17.sp)
                }
            } else {
                val word = level.words[index]

                Text("${index + 1} из $total", color = Color(0xFF6E6A75), fontSize = 14.sp)
                Spacer(Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = { index.toFloat() / total },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = Turquoise,
                    trackColor = Color(0xFFE6DDF0)
                )
                Spacer(Modifier.height(24.dp))

                Card(
                    modifier = Modifier.fillMaxWidth().height(320.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            word.en,
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                            color = Purple,
                            textAlign = TextAlign.Center
                        )
                        if (revealed) {
                            Spacer(Modifier.height(20.dp))
                            Box(
                                Modifier
                                    .width(80.dp)
                                    .height(3.dp)
                                    .background(Turquoise, RoundedCornerShape(2.dp))
                            )
                            Spacer(Modifier.height(20.dp))
                            Text(
                                word.ru,
                                fontSize = 30.sp,
                                color = Color(0xFF1F1B24),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                Spacer(Modifier.weight(1f))

                if (!revealed) {
                    Button(
                        onClick = { revealed = true },
                        modifier = Modifier.fillMaxWidth().height(60.dp),
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Turquoise,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Ответ", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                } else {
                    Button(
                        onClick = {
                            index += 1
                            revealed = false
                        },
                        modifier = Modifier.fillMaxWidth().height(60.dp),
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Purple,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            if (index + 1 >= total) "Завершить" else "Продолжить",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
