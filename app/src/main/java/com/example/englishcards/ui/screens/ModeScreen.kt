package com.example.englishcards.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.englishcards.ui.theme.Purple
import com.example.englishcards.ui.theme.PurpleLight
import com.example.englishcards.ui.theme.Turquoise

@Composable
fun ModeScreen(onCardsClick: () -> Unit, onQuizClick: () -> Unit, onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Purple, PurpleLight, Turquoise)))
    ) {
        IconButton(onClick = onBack, modifier = Modifier.padding(16.dp)) {
            Text("←", fontSize = 28.sp, color = Color.White, fontWeight = FontWeight.Bold)
        }
        
        Column(
            modifier = Modifier.fillMaxSize().padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Как будем учиться?",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(Modifier.height(40.dp))
            
            Button(
                onClick = onCardsClick,
                modifier = Modifier.fillMaxWidth().height(64.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Purple)
            ) {
                Text("📚 Карточки", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            
            Spacer(Modifier.height(20.dp))
            
            Button(
                onClick = onQuizClick,
                modifier = Modifier.fillMaxWidth().height(64.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Turquoise, contentColor = Color.White)
            ) {
                Text("🎯 Квиз (Тест)", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
