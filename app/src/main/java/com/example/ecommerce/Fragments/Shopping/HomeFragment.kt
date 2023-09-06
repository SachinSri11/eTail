package com.example.ecommerce.Fragments.Shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ecommerce.Fragments.categories.AccessoryFragment
import com.example.ecommerce.Fragments.categories.ChairFragment
import com.example.ecommerce.Fragments.categories.CupboardFragment
import com.example.ecommerce.Fragments.categories.FurnitureFragment
import com.example.ecommerce.Fragments.categories.MainCategoryFragment
import com.example.ecommerce.Fragments.categories.TableFragment
import com.example.ecommerce.R
import com.example.ecommerce.adapters.HomeViewpagerAdapter
import com.example.ecommerce.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment: Fragment(R.layout.fragment_home){
private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoriesFragment= arrayListOf<Fragment>(
            MainCategoryFragment(),
            ChairFragment(),
            TableFragment(),
            CupboardFragment(),
            AccessoryFragment(),
            FurnitureFragment()
        )

        val viewpager2adapter=HomeViewpagerAdapter(categoriesFragment,childFragmentManager,lifecycle)
        binding.viewPagerHome.adapter=viewpager2adapter
        TabLayoutMediator(binding.tabLayout,binding.viewPagerHome){
            tab,position->
            when(position){
                0->tab.text="Main"
                1->tab.text="Chair"
                2->tab.text="Cupboard"
                3->tab.text="Table"
                4->tab.text="Accessory"
                5->tab.text="Furniture"
            }
        }.attach()

    }
}