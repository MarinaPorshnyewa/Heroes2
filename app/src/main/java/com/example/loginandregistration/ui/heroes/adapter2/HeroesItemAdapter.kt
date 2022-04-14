package com.example.loginandregistration.ui.heroes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loginandregistration.databinding.ItemChipsForFilmsBinding
import com.example.loginandregistration.databinding.ItemDisneyHeroesBinding
import com.example.loginandregistration.model.Data

class HeroesItemAdapter(
    private val context: Context
) : RecyclerView.Adapter<HeroesItemViewHolder>() {

    private var list = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesItemViewHolder {
        val binding = ItemChipsForFilmsBinding.inflate(LayoutInflater.from(context))
        return HeroesItemViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: HeroesItemViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun setDataList(heroes: ArrayList<String>) {

        list = heroes
        notifyDataSetChanged()
    }
}