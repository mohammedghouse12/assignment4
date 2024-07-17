package com.bootcamp.assignment1.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateUtils {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    fun parseDate(date: String): LocalDate {
        return LocalDate.parse(date, formatter)
    }

    fun formatDate(localDate: LocalDate): String {
        return localDate.format(formatter)
    }

    fun calculateAge(dateOfBirth: String): Int {
        val birthDate: LocalDate = parseDate(dateOfBirth)

        val currentDate = LocalDate.now()
        var age = currentDate.year - birthDate.year

        if (currentDate.month < birthDate.month ||
            (currentDate.month == birthDate.month && currentDate.dayOfMonth < birthDate.dayOfMonth)
        )
            age--

        return age
    }
}