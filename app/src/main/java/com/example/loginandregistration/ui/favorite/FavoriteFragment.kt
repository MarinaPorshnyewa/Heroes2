package com.example.loginandregistration.ui.favorite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginandregistration.MyApplication
import com.example.loginandregistration.databinding.FragmentDisneyHeroesBinding
import com.example.loginandregistration.databinding.FragmentFavoriteBinding
import com.example.loginandregistration.model.Data
import com.example.loginandregistration.repository.SharedPreferencesRepositoriy
import com.example.loginandregistration.ui.favorite.adapter.FavoriteAdapter
import com.example.loginandregistration.ui.heroes.DisneyHeroesFragmentDirections
import com.example.loginandregistration.ui.heroes.HeroesViewModel
import com.example.loginandregistration.ui.heroes.HeroesViewModelFactory
import com.example.loginandregistration.ui.heroes.adapter3.ShowsHeroesAdapter
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding

    private lateinit var navController: NavController


    @Inject
    lateinit var sharePreferences: SharedPreferencesRepositoriy

    private lateinit var viewModel: HeroesViewModel

    @Inject
    lateinit var viewModelProvider: HeroesViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MyApplication.appComponent.inject2(this)
        viewModel = ViewModelProvider(this, viewModelProvider)
            .get(HeroesViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel.showProgress = {
            requireActivity().runOnUiThread {

                binding.progressViewFavorite.visibility = if (it) {
                    View.VISIBLE
                } else View.GONE
            }
        }

        navController = view.findNavController()

        binding.backButton.setOnClickListener {
            navController.popBackStack()
        }

        viewModel.getHeroesOnID(sharePreferences.getIdHeroes()!!)

        viewModel.loadImages2 = {
            requireActivity().runOnUiThread {
                binding.vaforiteRecyclerView.run {

                    adapter = FavoriteAdapter(context)
                    layoutManager = LinearLayoutManager(context)

                }

                (binding.vaforiteRecyclerView.adapter as FavoriteAdapter).setDataList(it as ArrayList<Data>)

            }
        }

        binding.text.append(sharePreferences.getIdHeroes().toString())

    }

}