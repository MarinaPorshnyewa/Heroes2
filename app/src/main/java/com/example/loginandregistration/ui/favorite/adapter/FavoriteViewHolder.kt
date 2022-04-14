package com.example.loginandregistration.ui.favorite.adapter

import android.content.Context
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.loginandregistration.R
import com.example.loginandregistration.databinding.ItemDisneyHeroesBinding
import com.example.loginandregistration.model.Data
import com.example.loginandregistration.repository.SharedPreferencesRepositoriy
import com.example.loginandregistration.ui.heroes.adapter.HeroesItemAdapter
import com.example.loginandregistration.ui.heroes.adapter3.ShowsHeroesAdapter

class FavoriteViewHolder(
    private val binding: ItemDisneyHeroesBinding,
    private val context: Context,
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(heroes: Data) {

        Glide.with(context).load(heroes.imageUrl).into(binding.imageViewForHeroes)
        binding.nameTextView.text = heroes.name

        binding.itemRV.run {

            adapter = HeroesItemAdapter(context)
            //layoutManager = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)
            layoutManager = LinearLayoutManager(context)
        }

        (binding.itemRV.adapter as HeroesItemAdapter).setDataList(heroes.films)

        binding.itemShows.run {

            adapter = ShowsHeroesAdapter(context)
            layoutManager = LinearLayoutManager(context)
        }

        (binding.itemShows.adapter as ShowsHeroesAdapter).getDataList(heroes.tvShows)

    }
}