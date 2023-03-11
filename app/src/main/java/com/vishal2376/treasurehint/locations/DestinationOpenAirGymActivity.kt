package com.vishal2376.treasurehint.locations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vishal2376.treasurehint.databinding.ActivityDestinationOpenAirGymBinding

class DestinationOpenAirGymActivity : AppCompatActivity() {
    private var _binding: ActivityDestinationOpenAirGymBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDestinationOpenAirGymBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}