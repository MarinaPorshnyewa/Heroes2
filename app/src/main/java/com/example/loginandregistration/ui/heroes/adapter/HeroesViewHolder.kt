package com.example.loginandregistration.ui.heroes.adapter

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.loginandregistration.databinding.ItemDisneyHeroesBinding
import com.example.loginandregistration.model.Data
import com.example.loginandregistration.model.DisneyHeroes
import com.example.loginandregistration.repository.HeroesRepository
import com.example.loginandregistration.ui.heroes.adapter3.ShowsHeroesAdapter
import org.intellij.lang.annotations.JdkConstants


import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProvider
import com.example.loginandregistration.R
import com.example.loginandregistration.repository.SharedPreferencesRepositoriy
import com.example.loginandregistration.ui.heroes.HeroesViewModel
import com.example.loginandregistration.ui.heroes.HeroesViewModelFactory


class HeroesViewHolder(
    private val binding: ItemDisneyHeroesBinding,
    private val context: Context,
) : RecyclerView.ViewHolder(binding.root) {

    lateinit var sharePreferences: SharedPreferencesRepositoriy

    private var list: MutableSet<String> = mutableSetOf()

    init {
        setIsRecyclable(false)
    }

    fun bind(heroes: Data) {


        sharePreferences = SharedPreferencesRepositoriy(context)

        Glide.with(context).load(heroes.imageUrl).into(binding.imageViewForHeroes)
        binding.nameTextView.text = heroes.name

        binding.favoriteImage.startAnimation(
            AnimationUtils.loadAnimation(
                context,
                R.anim.anim_favorites
            )
        )

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

        binding.favoriteImage.setOnClickListener {




            if(sharePreferences.getIdHeroes()!!.contains(heroes._id.toString())){

                list = sharePreferences.getIdHeroes()!!
                list.remove(heroes._id.toString())
                sharePreferences.saveIdHeroes(list)

                binding.favoriteImage.setImageResource(R.drawable.ic_star_black)
            }else{
                list = sharePreferences.getIdHeroes()!!
                list.add(heroes._id.toString())
                sharePreferences.saveIdHeroes(list)

                binding.favoriteImage.setImageResource(R.drawable.ic_star)
            }
        }

        if(sharePreferences.getIdHeroes()!!.contains(heroes._id.toString())){
            binding.favoriteImage.setImageResource(R.drawable.ic_star)
        }

    }
}