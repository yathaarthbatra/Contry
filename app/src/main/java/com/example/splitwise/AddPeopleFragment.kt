package com.example.splitwise

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.splitwise.databinding.FragmentAddPeopleBinding


class AddPeopleFragment : Fragment() {

    lateinit var binding:FragmentAddPeopleBinding
    lateinit var navController:NavController
    val args: AddPeopleFragmentArgs by navArgs()
    var numberOfPeople:Int = 0
    var list = mutableListOf<Person>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentAddPeopleBinding.inflate(layoutInflater)
        navController = findNavController()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val count = args.numberPerson
        numberOfPeople = count

        //for add button
        binding.addPeopleFragmentAddButton.setOnClickListener {
            onClickAdd()
        }

        //for settle Up button
        binding.addPeopleFragmentSettleButton.setOnClickListener {
            Log.d("Yathaarth","OnButtonClicked")
            onClickSettle()
        }


    }

    private fun onClickAdd(){
            //fetching the input details
            val name = binding.addPeopleFragmentInputName.text.toString()
            val expense = binding.addPeopleFragmentInputExpense.text.toString().toInt()


            //clearing the input boxes
            binding.addPeopleFragmentInputName.setText("")
            binding.addPeopleFragmentInputExpense.setText("")

            //hiding the keyboard:
            binding.addPeopleFragmentInputExpense.onEditorAction(EditorInfo.IME_ACTION_DONE)



            //creating a new object
            //calculating the leftover amount to other persons
            val left = expense/numberOfPeople
            val person = Person(name,expense,left)

            //inserting into the list
            if(list.size >= numberOfPeople){
                Toast.makeText(context,"Cannot add more members",Toast.LENGTH_SHORT).show()
            }else{
                list.add(person)
                Toast.makeText(context,"$name added to the Group",Toast.LENGTH_SHORT).show()
            }



    }


    private fun onClickSettle(){
        //in this we have to make a graph of n*n
        //all will be assigned to zero
        val graph = Array(numberOfPeople){IntArray(numberOfPeople)}

        //graph[i][j] means amount i has to pay to j
        for(i in 0 until numberOfPeople){
            for (j in 0 until numberOfPeople){
                if(i == j){
                    //same cannot pay to themselves
                    continue
                }
               val leftOver = list[j].leftOver
                graph[i][j] = leftOver
            }
        }

        val split = SplitAlgo()
        split.setNumberPeople(numberOfPeople)
       val result = split.takeInput(graph, list.toTypedArray())
        Log.d("Yathaarth",result)

        val action = AddPeopleFragmentDirections.actionAddPeopleFragmentToSetttleResultFragment(result)
        navController.navigate(action)



    }


}