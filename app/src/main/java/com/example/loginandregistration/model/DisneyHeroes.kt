package com.example.loginandregistration.model

data class DisneyHeroes(
    val count: Int,
    val `data`: ArrayList<Data>,
    val nextPage: String,
    val totalPages: Int
)

data class Data(
    val _id: Int,
    val allies: List<Any>,
    val enemies: List<Any>,
    val films: ArrayList<String>,
    val imageUrl: String,
    val name: String,
    val parkAttractions: List<String>,
    val shortFilms: List<String>,
    val tvShows: ArrayList<String>,
    val url: String,
    val videoGames: List<String>
)