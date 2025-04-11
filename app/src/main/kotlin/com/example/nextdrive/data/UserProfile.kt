package com.example.nextdrive.data

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UserProfile(
    @SerialName("id")
    val id: Long = 0,
    @SerialName("created_at")
    val createdAt: Instant = Instant.fromEpochSeconds(0, 0),
    @SerialName("last_name")
    val lastName: String = "Не указано",
    @SerialName("first_name")
    val firstName: String = "Не указано",
    @SerialName("middle_name")
    val middleName: String = "Не указано",
    @SerialName("birth_date")
    val birthDate: LocalDateTime = LocalDateTime(1970, 1,1, 0, 0, 0),
    @SerialName("gender")
    val gender: String = "Не указано",
    @SerialName("license_number")
    val licenseNumber: String? = null,
    @SerialName("issue_date")
    val issueDate: LocalDateTime? = null,
    @SerialName("user_id")
    var userId: String = "00000000-0000-0000-000000000000"
)