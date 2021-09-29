package com.example.splitwise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.splitwise.databinding.FragmentQuickSettlementNumberPeopleBinding


class QuickSettlementNumberPeople : Fragment() {

   lateinit var binding:FragmentQuickSettlementNumberPeopleBinding
   lateinit var navController:NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuickSettlementNumberPeopleBinding.inflate(inflater,container,false)
        navController = findNavController()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //for next Button
        //take input value and navigate through action
        binding.quickSettlementNumberPeopleNextButton.setOnClickListener {
            //first retrieve the value
            val count = binding.quickSettlementNumberPeopleInputPeople.text.toString().toInt()
            val action = QuickSettlementNumberPeopleDirections.actionQuickSettlementNumberPeopleToAddPeopleFragment(count)
            navController.navigate(action)
        }
    }


}