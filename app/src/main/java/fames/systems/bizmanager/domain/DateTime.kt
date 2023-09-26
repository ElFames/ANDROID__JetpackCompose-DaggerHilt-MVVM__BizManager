package fames.systems.bizmanager.domain

import kotlinx.serialization.Serializable
import java.util.Calendar

@Serializable
data class DateTime(
    val day: Int,
    val month: Int,
    val year: Int,
    val hour: Int,
    val minute: Int,
    val second: Int
)

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