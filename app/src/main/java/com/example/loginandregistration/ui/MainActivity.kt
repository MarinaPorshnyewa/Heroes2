package com.example.loginandregistration.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.loginandregistration.R
import com.example.loginandregistration.model.DisneyHeroes
import com.example.loginandregistration.ui.heroes.DisneyHeroesFragment

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.navHostFragment)
        navController.graph = navController.navInflater.inflate(R.navigation.main_graph)


        //supportFragmentManager
        //    .beginTransaction()
        //    .replace(R.id.container, LoginFragment())
        //    .commit()
    }

}