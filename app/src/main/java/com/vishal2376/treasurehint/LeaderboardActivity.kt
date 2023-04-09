package com.vishal2376.treasurehint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.vishal2376.treasurehint.adapters.LeaderboardAdapter
import com.vishal2376.treasurehint.databinding.ActivityLeaderboardBinding
import com.vishal2376.treasurehint.models.LeaderboardModel

class LeaderboardActivity : AppCompatActivity() {
    private var _binding: ActivityLeaderboardBinding? = null
    private val binding get() = _binding!!
    private val leaderboardArray= ArrayList<LeaderboardModel>()
    private val leaderboardModel= LeaderboardModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLeaderboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //testing
        leaderboardModel.name = "Xdtf"
        leaderboardModel.score = "10"
        leaderboardArray.add(leaderboardModel)


        binding.rvLeaderboard.let {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = LeaderboardAdapter(leaderboardArray)
        }


    }
}