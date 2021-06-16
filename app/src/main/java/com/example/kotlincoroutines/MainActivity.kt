package com.example.kotlincoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.example.kotlincoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity()
{
    val TAG = "1234"

    lateinit var binding: ActivityMainBinding
    private  var coroutineScope:CoroutineScope = CoroutineScope(Dispatchers.Main)
    private var elapsedTimeJob:Job? = null


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonGet.isEnabled = false
        binding.editText.addTextChangedListener(object :TextWatcher
        {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int)
            {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
            {
                binding.buttonGet.isEnabled = !s.isNullOrEmpty()
            }

            override fun afterTextChanged(s: Editable?)
            {
            } }
        ) //

        binding.buttonGet.setOnClickListener()
        {
            binding.buttonGet.isEnabled = false


            elapsedTimeJob = coroutineScope.launch ()
            {

                val startTime = System.nanoTime()
                while (true)
                {
                    delay(1000)
                    val elapsedTime = System.nanoTime() - startTime
                    val elapsedTimeInMillis = elapsedTime/1000000
                    binding.textView.text = "elapsedTime : "+elapsedTimeInMillis
                } // while closed
            } /// elapsedTimeJob
            coroutineScope.launch()
            {
                val reputation = getReputationFromUser(binding.editText.text.toString())
                Toast.makeText(applicationContext,""+reputation,Toast.LENGTH_SHORT).show()
                binding.editText.text.clear()
                binding.buttonGet.isEnabled =true
                elapsedTimeJob?.cancel()
            }

        }

    }
      suspend  fun getReputationFromUser(toString: String) :Int
    {
        return withContext(Dispatchers.Default)
        {
            GetReputationEndpoint().getReputation(toString)
        } //
    } // func closed

    override fun onStop()
    {
        super.onStop()
        coroutineScope.coroutineContext.cancelChildren()
        binding.buttonGet.isEnabled =true
    }

} // MainActivity closed

