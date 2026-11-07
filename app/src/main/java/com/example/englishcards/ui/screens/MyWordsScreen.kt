package com.example.englishcards.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.englishcards.data.CustomWordsManager
import com.example.englishcards.ui.theme.Purple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyWordsScreen(customWordsManager: CustomWordsManager, onPlayClick: () -> Unit, onBack: () -> Unit) {
    var enWord by remember { mutableStateOf("") }
    var ruWord by remember { mutableStateOf("") }
    // Стейт для обновления списка после добавления/удаления
    var refreshKey by remember { mutableStateOf(0) }
    
    val words = remember(refreshKey) { customWordsManager.getCustomWords() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Мои слова") },
                navigationIcon = {
                    IconButton(onClick = onBack) { Text("←", fontSize = 24.sp, color = Color.White) }
                },
                actions = {
                    if (words.isNotEmpty()) {
                        TextButton(onClick = onPlayClick) {
                            Text("Учить ➔", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Purple, titleContentColor = Color.White)
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp)) {
            Text("Добавить слово:", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = enWord, onValueChange = { enWord = it },
                    label = { Text("English") }, modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                )
                OutlinedTextField(
                    value = ruWord, onValueChange = { ruWord = it },
                    label = { Text("Русский") }, modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                )
            }
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = {
                    if (enWord.isNotBlank() && ruWord.isNotBlank()) {
                        customWordsManager.addWord(enWord, ruWord)
                        enWord = ""
                        ruWord = ""
                        refreshKey++ // Обновляем список
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Добавить в список")
            }

            Spacer(Modifier.height(24.dp))
            Divider()
            Spacer(Modifier.height(16.dp))

            if (words.isEmpty()) {
                Text("Список пока пуст. Добавь первое слово!", color = Color.Gray)
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(words) { word ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(word.en, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Purple)
                                    Text(word.ru, fontSize = 16.sp, color = Color.DarkGray)
                                }
                                IconButton(onClick = {
                                    customWordsManager.removeWord(word.en)
                                    refreshKey++
                                }) {
                                    Text("🗑️", fontSize = 20.sp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
