package com.vishal2376.treasurehint.locations

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.vishal2376.treasurehint.LeaderboardActivity
import com.vishal2376.treasurehint.ProgressActivity
import com.vishal2376.treasurehint.databinding.ActivityDestinationAuditoriumBinding
import com.vishal2376.treasurehint.models.User
import com.vishal2376.treasurehint.util.Constants.LocationCount
import com.vishal2376.treasurehint.util.Constants.Locations

class DestinationAuditoriumActivity : AppCompatActivity() {
    private var _binding: ActivityDestinationAuditoriumBinding? = null
    private val binding get() = _binding!!
      lateinit var userJson:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDestinationAuditoriumBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userJson=intent.getStringExtra("UserJson")!!
        val user= Gson().fromJson(userJson, User::class.java)
        binding.tvCheckpoint.setOnClickListener {
            val intent = Intent(this, ProgressActivity::class.java)
            startActivity(intent)
        }
        var hintCheck=true
        binding.btnHintAudi.setOnClickListener{
            if(hintCheck)
            {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Do you want to buy hint")
                builder.setMessage("This hint will cost 100 coins")

                builder.setPositiveButton("Yes") { dialog, which ->
                    //Alert which will show the hint after buying
                    hintCheck=false
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Hint")
                    builder.setMessage(user.team.checkpoints[0].hints.toString())

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

            else
            {
                Toast.makeText(this,"You've already seen the hint",Toast.LENGTH_SHORT).show()
            }
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
                intent.putExtra("UserJson",userJson)
                startActivity(intent)
            }

            2 -> {
                val intent = Intent(this, DestinationAuditoriumActivity::class.java)
                intent.putExtra("UserJson",userJson)
                startActivity(intent)
            }

            3 -> {
                val intent = Intent(this, DestinationGroundActivity::class.java)
                intent.putExtra("UserJson",userJson)
                startActivity(intent)
            }

            4 -> {
                val intent = Intent(this, DestinationOpenAirGymActivity::class.java)
                intent.putExtra("UserJson",userJson)
                startActivity(intent)
            }

            5 -> {
                val intent = Intent(this, DestinationSACActivity::class.java)
                intent.putExtra("UserJson",userJson)
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