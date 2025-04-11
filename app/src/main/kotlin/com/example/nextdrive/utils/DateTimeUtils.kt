package com.example.nextdrive.utils

import kotlinx.datetime.*
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException


object DateTimeUtils {
    private const val DATE_PATTERN = "dd.MM.yyyy"
    private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN)

    /**
     * Преобразует строку в формате DD.MM.YYYY в LocalDate
     */
    fun parseDate(dateString: String): LocalDate? {
        return try {
            val javaDate = java.time.LocalDate.parse(dateString, dateFormatter)
            LocalDate(javaDate.year, javaDate.monthValue, javaDate.dayOfMonth)
        } catch (e: DateTimeParseException) {
            null
        }
    }

    /**
     * Преобразует строку в формате DD.MM.YYYY в LocalDateTime (время будет 00:00)
     */
    fun parseDateTime(dateString: String): LocalDateTime? {
        return parseDate(dateString)?.atTime(0, 0)
    }

    /**
     * Форматирует LocalDate в строку DD.MM.YYYY
     */
    fun formatDate(date: LocalDate): String {
        val javaDate = java.time.LocalDate.of(date.year, date.monthNumber, date.dayOfMonth)
        return javaDate.format(dateFormatter)
    }

    /**
     * Форматирует LocalDateTime в строку DD.MM.YYYY
     */
    fun formatDateTime(dateTime: LocalDateTime): String {
        return formatDate(dateTime.date)
    }
}
