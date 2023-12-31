package com.example.ecommerce.Fragments.loginRegister

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.ecommerce.R
import com.example.ecommerce.activities.ShoppingActivity
import com.example.ecommerce.databinding.FragmentLoginBinding
import com.example.ecommerce.util.Resource
import com.example.ecommerce.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class loginFragment:Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            buttonLoginLogin.setOnClickListener {
                val email=editTextTextEmailAddress.text.toString().trim()
                val password=editTextTextPassword.text.toString()
                viewModel.login(email, password)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.login.collect{
                when(it){
                    is Resource.Loading->{
                        binding.buttonLoginLogin.startAnimation()
                    }
                    is Resource.Success->{
                        binding.buttonLoginLogin.revertAnimation()
                       Intent(requireActivity(),ShoppingActivity::class.java).also {
                           intent -> intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                           startActivity(intent)
                       }
                    }
                    is Resource.Error->{
                        Toast.makeText(requireActivity(), it.message, Toast.LENGTH_LONG).show()
                        binding.buttonLoginLogin.revertAnimation()
                    }
                    else->Unit
                }
            }
        }
    }

}