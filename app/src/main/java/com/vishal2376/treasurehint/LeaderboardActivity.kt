package com.vishal2376.treasurehint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.vishal2376.treasurehint.databinding.ActivityLeaderboardBinding

class LeaderboardActivity : AppCompatActivity() {
    private var _binding: ActivityLeaderboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLeaderboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvLeaderboard.let {
            it.layoutManager = LinearLayoutManager(this)
//            it.adapter = LeaderboardAdapter(Constants.Locations)
        }


    }
}