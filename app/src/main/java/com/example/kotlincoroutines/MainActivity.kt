package com.example.kotlincoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.kotlincoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity()
{
    val  TAG = "1234"

    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch(Dispatchers.Main)
        {
            delay(3000)
            binding.textViewMain.text = executeTask().toString()
        }

    }

    private suspend fun executeTask() : Int
    {
       return withContext(Dispatchers.Default)
       {
           var a:Int = 0
           for(i in 1..10)
           {
               a++
           }
           a
       }
    }
}