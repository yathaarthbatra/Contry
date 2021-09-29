package com.example.splitwise

import android.app.UiModeManager.MODE_NIGHT_YES
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.splitwise.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    var isDarkModeOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("Yathaarth","OnCreate Called")
        val sharedPref = getSharedPreferences("SharedPref", MODE_PRIVATE)
        isDarkModeOn = sharedPref.getBoolean("DARK_MODE",false)
        Log.d("Yathaarth","isDarkModeOn = $isDarkModeOn")

       // Log.d("Yathaarth","isDarkModeOn=$isDarkModeOn")
        setTheme(R.style.Theme_SplitWise)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(isDarkModeOn){
            //setting it to darkMode
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        else{
            //setting it to light mode
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        setupNavigation()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater:MenuInflater = menuInflater
        if(isDarkModeOn){
            inflater.inflate(R.menu.action_bar_dark,menu)
        }
        else{
            inflater.inflate(R.menu.action_bar,menu)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("Yathaarth","OnOptionsSelected called")
        return when(item.itemId){
            R.id.lightSwitch -> {
                //if the icon touched is light mode icon then we have to make it dark mode by changing the icon
                //and switch to dark mode

                //first changing the icon to dark mode having on Icon
                item.icon = ResourcesCompat.getDrawable(resources,R.drawable.ic_baseline_toggle_on_24,applicationContext.theme)

                //setting the dark mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

                //storing the dark mode in sharedPreferences
                val sharedPref = getSharedPreferences("SharedPref", MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putBoolean("DARK_MODE",true)
                editor.apply()
                true
            }
            R.id.darkSwitch -> {
                //if the icon clicked is dark one then it means we have to change it to light mode and change icon to light mode ie off

                //first changing the icon to light mode ie off
                item.icon = ResourcesCompat.getDrawable(resources,R.drawable.ic_baseline_toggle_off_24,applicationContext.theme)

                //now changing the mode to LightMode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

                //now saving the value ie light mode
                val sharedPref = getSharedPreferences("SharedPref", MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putBoolean("DARK_MODE",false)
                editor.apply()
                return true
            }
            else ->{
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun setupNavigation(){
        val bottomNavigationView = binding.bottomNavigation
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        NavigationUI.setupWithNavController(bottomNavigationView,navHostFragment.navController)
    }



}