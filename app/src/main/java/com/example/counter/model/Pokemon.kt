package com.example.counter.model

import com.squareup.moshi.Json

data class Pokemon(
    val id: Int,
    val name: String,
    @Json(name = "base_experience") val baseExperience: Int,
    val types: List<PokemonType>
)

data class PokemonType(
    val slot: Int,
    val type: Type
)

data class Type(
    val name: String
)
