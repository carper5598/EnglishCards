package com.example.englishcards.data

data class Word(val en: String, val ru: String)

data class Level(
    val code: String,
    val title: String,
    val description: String,
    val words: List<Word>
)

private fun w(en: String, ru: String) = Word(en, ru)

val LEVELS: List<Level> = listOf(
    Level("A0", "Pre-A1 · С нуля", "Первые слова", listOf(
        w("dad", "папа"), w("mum", "мама"), w("cat", "кот"), w("dog", "собака"),
        w("sun", "солнце"), w("water", "вода"), w("red", "красный"), w("blue", "синий"),
        w("one", "один"), w("two", "два"), w("three", "три"), w("big", "большой"),
        w("small", "маленький"), w("yes", "да"), w("no", "нет"), w("hello", "привет"),
        w("book", "книга"), w("house", "дом"), w("milk", "молоко"), w("good", "хороший")
    )),
    Level("A1", "Beginner", "Базовые темы", listOf(
        w("friend", "друг"), w("school", "школа"), w("family", "семья"), w("happy", "счастливый"),
        w("eat", "есть"), w("drink", "пить"), w("morning", "утро"), w("night", "ночь"),
        w("city", "город"), w("work", "работа"), w("teacher", "учитель"), w("money", "деньги"),
        w("door", "дверь"), w("window", "окно"), w("bread", "хлеб"), w("apple", "яблоко"),
        w("run", "бежать"), w("sleep", "спать"), w("new", "новый"), w("cold", "холодный")
    )),
    Level("A2", "Elementary", "Быт и путешествия", listOf(
        w("weather", "погода"), w("holiday", "отпуск"), w("ticket", "билет"), w("borrow", "одалживать"),
        w("expensive", "дорогой"), w("dangerous", "опасный"), w("prefer", "предпочитать"), w("probably", "вероятно"),
        w("journey", "поездка"), w("luggage", "багаж"), w("neighbour", "сосед"), w("queue", "очередь"),
        w("healthy", "здоровый"), w("improve", "улучшать"), w("advice", "совет"), w("decide", "решать"),
        w("arrive", "прибывать"), w("quite", "довольно"), w("worried", "обеспокоенный"), w("polite", "вежливый")
    )),
    Level("B1", "Intermediate", "Мнение и опыт", listOf(
        w("opinion", "мнение"), w("experience", "опыт"), w("environment", "окружающая среда"), w("although", "хотя"),
        w("suggest", "предлагать"), w("advantage", "преимущество"), w("achieve", "достигать"), w("challenge", "вызов"),
        w("consider", "рассматривать"), w("develop", "развивать"), w("encourage", "поощрять"), w("however", "однако"),
        w("manage", "справляться"), w("opportunity", "возможность"), w("purpose", "цель"), w("reason", "причина"),
        w("require", "требовать"), w("solution", "решение"), w("success", "успех"), w("trust", "доверять")
    )),
    Level("B2", "Upper-Intermediate", "Абстрактные темы", listOf(
        w("consequence", "последствие"), w("approach", "подход"), w("reliable", "надёжный"), w("negotiate", "вести переговоры"),
        w("significant", "значительный"), w("nevertheless", "тем не менее"), w("assume", "предполагать"), w("benefit", "польза"),
        w("complicated", "сложный"), w("contribute", "вносить вклад"), w("efficient", "эффективный"), w("evidence", "доказательство"),
        w("failure", "неудача"), w("influence", "влияние"), w("maintain", "поддерживать"), w("persuade", "убеждать"),
        w("relevant", "уместный"), w("tendency", "тенденция"), w("unless", "если не"), w("worthwhile", "стоящий")
    )),
    Level("C1", "Advanced", "Академическая лексика", listOf(
        w("comprehensive", "всесторонний"), w("ambiguous", "двусмысленный"), w("deteriorate", "ухудшаться"), w("feasible", "осуществимый"),
        w("inherent", "присущий"), w("scrutinize", "тщательно изучать"), w("coherent", "связный"), w("discrepancy", "расхождение"),
        w("elaborate", "детально изложить"), w("inevitable", "неизбежный"), w("mitigate", "смягчать"), w("preliminary", "предварительный"),
        w("profound", "глубокий"), w("reluctant", "неохотный"), w("sustain", "поддерживать"), w("versatile", "многосторонний"),
        w("albeit", "хотя"), w("constrain", "ограничивать"), w("endorse", "одобрять"), w("undermine", "подрывать")
    )),
    Level("C2", "Proficiency", "Редкая и точная лексика", listOf(
        w("ubiquitous", "вездесущий"), w("idiosyncrasy", "особенность"), w("juxtapose", "сопоставлять"), w("perspicacious", "проницательный"),
        w("serendipity", "счастливая случайность"), w("acquiesce", "нехотя соглашаться"), w("belligerent", "воинственный"), w("clandestine", "тайный"),
        w("conundrum", "загадка"), w("ebullient", "кипучий"), w("fastidious", "привередливый"), w("gregarious", "общительный"),
        w("harbinger", "предвестник"), w("impeccable", "безупречный"), w("lucid", "ясный"), w("meticulous", "дотошный"),
        w("notorious", "печально известный"), w("pragmatic", "прагматичный"), w("quintessential", "типичнейший"), w("resilient", "устойчивый")
    ))
)
