package com.example.loginandregistration.ui.heroes

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginandregistration.MyApplication
import com.example.loginandregistration.R
import com.example.loginandregistration.databinding.FragmentDisneyHeroesBinding
import com.example.loginandregistration.model.Data
import com.example.loginandregistration.repository.SharedPreferencesRepositoriy
import com.example.loginandregistration.ui.heroes.adapter.HeroesAdapter
import com.example.loginandregistration.ui.heroes.adapter.HeroesItemAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val SECONDS = 5000L

class DisneyHeroesFragment : Fragment() {

    private lateinit var binding: FragmentDisneyHeroesBinding

    private lateinit var viewModel: HeroesViewModel

    private lateinit var navController: NavController

    @Inject
    lateinit var sharePreferences: SharedPreferencesRepositoriy

    @Inject
    lateinit var viewModelProvider: HeroesViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDisneyHeroesBinding.inflate(inflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MyApplication.appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelProvider)
            .get(HeroesViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.showProgress = {
            requireActivity().runOnUiThread {

                binding.progressView.visibility = if (it) {
                    View.VISIBLE
                } else View.GONE
            }
        }

        if (sharePreferences.getState()) {

            binding.emotionalFaceView.visibility = View.VISIBLE
            lifecycleScope.launch {
                delay(SECONDS)
                binding.emotionalFaceView.visibility = View.GONE
            }
            sharePreferences.saveState(false)
        }

        navController = view.findNavController()

        binding.favoriteHeroesButton.setOnClickListener {
            navController.navigate(DisneyHeroesFragmentDirections.actionDisneyHeroesFragmentToFavoriteFragment())

        }

        //viewModel.getHeroesList()

        binding.heroesRecyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.left = 20    // Left Margin.
                outRect.right = 20   // Right Margin.
                outRect.top = 16     // Top Margin.
                outRect.bottom = 16  // Bottom Margin.
            }
        })

        //viewModel.loadImages = {
          //  requireActivity().runOnUiThread {

                binding.heroesRecyclerView.run {

                    adapter = HeroesAdapter(requireContext())
                    layoutManager = LinearLayoutManager(requireContext())

                }

                //(binding.heroesRecyclerView.adapter as HeroesAdapter).setDataList(it)
                lifecycleScope.launch {
                    viewModel.result.collectLatest {
                        //binding.progressView.visibility = View.VISIBLE
                        setData(it)
                        //binding.progressView.visibility = View.GONE
                    }

                }

            //}
        //}
    }
    private fun setData(list: PagingData<Data>) {
        (binding.heroesRecyclerView.adapter as HeroesAdapter).submitData(lifecycle, list)
    }


}