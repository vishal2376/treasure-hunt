package com.vishal2376.treasurehint.locations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vishal2376.treasurehint.databinding.ActivityDestination4HactivityBinding

class Destination4HActivity : AppCompatActivity() {
    private var _binding: ActivityDestination4HactivityBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDestination4HactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}