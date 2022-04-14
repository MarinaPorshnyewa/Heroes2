package com.example.loginandregistration.ui.heroes.adapter3

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loginandregistration.databinding.ItemChipsForFilmsBinding

class ShowsHeroesAdapter(
    private val context: Context
) : RecyclerView.Adapter<ShowsHeroesViewHolder>() {

    private var list = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowsHeroesViewHolder {
        val binding = ItemChipsForFilmsBinding.inflate(LayoutInflater.from(context))
        return ShowsHeroesViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: ShowsHeroesViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size


    fun getDataList(shows: ArrayList<String>) {

        list = shows
        notifyDataSetChanged()
    }
}