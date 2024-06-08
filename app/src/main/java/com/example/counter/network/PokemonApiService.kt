package com.example.counter.network

import com.example.counter.model.Pokemon
import com.example.counter.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {
    // Metodo per ottenere una lista di Pokémon con paginazione
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,   //numero massimo di grandezza della lista
        @Query("offset") offset: Int  //
    ): PokemonResponse

    // Metodo per ottenere i dettagli di un singolo Pokémon tramite ID
    @GET("pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") id: Int
    ): Pokemon

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(
        @Path("name") name: String
    ): Pokemon
}
