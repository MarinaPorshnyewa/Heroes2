package com.example.loginandregistration.ui.heroes.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.loginandregistration.databinding.ItemChipsForFilmsBinding
import com.example.loginandregistration.databinding.ItemDisneyHeroesBinding
import com.example.loginandregistration.model.Data
import com.example.loginandregistration.model.DisneyHeroes


class HeroesItemViewHolder(
    private val binding: ItemChipsForFilmsBinding,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(film: String) {

        binding.chip.text = film
    }

}