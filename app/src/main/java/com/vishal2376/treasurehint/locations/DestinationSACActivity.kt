package com.vishal2376.treasurehint.locations

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
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
import com.vishal2376.treasurehint.databinding.ActivityDestinationSacactivityBinding
import com.vishal2376.treasurehint.models.LoginData
import com.vishal2376.treasurehint.models.User
import com.vishal2376.treasurehint.util.Constants
import com.vishal2376.treasurehint.util.Constants.Email
import com.vishal2376.treasurehint.util.Constants.LocationCount
import com.vishal2376.treasurehint.util.Constants.Locations
import com.vishal2376.treasurehint.util.Constants.Password

class DestinationSACActivity : AppCompatActivity() {
    private var _binding: ActivityDestinationSacactivityBinding? = null
    private val binding get() = _binding!!
    lateinit var hint:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDestinationSacactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //only light mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)


        var hintCheck = true
        val viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.getUserData(LoginData(Constants.Email!!, Constants.Password!!))
        binding.tvCheckpoint.setOnClickListener {
            val intent = Intent(this, ProgressActivity::class.java)
            startActivity(intent)

        }
        viewModel.userStatus.observe(
            this,
            Observer {
                when(viewModel.userStatus.value){
                    ApiStatus.SUCCESS-> {
                        binding.tvCoin.text = viewModel.user.value?.team?.score.toString()
                        hint=viewModel.user.value?.team?.checkpoints?.get(LocationCount-2)?.helper?.hints?.get(0).toString()

                    }
                    ApiStatus.LOADING->{
                        binding.tvCoin.text=""
                    }
                    ApiStatus.ERROR->{
                        binding.tvCoin.text=""

                    }
                    else->
                    {}
                }
            }
        )
        binding.btnHintSac.setOnClickListener {
            if (hintCheck) {

                val builder = AlertDialog.Builder(this)
                builder.setTitle("Do you want to buy hint")
                builder.setMessage("This hint will cost 50 coins")

                builder.setPositiveButton("Yes") { dialog, which ->
                    //Alert which will show the hint after buying
                    viewModel.getUserData(LoginData(Email!!, Password!!))

                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Hint")
                    builder.setMessage(hint)



                    builder.setNegativeButton(android.R.string.no) { dialog, which ->
                        builder.setCancelable(true)
                        hintCheck = false
                    }
                    viewModel.getHint(LoginData(Constants.Email!!, Constants.Password!!))
                    builder.show()
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
            viewModel.getUserData(LoginData(Constants.Email!!, Constants.Password!!))

            viewModel.userStatus.observe(this, Observer {
                when (viewModel.userStatus.value) {
                    ApiStatus.SUCCESS -> {
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
                        }
                        else{
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

            })
        }
    }

    override fun onBackPressed() {

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
    }}