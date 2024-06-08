package com.example.counter.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://pokeapi.co/api/v2/"

// Configura Moshi con il supporto per la riflessione Kotlin
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Configura l'interceptor per il logging delle richieste HTTP
private val logging = HttpLoggingInterceptor().apply {
    setLevel(HttpLoggingInterceptor.Level.BASIC)
}

// Configura OkHttpClient con l'interceptor di logging
private val client = OkHttpClient.Builder()
    .addInterceptor(logging)
    .build()

// Configura Retrofit con la base URL, il convertitore Moshi e l'OkHttpClient
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(client)
    .build()

// Oggetto singleton per accedere all'API di PokeAPI
object RetrofitInstance {
    val api: PokemonApiService by lazy {
        retrofit.create(PokemonApiService::class.java)
    }
}
