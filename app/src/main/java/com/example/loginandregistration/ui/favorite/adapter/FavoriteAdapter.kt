package com.example.loginandregistration.ui.favorite.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loginandregistration.databinding.ItemDisneyHeroesBinding
import com.example.loginandregistration.model.Data
import com.example.loginandregistration.ui.heroes.adapter.HeroesViewHolder

class FavoriteAdapter(
    private val context: Context
) : RecyclerView.Adapter<FavoriteViewHolder>() {

    private var list = arrayListOf<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemDisneyHeroesBinding.inflate(LayoutInflater.from(context))
        return FavoriteViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun setDataList(heroes: ArrayList<Data>) {

        list = heroes
        notifyDataSetChanged()
    }
}