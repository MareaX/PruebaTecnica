package com.example.pruebatecnica.jokes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.pruebatecnica.R
import com.example.pruebatecnica.databinding.ActivityJokesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JokesActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityJokesBinding
    private lateinit var navController: NavController

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, JokesActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        onNavComponentCreated()
    }

    private fun initView() {
        _binding = ActivityJokesBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.show()
    }

    private fun onNavComponentCreated() {
        navController = Navigation.findNavController(this, R.id.main_content_jokes)
        val navGraph = navController.navInflater.inflate(R.navigation.nav_jokes)

        navController.setGraph(navGraph, null)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.category_fragment -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }

                else -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                }
            }
        }
        navController.navigate(R.id.category_fragment)
    }

    override fun onBackPressed() {
        when (navController.currentDestination?.id) {
            R.id.category_fragment -> Unit
            else -> {
                navController.popBackStack()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}