package com.vishal2376.treasurehint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vishal2376.treasurehint.databinding.ActivityTeamBinding

class TeamActivity : AppCompatActivity() {
    private var _binding: ActivityTeamBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}