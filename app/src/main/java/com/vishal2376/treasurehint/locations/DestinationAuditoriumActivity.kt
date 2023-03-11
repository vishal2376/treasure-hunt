package com.vishal2376.treasurehint.locations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vishal2376.treasurehint.databinding.ActivityDestinationAuditoriumBinding

class DestinationAuditoriumActivity : AppCompatActivity() {
    private var _binding: ActivityDestinationAuditoriumBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDestinationAuditoriumBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}