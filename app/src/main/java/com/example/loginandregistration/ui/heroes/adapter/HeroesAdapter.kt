package com.example.loginandregistration.ui.heroes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.loginandregistration.databinding.ItemDisneyHeroesBinding
import com.example.loginandregistration.model.Data
import com.example.loginandregistration.ui.heroes.DisneyHeroComparator

class HeroesAdapter(
    private val context: Context
) : PagingDataAdapter<Data, HeroesViewHolder>(DisneyHeroComparator) {

    //private var list = arrayListOf<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val binding = ItemDisneyHeroesBinding.inflate(LayoutInflater.from(context))


        return HeroesViewHolder(binding, context).apply {
            setIsRecyclable(false)
        }
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {


        //holder.bind(list[position])
        getItem(position)?.let {
            holder.bind(it)
            //holder.setIsRecyclable(false)
        }

    }

    //override fun getItemCount(): Int = list.size

    /*fun setDataList(heroes: ArrayList<Data>) {

        list = heroes
        notifyDataSetChanged()
    }*/


}