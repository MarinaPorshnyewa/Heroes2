package com.example.loginandregistration.ui.heroes

import androidx.recyclerview.widget.DiffUtil
import com.example.loginandregistration.model.Data

object DisneyHeroComparator : DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data) =
        oldItem._id == newItem._id

    override fun areContentsTheSame(oldItem: Data, newItem: Data) =
        oldItem.allies == newItem.allies
                && oldItem.films == newItem.films
                && oldItem.name == newItem.name
}