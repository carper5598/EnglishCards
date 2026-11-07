package com.example.englishcards.data

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONArray
import org.json.JSONObject

class CustomWordsManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("custom_words_prefs", Context.MODE_PRIVATE)

    fun getCustomWords(): List<Word> {
        val json = prefs.getString("my_words", "[]") ?: "[]"
        val list = mutableListOf<Word>()
        try {
            val array = JSONArray(json)
            for (i in 0 until array.length()) {
                val obj = array.getJSONObject(i)
                list.add(Word(obj.getString("en"), obj.getString("ru")))
            }
        } catch (e: Exception) { e.printStackTrace() }
        return list
    }

    fun addWord(en: String, ru: String) {
        val list = getCustomWords().toMutableList()
        list.add(Word(en.trim(), ru.trim()))
        saveWords(list)
    }

    fun removeWord(wordEn: String) {
        val list = getCustomWords().toMutableList()
        list.removeAll { it.en == wordEn }
        saveWords(list)
    }

    private fun saveWords(words: List<Word>) {
        val array = JSONArray()
        words.forEach {
            val obj = JSONObject()
            obj.put("en", it.en)
            obj.put("ru", it.ru)
            array.put(obj)
        }
        prefs.edit().putString("my_words", array.toString()).apply()
    }
}
