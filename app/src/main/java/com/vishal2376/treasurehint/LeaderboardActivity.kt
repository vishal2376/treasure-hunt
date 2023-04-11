package com.vishal2376.treasurehint

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.vishal2376.treasurehint.ViewModels.ApiStatus
import com.vishal2376.treasurehint.ViewModels.UserViewModel
import com.vishal2376.treasurehint.adapters.LeaderboardAdapter
import com.vishal2376.treasurehint.databinding.ActivityLeaderboardBinding
import com.vishal2376.treasurehint.models.LeaderboardModel
import com.vishal2376.treasurehint.models.User

class LeaderboardActivity : AppCompatActivity() {
    private var _binding: ActivityLeaderboardBinding? = null
    private val binding get() = _binding!!
    private val leaderboardArray= ArrayList<LeaderboardModel>()
    private val leaderboardModel= LeaderboardModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLeaderboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.getLeaderBoard()

        leaderboardArray.add(leaderboardModel)
        binding.rvLeaderboard.layoutManager=LinearLayoutManager(this)
        viewModel.allStatus.observe(this, Observer {
          when(viewModel.allStatus.value)
          {
              ApiStatus.LOADING->{}
              ApiStatus.ERROR->{
                  Toast.makeText(this,"UNABLE TO FETCH LEADERBOARD",Toast.LENGTH_SHORT).show()
              }
              ApiStatus.SUCCESS->{
                  binding.rvLeaderboard.adapter = LeaderboardAdapter(viewModel.leaderBoard.value!!)
              }
              else->{}

          }




        })


    }
}