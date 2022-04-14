package com.example.loginandregistration.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.loginandregistration.R
import com.example.loginandregistration.databinding.FragmentRegistrationBinding
import com.example.loginandregistration.ui.heroes.DisneyHeroesFragment
import com.google.firebase.auth.FirebaseAuth

class RegistrationFragment : Fragment() {

    lateinit var binding: FragmentRegistrationBinding

    private lateinit var auth: FirebaseAuth

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        auth = FirebaseAuth.getInstance()

        binding.registrationButton.setOnClickListener {
            signUpUser()
        }
    }

    private fun signUpUser() {

        auth.createUserWithEmailAndPassword(
            binding.emailRegistrationEditText.text.toString(), binding.passwordRegistrationEditText.text.toString()
        ).addOnCompleteListener {

            binding.progressViewRegistration.visibility = View.VISIBLE

            if (it.isSuccessful) {
                Toast.makeText(context, "Successfully Singed Up", Toast.LENGTH_SHORT).show()

                navController.navigate(RegistrationFragmentDirections.actionRegistrationFragmentToDisneyHeroesFragment())

            } else {
                Toast.makeText(context, "Singed Up Failed!", Toast.LENGTH_SHORT).show()
            }
            binding.progressViewRegistration.visibility = View.GONE
        }
    }
}