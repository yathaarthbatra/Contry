package com.example.splitwise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.splitwise.databinding.FragmentSetttleResultBinding

class SetttleResultFragment : Fragment() {

    lateinit var binding:FragmentSetttleResultBinding
    lateinit var navController: NavController
    val args:SetttleResultFragmentArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSetttleResultBinding.inflate(layoutInflater)
        navController = findNavController()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setResultText()

        binding.settleResultFragmentButton.setOnClickListener {
            onClickOneMoreSettle()
        }



    }

    private fun setResultText(){
        val resultText = args.resultText
        binding.settleResultFragmentResultText.text = resultText
    }

    private fun onClickOneMoreSettle(){
        val action = SetttleResultFragmentDirections.actionSetttleResultFragmentToHomeFragment()
        navController.navigate(action)
    }


}