package com.example.ecommerce.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ecommerce.R
import com.example.ecommerce.databinding.ActivityShoppingBinding

class ShoppingActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityShoppingBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val navController=findNavController(R.id.shoppingHostFragment)
        binding.bottomNavigation.setupWithNavController(navController)

    }
}