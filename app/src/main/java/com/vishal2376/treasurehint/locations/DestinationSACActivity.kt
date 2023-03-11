package com.vishal2376.treasurehint.locations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vishal2376.treasurehint.databinding.ActivityDestinationSacactivityBinding

class DestinationSACActivity : AppCompatActivity() {
    private var _binding: ActivityDestinationSacactivityBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDestinationSacactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}