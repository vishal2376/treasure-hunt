package com.vishal2376.treasurehint.locations

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vishal2376.treasurehint.databinding.ActivityDestinationAuditoriumBinding
import com.vishal2376.treasurehint.util.Constants.LocationCount
import com.vishal2376.treasurehint.util.Constants.Locations

class DestinationAuditoriumActivity : AppCompatActivity() {
    private var _binding: ActivityDestinationAuditoriumBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDestinationAuditoriumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            val location = Locations[LocationCount - 1]
            LocationCount++
            if (LocationCount <= 5) {
                NextLocation(location)
            } else {
                Toast.makeText(this, "Congratulations", Toast.LENGTH_LONG).show()
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