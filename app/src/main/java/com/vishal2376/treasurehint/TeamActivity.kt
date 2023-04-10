package com.vishal2376.treasurehint

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.google.gson.Gson
import com.vishal2376.treasurehint.ViewModels.UserViewModel
import com.vishal2376.treasurehint.databinding.ActivityTeamBinding
import com.vishal2376.treasurehint.locations.*
import com.vishal2376.treasurehint.models.User
import com.vishal2376.treasurehint.util.Constants.LocationCount
import com.vishal2376.treasurehint.util.Constants.Locations

class TeamActivity : AppCompatActivity() {
    private var _binding: ActivityTeamBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userJson=intent.getStringExtra("UserJson")

        val user= Gson().fromJson(userJson, User::class.java)

        binding.tvTeamName.text=user.team.name

        binding.btnStart.setOnClickListener {
            if (LocationCount <= 5) {
                val location = Locations[LocationCount - 1]
                NextLocation(location)
                LocationCount++
            } else {
                val intent = Intent(this, LeaderboardActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun NextLocation(location: Int) {
        when (location) {
            1 -> {
                val intent = Intent(this, Destination4HActivity::class.java)
                startActivity(intent)
            }

            2 -> {
                val intent = Intent(this, DestinationAuditoriumActivity::class.java)
                startActivity(intent)
            }

            3 -> {
                val intent = Intent(this, DestinationGroundActivity::class.java)
                startActivity(intent)
            }

            4 -> {
                val intent = Intent(this, DestinationOpenAirGymActivity::class.java)
                startActivity(intent)
            }

            5 -> {
                val intent = Intent(this, DestinationSACActivity::class.java)
                startActivity(intent)
            }

            else -> {
                Toast.makeText(this, "Failed to load Next Location.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}