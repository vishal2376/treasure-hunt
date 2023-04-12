package com.vishal2376.treasurehint

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vishal2376.treasurehint.ViewModels.ApiStatus
import com.vishal2376.treasurehint.ViewModels.UserViewModel
import com.vishal2376.treasurehint.adapters.LeaderboardAdapter
import com.vishal2376.treasurehint.databinding.ActivityLeaderboardBinding

class LeaderboardActivity : AppCompatActivity() {
    private var _binding: ActivityLeaderboardBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLeaderboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.getLeaderBoard()

        binding.rvLeaderboard.layoutManager = LinearLayoutManager(this)
        viewModel.allStatus.observe(this, Observer {

            when (viewModel.allStatus.value) {
                ApiStatus.LOADING -> {}

                ApiStatus.ERROR -> {
                    Toast.makeText(this, "UNABLE TO FETCH LEADERBOARD", Toast.LENGTH_SHORT).show()
                }

                ApiStatus.SUCCESS -> {

                    val list = viewModel.leaderBoard.value?.teams?.sortedByDescending { it.score }
                    val size = list?.size
                    if (size != null) {
                        when (size) {
                            1 -> {
                                binding.tvFirst.text = list[0].name
                            }
                            2 -> {
                                binding.tvFirst.text = list[0].name
                                binding.tvSecond.text = list[1].name
                            }
                            else -> {
                                binding.tvFirst.text = list[0].name
                                binding.tvSecond.text = list[1].name
                                binding.tvThird.text = list[2].name
                            }
                        }
                    }

                    if (size != null) {
                        if (size > 3) {
                            binding.rvLeaderboard.adapter = LeaderboardAdapter(list)

                        } else {
                            binding.rvLeaderboard.visibility = View.GONE
                        }
                    }
                }
                else -> {}
            }
        })


    }
}