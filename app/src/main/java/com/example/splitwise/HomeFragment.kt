package com.example.splitwise

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.splitwise.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding:FragmentHomeBinding
    lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        navController = findNavController()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //for quick settle button
        binding.quickSettleButton.setOnClickListener {

            val action = HomeFragmentDirections.actionHomeFragmentToQuickSettlementNumberPeople()
            navController.navigate(action)
        }

        //for detail Settle Button
        binding.detailSettleButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddGroupFragment()
            navController.navigate(action)

        }



        }
    }






