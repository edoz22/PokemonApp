package com.example.counter.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.counter.model.Pokemon
import com.example.counter.network.RetrofitInstance
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {

    private val _pokemonList = MutableLiveData<List<Pokemon>>()
    val pokemonList: LiveData<List<Pokemon>> get() = _pokemonList
    var limit: Int = 20



    fun fetchPokemonName(pokemonName: String) {
        viewModelScope.launch {
            try {
                Log.d("PokemonViewModel", "Fetching Pokémon with ID: $pokemonName")
                val pokemon = RetrofitInstance.api.getPokemonByName(pokemonName)
                Log.d("PokemonViewModel", "Fetched Pokémon: ${pokemon.name}")
                _pokemonList.value = listOf(pokemon)

            } catch (e: Exception) {
                Log.e("PokemonViewModel", "Error fetching Pokémon", e)
                // Gestisci l'errore
                  "Error fetching $pokemonName"
            }
        }
    }



    fun fetchPokemonList(offset:Int) {
        viewModelScope.launch {
            try {
                Log.d("PokemonViewModel", "Fetching Pokémon list: Page ")
                val response = RetrofitInstance.api.getPokemonList(limit, limit * offset)
                val pokemonDetails = response.results.map { result ->
                    val id = result.url.split("/").filter { it.isNotEmpty() }.last().toInt()
                    RetrofitInstance.api.getPokemon(id)
                }
                _pokemonList.value =  pokemonDetails

            } catch (e: Exception) {
                Log.e("PokemonViewModel", "Error fetching Pokémon list", e)
            }
        }
    }


}
