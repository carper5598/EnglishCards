package com.example.englishcards.data

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("english_app_prefs", Context.MODE_PRIVATE)

    // Сложные слова
    fun getDifficultWords(): Set<String> = prefs.getStringSet("difficult_words", emptySet()) ?: emptySet()
    fun isDifficult(wordEn: String): Boolean = getDifficultWords().contains(wordEn)
    fun toggleDifficultWord(wordEn: String) {
        val current = getDifficultWords().toMutableSet()
        if (current.contains(wordEn)) current.remove(wordEn) else current.add(wordEn)
        prefs.edit().putStringSet("difficult_words", current).apply()
    }
}
