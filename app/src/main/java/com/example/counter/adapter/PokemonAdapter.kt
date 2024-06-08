package com.example.counter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.counter.R
import com.example.counter.model.Pokemon

class PokemonAdapter(
    private val context: Context,
    private val dataset: List<Pokemon>
) : RecyclerView.Adapter<PokemonAdapter.ItemViewHolder>() {

     val NormalView = 0
     val ButtonView = 1

    override fun getItemViewType(position: Int): Int {
        return if (position == dataset.size - 1 && dataset.size!=1) {
            ButtonView
        } else {
            NormalView
        }
    }

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView = view.findViewById(R.id.pokemonName)
        val imageView: ImageView = view.findViewById(R.id.pokemonImage)
        val textViewType1: TextView = view.findViewById(R.id.pokemonType1)
        val textViewType2: TextView = view.findViewById(R.id.pokemonType2)
        val textDescription: TextView = view.findViewById(R.id.pokemonDescription)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        if(viewType == ButtonView){
            val adapterLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_pokemo_buttons, parent, false)
            return ItemViewHolder(adapterLayout)
        }else{
            val adapterLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_pokemon, parent, false)
            return ItemViewHolder(adapterLayout)
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item = dataset[position]
        holder.textViewName.text = item.name.capitalize()
        Glide.with(context)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${item.id}.png")
            .into(holder.imageView)

        holder.textViewType1.text = item.types[0].type.name
        if (item.types.size > 1) {
            holder.textViewType2.visibility = View.VISIBLE
            holder.textViewType2.text = item.types[1].type.name
        } else {
            holder.textViewType2.visibility = View.GONE
        }
        holder.textDescription.text=item.baseExperience.toString()
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}
