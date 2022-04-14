package com.example.loginandregistration.ui

import android.content.Intent
import android.icu.util.TimeUnit
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.loginandregistration.R
import com.example.loginandregistration.databinding.FragmentLoginBinding
import com.example.loginandregistration.ui.heroes.DisneyHeroesFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.xml.datatype.DatatypeConstants.SECONDS

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private lateinit var auth: FirebaseAuth

    private lateinit var mVerificationId: String
    private lateinit var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    private lateinit var launcher: ActivityResultLauncher<Intent>

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        auth = FirebaseAuth.getInstance()

        binding.authorizationButton.setOnClickListener {

            binding.progressViewLogin.visibility = View.VISIBLE

            auth.signInWithEmailAndPassword(
                binding.emailEditText.text.toString(), binding.passwordEditText.text.toString()
            ).addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    navController.navigate(LoginFragmentDirections.actionLoginFragmentToDisneyHeroesFragment())

                } else {
                    Toast.makeText(context, "ERROR", Toast.LENGTH_LONG).show()
                }
                binding.progressViewLogin.visibility = View.GONE
            }
        }

        binding.registrationButton.setOnClickListener {

            navController.navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment())
        }


        //Phone
        mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {

                signInWithPhoneAuthCredential(phoneAuthCredential)
            }

            override fun onVerificationFailed(p0: FirebaseException) {

            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                super.onCodeSent(verificationId, token)

                mVerificationId = verificationId

                binding.codeEditText.visibility = View.VISIBLE
                binding.phoneEditText.visibility = View.GONE
                binding.authorizationPhoneButton.visibility = View.GONE

            }
        }

        binding.authorizationPhoneButton.setOnClickListener {

            val phone = binding.phoneEditText.text.toString()
            startPhoneNumberVerification(phone)

        }

        binding.checkCodeButton.setOnClickListener {

            val code = binding.codeEditText.text.toString()

            verifyPhoneNumberWithCode(mVerificationId, code)
        }


        //Google
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {

                val account = task.getResult(ApiException::class.java)

                if (account != null) {
                    firebaseAuthWithGoogle(account.idToken!!)
                }

            } catch (e: ApiException) {
                Toast.makeText(context, "ERROR API", Toast.LENGTH_LONG).show()
            }
        }

        binding.signInGoogleButton.setOnClickListener {
            signInWithGoogle()
        }

        checkAuthState()
    }


    //Phone
    private fun startPhoneNumberVerification(phoneNumber: String) {

        binding.progressViewLogin.visibility = View.VISIBLE
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, java.util.concurrent.TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(mCallbacks)
            .build()


        PhoneAuthProvider.verifyPhoneNumber(options)
        binding.progressViewLogin.visibility = View.GONE
    }


    private fun verifyPhoneNumberWithCode(verficationId: String, code: String) {

        binding.progressViewLogin.visibility = View.VISIBLE
        val credential = PhoneAuthProvider.getCredential(verficationId, code)


        signInWithPhoneAuthCredential(credential)
        binding.progressViewLogin.visibility = View.GONE
    }


    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {

        binding.progressViewLogin.visibility = View.VISIBLE
        auth.signInWithCredential(credential).addOnCompleteListener {

            if (it.isSuccessful) {

                val phone = auth.currentUser?.phoneNumber

                navController.navigate(LoginFragmentDirections.actionLoginFragmentToDisneyHeroesFragment())

            } else {

            }
        }
        binding.progressViewLogin.visibility = View.GONE
    }


    //Google
    private fun getClient(): GoogleSignInClient {

        binding.progressViewLogin.visibility = View.VISIBLE
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        binding.progressViewLogin.visibility = View.GONE
        return GoogleSignIn.getClient(requireActivity(), gso)

    }

    private fun signInWithGoogle() {
        binding.progressViewLogin.visibility = View.VISIBLE
        val signInClient = getClient()
        launcher.launch(signInClient.signInIntent)
        binding.progressViewLogin.visibility = View.GONE
    }

    private fun firebaseAuthWithGoogle(idToken: String) {

        binding.progressViewLogin.visibility = View.VISIBLE
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {

                checkAuthState()

            } else {
                Toast.makeText(context, "ERROR", Toast.LENGTH_LONG).show()
            }
        }
        binding.progressViewLogin.visibility = View.GONE
    }

    private fun checkAuthState() {

        if (auth.currentUser != null) {
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToDisneyHeroesFragment())
        }
    }
}
