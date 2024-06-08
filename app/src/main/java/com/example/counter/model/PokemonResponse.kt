package com.example.counter.model

data class PokemonResponse(
    val count: Int,
    val results: List<PokemonResult>
)

data class PokemonResult(
    val name: String,
    val url: String
)



