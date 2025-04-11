package com.example.nextdrive.data

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//@Serializable
//data class Car(
//    val id: String,
//    val brand: String,
//    val model: String,
//    val year: Int,
//    val pricePerDay: Int,
//    val type: String,
//    val imageUrl: String? = null
//)

val default_car_image = "https://i3.storeland.net/1/9686/96858715/afacdb/shopitemscatalogimage3026-jpg.jpg"

@Serializable
data class Car(
    @SerialName("id")
    val id: Long = 0,
    @SerialName("created_at")
    val createdAt: Instant = Instant.fromEpochSeconds(0, 0),
    @SerialName("brand")
    val brand: String? = null,
    @SerialName("model")
    val model: String? = null,
    @SerialName("year")
    val year: Int? = null,
    @SerialName("price_per_day")
    val pricePerDay: String? = null,
    @SerialName("type")
    val type: String? = null,
    @SerialName("image_url")
    val imageUrl: String? = default_car_image,
)