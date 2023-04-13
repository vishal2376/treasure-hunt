package com.vishal2376.treasurehint.locations

import android.content.Intent
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Constants
import android.view.LayoutInflater
import android.widget.Toast
import com.vishal2376.treasurehint.R
import com.vishal2376.treasurehint.databinding.ActivityDestinationSpactivityBinding

class DestinationSPActivity : AppCompatActivity() {
    private var _binding:ActivityDestinationSpactivityBinding? =null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding=ActivityDestinationSpactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnStart.setOnClickListener {
            if (com.vishal2376.treasurehint.util.Constants.LocationCount <= 5) {
                val location = com.vishal2376.treasurehint.util.Constants.Locations[com.vishal2376.treasurehint.util.Constants.LocationCount - 1]
                NextLocation(location)
                com.vishal2376.treasurehint.util.Constants.LocationCount++

            }
        }
    }

    private fun NextLocation(location: Int) {
        when (location) {
            1 -> {
                val intent = Intent(this, DestinationSACActivity::class.java)
                startActivity(intent)
            }

            2 -> {
                val intent = Intent(this, DestinationOpenAirGymActivity::class.java)
                startActivity(intent)
            }

            3 -> {
                val intent = Intent(this,Destination4HActivity::class.java)
                startActivity(intent)
            }

            4 -> {
                val intent = Intent(this, DestinationGroundActivity::class.java)
                startActivity(intent)
            }

            5 -> {
                val intent = Intent(this, DestinationAuditoriumActivity::class.java)
                startActivity(intent)
            }

            else -> {
                Toast.makeText(this, "Failed to load Next Location.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }}