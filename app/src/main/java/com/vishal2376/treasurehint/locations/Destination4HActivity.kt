package com.vishal2376.treasurehint.locations

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.vishal2376.treasurehint.LeaderboardActivity
import com.vishal2376.treasurehint.ProgressActivity
import com.vishal2376.treasurehint.ViewModels.ApiStatus
import com.vishal2376.treasurehint.ViewModels.UserViewModel
import com.vishal2376.treasurehint.databinding.ActivityDestination4HactivityBinding
import com.vishal2376.treasurehint.models.LoginData
import com.vishal2376.treasurehint.models.User
import com.vishal2376.treasurehint.util.Constants.Email
import com.vishal2376.treasurehint.util.Constants.LocationCount
import com.vishal2376.treasurehint.util.Constants.Locations
import com.vishal2376.treasurehint.util.Constants.Password

class Destination4HActivity : AppCompatActivity() {
    private var _binding: ActivityDestination4HactivityBinding? = null
    private val binding get() = _binding!!
    lateinit var hints: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDestination4HactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //only light mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        var hintCheck = true
        val viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.getUserData(LoginData(Email!!, Password!!))
        binding.tvCheckpoint.setOnClickListener {
            val intent = Intent(this, ProgressActivity::class.java)
            startActivity(intent)
        }
        updateCoins(viewModel)

        binding.btnHint4h.setOnClickListener {
            if (hintCheck) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Do you want to buy hint")
                builder.setMessage("This hint will cost 50 coins")

                builder.setPositiveButton("Yes") { dialog, which ->
                    //Alert which will show the hint after buying

                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Hint")
                    builder.setMessage(hints)

                    builder.setNegativeButton(android.R.string.no) { dialog, which ->
                        builder.setCancelable(true)
                        hintCheck = false
                    }
                    builder.show()
                    viewModel.getHint(LoginData(Email!!, Password!!))
                }


                builder.setNegativeButton(android.R.string.no) { dialog, which ->
                    builder.setCancelable(true)
                }

                builder.show()
            } else {
                Toast.makeText(this, "Hint Used", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnNext.setOnClickListener {
            viewModel.getUserData(LoginData(Email!!, Password!!))
            viewModel.userStatus.observe(this, Observer {
                Log.e("@@@", Locations[LocationCount - 1].toString())
                Log.e("@@@@", LocationCount.toString())

                when (viewModel.userStatus.value) {

                    ApiStatus.SUCCESS -> {
                        binding.btnNext.visibility = View.VISIBLE

                        if (viewModel.user.value?.team?.checkpoints?.get(LocationCount - 2)?.cleared == true) {
                            if (LocationCount <= 5) {
                                val location = Locations[LocationCount - 1]
                                NextLocation(location)
                                LocationCount++
                            } else {
                                val userJson = Gson().toJson(viewModel.user.value, User::class.java)
                                val intent = Intent(this, LeaderboardActivity::class.java)
                                intent.putExtra("UserJson", userJson)
                                startActivity(intent)
                            }
                        } else {
                            Toast.makeText(this, "Try Again", Toast.LENGTH_SHORT).show()
                        }
                    }
                    ApiStatus.LOADING -> {
                        binding.btnNext.visibility = View.GONE
                    }
                    ApiStatus.ERROR -> {
                        binding.btnNext.visibility = View.VISIBLE
                        Toast.makeText(this, "Can't go to next Activity", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        binding.btnNext.visibility = View.VISIBLE
                        Toast.makeText(this, "Can't go to next Activity", Toast.LENGTH_SHORT).show()
                    }
                }

            }

            )
        }


    }

    fun updateCoins(viewModel: UserViewModel) {
        viewModel.userStatus.observe(
            this,
            Observer {
                when (viewModel.userStatus.value) {
                    ApiStatus.SUCCESS -> {
                        binding.tvCoin.text = viewModel.user.value?.team?.score.toString()
                        hints =
                            viewModel.user.value?.team?.checkpoints?.get(LocationCount - 2)?.helper?.hints?.get(
                                0
                            ).toString()
                    }
                    ApiStatus.LOADING -> {
                        binding.tvCoin.text = ""
                    }
                    ApiStatus.ERROR -> {
                        binding.tvCoin.text = ""

                    }
                    else -> {}
                }
            }
        )
    }


    fun NextLocation(location: Int) {
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
                val intent = Intent(this, Destination4HActivity::class.java)
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
                Toast.makeText(this, "Failed to load Next Location.", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onBackPressed() {

    }

}
