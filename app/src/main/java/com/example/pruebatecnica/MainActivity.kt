package com.example.pruebatecnica

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebatecnica.databinding.ActivityMainBinding
import com.example.pruebatecnica.jokes.JokesActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        JokesActivity.startActivity(this)
    }
}