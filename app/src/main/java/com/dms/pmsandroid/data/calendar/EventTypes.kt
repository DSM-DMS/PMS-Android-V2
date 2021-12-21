package com.dms.pmsandroid.data.calendar

enum class EventTypes {
    MUST_GO_HOME, EXAM, VACATION, HOLIDAYS, ETC
}

fun String.toEventType(): EventTypes{
    return when (this) {
        "의무귀가" -> {
            EventTypes.MUST_GO_HOME
        }

        "중간고사", "기말고사" -> {
            EventTypes.EXAM
        }

        "여름방학", "겨울방학", "여름방학식", "겨울방학식" -> {
            EventTypes.VACATION
        }

        "신정", "어린이날", "석가탄신일", "현충일", "광복절", "대체공휴일", "추석연휴", "추석", "개천절", "한글날", "기독탄신일(성탄절)" -> {
            EventTypes.HOLIDAYS
        }

        else -> {
            EventTypes.ETC
        }
    }
}