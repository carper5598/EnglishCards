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
        // Первые 20
        w("dad", "папа"), w("mum", "мама"), w("cat", "кот"), w("dog", "собака"),
        w("sun", "солнце"), w("water", "вода"), w("red", "красный"), w("blue", "синий"),
        w("one", "один"), w("two", "два"), w("three", "три"), w("big", "большой"),
        w("small", "маленький"), w("yes", "да"), w("no", "нет"), w("hello", "привет"),
        w("book", "книга"), w("house", "дом"), w("milk", "молоко"), w("good", "хороший"),
        // Новые 30
        w("boy", "мальчик"), w("girl", "девочка"), w("man", "мужчина"), w("woman", "женщина"),
        w("car", "машина"), w("tree", "дерево"), w("fish", "рыба"), w("bird", "птица"),
        w("hand", "рука"), w("eye", "глаз"), w("eat", "есть"), w("drink", "пить"),
        w("go", "идти"), w("stop", "останавливаться"), w("hot", "горячий"), w("cold", "холодный"),
        w("day", "день"), w("night", "ночь"), w("ball", "мяч"), w("toy", "игрушка"),
        w("hat", "шляпа"), w("shoe", "обувь"), w("bed", "кровать"), w("door", "дверь"),
        w("look", "смотреть"), w("see", "видеть"), w("run", "бежать"), w("sit", "сидеть"),
        w("stand", "стоять"), w("open", "открывать")
    )),
    Level("A1", "Beginner", "Базовые темы", listOf(
        // Первые 20
        w("friend", "друг"), w("school", "школа"), w("family", "семья"), w("happy", "счастливый"),
        w("eat", "есть"), w("drink", "пить"), w("morning", "утро"), w("night", "ночь"),
        w("city", "город"), w("work", "работа"), w("teacher", "учитель"), w("money", "деньги"),
        w("door", "дверь"), w("window", "окно"), w("bread", "хлеб"), w("apple", "яблоко"),
        w("run", "бежать"), w("sleep", "спать"), w("new", "новый"), w("cold", "холодный"),
        // Новые 30
        w("room", "комната"), w("table", "стол"), w("chair", "стул"), w("bus", "автобус"),
        w("train", "поезд"), w("street", "улица"), w("shop", "магазин"), w("buy", "покупать"),
        w("sell", "продавать"), w("play", "играть"), w("read", "читать"), w("write", "писать"),
        w("speak", "говорить"), w("listen", "слушать"), w("watch", "смотреть"), w("help", "помогать"),
        w("start", "начинать"), w("finish", "заканчивать"), w("time", "время"), w("week", "неделя"),
        w("month", "месяц"), w("year", "год"), w("bad", "плохой"), w("warm", "теплый")
    )),
    Level("A2", "Elementary", "Быт и путешествия", listOf(
        // Первые 20
        w("weather", "погода"), w("holiday", "отпуск"), w("ticket", "билет"), w("borrow", "одалживать"),
        w("expensive", "дорогой"), w("dangerous", "опасный"), w("prefer", "предпочитать"), w("probably", "вероятно"),
        w("journey", "поездка"), w("luggage", "багаж"), w("neighbour", "сосед"), w("queue", "очередь"),
        w("healthy", "здоровый"), w("improve", "улучшать"), w("advice", "совет"), w("decide", "решать"),
        w("arrive", "прибывать"), w("quite", "довольно"), w("worried", "обеспокоенный"), w("polite", "вежливый"),
        // Новые 30
        w("airport", "аэропорт"), w("station", "станция"), w("hotel", "отель"), w("restaurant", "ресторан"),
        w("meal", "прием пищи"), w("bill", "счет"), w("order", "заказывать"), w("change", "менять"),
        w("check", "проверять"), w("passport", "паспорт"), w("map", "карта"), w("guide", "гид"),
        w("lost", "потерянный"), w("find", "находить"), w("wait", "ждать"), w("hurry", "спешить"),
        w("relax", "расслабляться"), w("enjoy", "наслаждаться"), w("hate", "ненавидеть"), w("love", "любить"),
        w("beautiful", "красивый"), w("ugly", "уродливый"), w("boring", "скучный"), w("exciting", "захватывающий"),
        w("empty", "пустой"), w("full", "полный"), w("heavy", "тяжелый"), w("light", "легкий"),
        w("clean", "чистый"), w("dirty", "грязный")
    )),
    Level("B1", "Intermediate", "Мнение и опыт", listOf(
        // Первые 20
        w("opinion", "мнение"), w("experience", "опыт"), w("environment", "окружающая среда"), w("although", "хотя"),
        w("suggest", "предлагать"), w("advantage", "преимущество"), w("achieve", "достигать"), w("challenge", "вызов"),
        w("consider", "рассматривать"), w("develop", "развивать"), w("encourage", "поощрять"), w("however", "однако"),
        w("manage", "справляться"), w("opportunity", "возможность"), w("purpose", "цель"), w("reason", "причина"),
        w("require", "требовать"), w("solution", "решение"), w("success", "успех"), w("trust", "доверять"),
        // Новые 30
        w("career", "карьера"), w("employ", "нанимать"), w("interview", "собеседование"), w("salary", "зарплата"),
        w("skill", "навык"), w("train", "тренировать"), w("task", "задача"), w("project", "проект"),
        w("meeting", "встреча"), w("discuss", "обсуждать"), w("explain", "объяснять"), w("describe", "описывать"),
        w("agree", "соглашаться"), w("disagree", "не соглашаться"), w("argue", "спорить"), w("compare", "сравнивать"),
        w("likely", "вероятно"), w("unlikely", "маловероятно"), w("possible", "возможный"), w("impossible", "невозможный"),
        w("necessary", "необходимый"), w("useful", "полезный"), w("useless", "бесполезный"), w("safe", "безопасный"),
        w("protect", "защищать"), w("damage", "повреждать"), w("prevent", "предотвращать"), w("avoid", "избегать")
    )),
    Level("B2", "Upper-Intermediate", "Абстрактные темы", listOf(
        // Первые 20
        w("consequence", "последствие"), w("approach", "подход"), w("reliable", "надёжный"), w("negotiate", "вести переговоры"),
        w("significant", "значительный"), w("nevertheless", "тем не менее"), w("assume", "предполагать"), w("benefit", "польза"),
        w("complicated", "сложный"), w("contribute", "вносить вклад"), w("efficient", "эффективный"), w("evidence", "доказательство"),
        w("failure", "неудача"), w("influence", "влияние"), w("maintain", "поддерживать"), w("persuade", "убеждать"),
        w("relevant", "уместный"), w("tendency", "тенденция"), w("unless", "если не"), w("worthwhile", "стоящий"),
        // Новые 30
        w("analyse", "анализировать"), w("assess", "оценивать"), w("concept", "концепция"), w("context", "контекст"),
        w("criteria", "критерии"), w("define", "определять"), w("demonstrate", "демонстрировать"), w("determine", "определять"),
        w("establish", "устанавливать"), w("evaluate", "оценивать"), w("feature", "особенность"), w("focus", "фокус"),
        w("indicate", "указывать"), w("interpret", "интерпретировать"), w("involve", "включать"), w("issue", "проблема"),
        w("occur", "происходить"), w("principle", "принцип"), w("process", "процесс"), w("react", "реагировать"),
        w("restrict", "ограничивать"), w("secure", "безопасный"), w("select", "выбирать"), w("specific", "конкретный"),
        w("strategy", "стратегия"), w("structure", "структура"), w("survive", "выживать"), w("theory", "теория"),
        w("transfer", "переводить"), w("valid", "действительный")
    )),
    Level("C1", "Advanced", "Академическая лексика", listOf(
        // Первые 20
        w("comprehensive", "всесторонний"), w("ambiguous", "двусмысленный"), w("deteriorate", "ухудшаться"), w("feasible", "осуществимый"),
        w("inherent", "присущий"), w("scrutinize", "тщательно изучать"), w("coherent", "связный"), w("discrepancy", "расхождение"),
        w("elaborate", "детально изложить"), w("inevitable", "неизбежный"), w("mitigate", "смягчать"), w("preliminary", "предварительный"),
        w("profound", "глубокий"), w("reluctant", "неохотный"), w("sustain", "поддерживать"), w("versatile", "многосторонний"),
        w("albeit", "хотя"), w("constrain", "ограничивать"), w("endorse", "одобрять"), w("undermine", "подрывать"),
        // Новые 30
        w("advocate", "защитник"), w("allocate", "распределять"), w("anticipate", "предвидеть"), w("arbitrary", "произвольный"),
        w("compile", "составлять"), w("comprise", "включать"), w("conceive", "задумывать"), w("concurrent", "одновременный"),
        w("contradict", "противоречить"), w("controversial", "спорный"), w("crucial", "решающий"), w("deviate", "отклоняться"),
        w("diminish", "уменьшать"), w("discrete", "отдельный"), w("distort", "искажать"), w("empirical", "эмпирический"),
        w("enhance", "улучшать"), w("explicit", "явный"), w("facilitate", "облегчать"), w("fluctuate", "колебаться"),
        w("hierarchy", "иерархия"), w("implicit", "неявный"), w("initiate", "инициировать"), w("integrate", "интегрировать"),
        w("intervene", "вмешиваться"), w("justify", "оправдывать"), w("manipulate", "манипулировать"), w("paradigm", "парадигма"),
        w("perceive", "воспринимать"), w("refine", "совершенствовать")
    )),
    Level("C2", "Proficiency", "Редкая и точная лексика", listOf(
        // Первые 20
        w("ubiquitous", "вездесущий"), w("idiosyncrasy", "особенность"), w("juxtapose", "сопоставлять"), w("perspicacious", "проницательный"),
        w("serendipity", "счастливая случайность"), w("acquiesce", "нехотя соглашаться"), w("belligerent", "воинственный"), w("clandestine", "тайный"),
        w("conundrum", "загадка"), w("ebullient", "кипучий"), w("fastidious", "привередливый"), w("gregarious", "общительный"),
        w("harbinger", "предвестник"), w("impeccable", "безупречный"), w("lucid", "ясный"), w("meticulous", "дотошный"),
        w("notorious", "печально известный"), w("pragmatic", "прагматичный"), w("quintessential", "типичнейший"), w("resilient", "устойчивый"),
        // Новые 30
        w("aberration", "отклонение"), w("abhor", "ненавидеть"), w("admonish", "предостерегать"), w("aesthetic", "эстетичный"),
        w("altruistic", "альтруистичный"), w("anachronism", "анахронизм"), w("apathy", "апатия"), w("astute", "проницательный"),
        w("audacious", "дерзкий"), w("benign", "доброкачественный"), w("capricious", "капризный"), w("corroborate", "подтверждать"),
        w("deference", "почтение"), w("delineate", "очерчивать"), w("ephemeral", "мимолетный"), w("equivocal", "двусмысленный"),
        w("exacerbate", "обострять"), w("exorbitant", "чрезмерный"), w("fallacy", "заблуждение"), w("frugal", "экономный"),
        w("hackneyed", "избитый"), w("impetuous", "импульсивный"), w("incisive", "проницательный"), w("innocuous", "безобидный"),
        w("insidious", "коварный"), w("magnanimous", "великодушный"), w("nefarious", "гнусный"), w("obsolete", "устаревший"),
        w("ostentatious", "показной"), w("paragon", "образец")
    ))
)
