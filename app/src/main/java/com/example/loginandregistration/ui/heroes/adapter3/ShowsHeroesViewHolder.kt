package com.example.loginandregistration.ui.heroes.adapter3

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.loginandregistration.databinding.ItemChipsForFilmsBinding

class ShowsHeroesViewHolder(
    private val binding: ItemChipsForFilmsBinding,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(show: String) {

        binding.chip.text = show
    }
}