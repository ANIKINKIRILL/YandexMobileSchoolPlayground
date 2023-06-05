package com.anikinkirill.playground.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String,
)