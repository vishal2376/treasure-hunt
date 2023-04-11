package com.vishal2376.treasurehint.locations

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.vishal2376.treasurehint.LeaderboardActivity
import com.vishal2376.treasurehint.ProgressActivity
import com.vishal2376.treasurehint.databinding.ActivityDestination4HactivityBinding
import com.vishal2376.treasurehint.util.Constants.LocationCount
import com.vishal2376.treasurehint.util.Constants.Locations

class Destination4HActivity : AppCompatActivity() {
    private var _binding: ActivityDestination4HactivityBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDestination4HactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvCheckpoint.setOnClickListener {
            val intent = Intent(this, ProgressActivity::class.java)
            startActivity(intent)
        }
        binding.btnHint4h.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Do you want to buy hint")
            builder.setMessage("This hint will cost 100 coins")

            builder.setPositiveButton("Yes") { dialog, which ->
                //Alert which will show the hint after buying

                val builder = AlertDialog.Builder(this)
                builder.setTitle("Hint")
                builder.setMessage("Here we will display hint")

                builder.setNegativeButton(android.R.string.no) { dialog, which ->
                    builder.setCancelable(true)
                }
                builder.show()
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->
                builder.setCancelable(true)
            }

            builder.show()
        }

        binding.btnNext.setOnClickListener {
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
    override fun onBackPressed() {

    }

}