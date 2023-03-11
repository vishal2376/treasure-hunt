package com.vishal2376.treasurehint.locations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vishal2376.treasurehint.databinding.ActivityDestinationGroundBinding

class DestinationGroundActivity : AppCompatActivity() {
    private var _binding: ActivityDestinationGroundBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDestinationGroundBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}