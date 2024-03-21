package fames.systems.bizmanager.domain.models

import fames.systems.bizmanager.infrastructure.utils.values.Formats
import kotlinx.serialization.Serializable
import java.time.DayOfWeek
import java.util.Calendar

@Serializable
data class DateTime(
    val day: Int,
    val month: Int,
    val year: Int,
    val hour: Int,
    val minute: Int,
    val second: Int
) {
    fun getStringDateTime(): String {
        return "${Formats.time(day)}/${Formats.time(month)}/${Formats.time(year)}" +
                " - ${Formats.time(hour)}:${Formats.time(minute)}:${Formats.time(second)}"
    }
}

fun getCurrentDateTime(): DateTime {
    val currentDateTime = Calendar.getInstance()
    return DateTime(
        currentDateTime.get(Calendar.DAY_OF_MONTH),
        currentDateTime.get(Calendar.MONTH) + 1,
        currentDateTime.get(Calendar.YEAR),
        currentDateTime.get(Calendar.HOUR_OF_DAY),
        currentDateTime.get(Calendar.MINUTE),
        currentDateTime.get(Calendar.SECOND)
    )
}

fun getTodayRangeDate(): Pair<DateTime,DateTime> {
    val today = getCurrentDateTime()
    return Pair(
        DateTime(today.day, today.month, today.year, 0, 0, 0),
        DateTime(today.day, today.month, today.year, 23, 59, 59)
    )
}

fun getWeekRangeDate(): Pair<DateTime,DateTime> {
    val today = getCurrentDateTime()
    val dayOfWeek = DayOfWeek.of(today.day)
    val startWeek = today.copy(day = today.day - dayOfWeek.value + 1)
    val endWeek = today.copy(day = today.day + (7 - dayOfWeek.value))
    return Pair(
        DateTime(startWeek.day, startWeek.month, startWeek.year, 0, 0, 0),
        DateTime(endWeek.day, endWeek.month, endWeek.year, 23, 59, 59)
    )
}

fun getMonthRangeDate(): Pair<DateTime,DateTime> {
    val today = getCurrentDateTime()
    val startMonth = today.copy(day = 1)
    val endMonth = today.copy(day = today.day + (30 - today.day))
    return Pair(
        DateTime(startMonth.day, startMonth.month, startMonth.year, 0, 0, 0),
        DateTime(endMonth.day, endMonth.month, endMonth.year, 23, 59, 59)
    )
}
