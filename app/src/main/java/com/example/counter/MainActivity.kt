package com.example.counter

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.counter.adapter.PokemonAdapter
import com.example.counter.viewmodel.PokemonViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: PokemonViewModel by viewModels()

    var offset:Int = 0


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val searchView: SearchView = findViewById(R.id.searchView)


        viewModel.pokemonList.observe(this, Observer { lista ->
            recyclerView.adapter = PokemonAdapter(this, lista)
        })
        viewModel.fetchPokemonList(offset)





       recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (viewModel.pokemonList.value?.size!=1){
                    if (!recyclerView.canScrollVertically(1)) {
                        var buttonSuc: Button = findViewById(R.id.button2)
                        buttonSuc.setOnClickListener {
                            offset++
                            viewModel.fetchPokemonList(offset) }
                        var buttonPrev: Button = findViewById(R.id.button)
                        buttonPrev.setOnClickListener {
                            if (offset>0){
                                offset--
                                viewModel.fetchPokemonList(offset)
                            }
                        }

                    }
                }
            }
        })

        // Set a listener for the SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.fetchPokemonName(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText==""){
                    viewModel.fetchPokemonList(offset)
                }
                return true
            }
        })





        }





    }







        /*val textProva = findViewById<TextView>(R.id.textProva)
        viewModel.pokemonName.observe(this, Observer { name ->
            textProva.text = name
        })
        viewModel.fetchPokemonName(1)*/

// Recupera il nome del Pokémon con ID 1 (Bulbasaur)
      //  viewModel.fetchPokemonName(1)


       //val textProva = findViewById<TextView>(R.id.textProva)
        //textProva.text = viewModel.pokemonList.value?.get(0)?.name ?: "lol"

        /*viewModel.pokemonList.observe(this, Observer { pokemonList ->
            recyclerView.adapter = PokemonAdapter(this, pokemonList)
        })*/

        // Carica altri Pokémon quando si raggiunge il fondo della lista
      /*  recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.fetchPokemonList()
                }
            }
        })*/


