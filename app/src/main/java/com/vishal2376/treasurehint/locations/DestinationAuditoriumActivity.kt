package com.vishal2376.treasurehint.locations

import android.content.Intent
import android.net.Uri
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
import com.vishal2376.treasurehint.databinding.ActivityDestinationAuditoriumBinding
import com.vishal2376.treasurehint.models.LoginData
import com.vishal2376.treasurehint.models.User
import com.vishal2376.treasurehint.util.Constants
import com.vishal2376.treasurehint.util.Constants.LocationCount
import com.vishal2376.treasurehint.util.Constants.Locations

class DestinationAuditoriumActivity : AppCompatActivity() {
    private var _binding: ActivityDestinationAuditoriumBinding? = null
    private val binding get() = _binding!!
    private var link12: String? = null
    private var link2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDestinationAuditoriumBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //only light mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)


        var hintCheck = true
        val viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.getUserData(LoginData(Constants.Email!!, Constants.Password!!))
        lateinit var hint:String
        binding.tvCheckpoint.setOnClickListener {
            val intent = Intent(this, ProgressActivity::class.java)
            startActivity(intent)
        }
        viewModel.userStatus.observe(
            this,
            Observer {

                when (viewModel.userStatus.value) {
                    ApiStatus.SUCCESS -> {

                        binding.tvCoin.text = viewModel.user.value?.team?.score.toString()
                        hint=viewModel.user.value?.team?.checkpoints?.get(LocationCount-2)?.helper?.hints?.get(0).toString()
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

        binding.btnHintAudi.setOnClickListener {
            if (hintCheck) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Do you want to buy hint")
                builder.setMessage("This hint will cost 50 coins")

                builder.setPositiveButton("Yes") { dialog, which ->
                    //Alert which will show the hint after buying

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
                Toast.makeText(this, "Hint Used", Toast.LENGTH_SHORT)
            }
        }
        binding.btnGetAudio.setOnClickListener {

            val sercretKey1 = binding.editSecretKey.text.toString()
            //TODO remove true
            if ((sercretKey1 == viewModel.user.value?.team?.helpers?.puzzleCode.toString())) {
                binding.llSecretKey.visibility = View.GONE
                binding.llPassword.visibility = View.VISIBLE
                val audioLink =
                    viewModel.user.value?.team?.helpers?.stenographyCode.toString()
                val i = Intent(Intent.ACTION_VIEW, Uri.parse(audioLink))
                startActivity(i)

            } else {
                Toast.makeText(this, "Wrong code", Toast.LENGTH_SHORT).show()
            }




        }
        binding.btnVerifyMorse.setOnClickListener {
            if (binding.editMorse.text.toString() == viewModel.user.value?.team?.helpers?.morseCode.toString()) {
                link12 = viewModel.user.value?.team?.helpers?.imageLink.toString()
                link2 = viewModel.user.value?.team?.helpers?.copy.toString()
                binding.llPassword.visibility = View.GONE
                binding.llLinkButtons.visibility = View.VISIBLE

            } else {
                Toast.makeText(this, "Wrong code", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnLink1.setOnClickListener {
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link12?.trim())))

            }
            catch (e:java.lang.Exception)
            {
                Log.d("Errors","${e.message}")
            }
        }
        binding.btnLink2.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse(link2?.trim()))
            startActivity(i)
        }



        binding.btnNext.setOnClickListener {
            viewModel.getUserData(LoginData(Constants.Email!!, Constants.Password!!))

            viewModel.userStatus.observe(this, Observer {
                when (viewModel.userStatus.value) {
                    ApiStatus.SUCCESS -> {
                        if (viewModel.user.value?.team?.checkpoints?.get(4)?.cleared == true||true) {
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
                            Toast.makeText(this, "Task not Completed", Toast.LENGTH_SHORT).show()
                            binding.btnNext.visibility = View.VISIBLE
                        }
                    }

                    ApiStatus.LOADING -> {
                        binding.btnNext.visibility = View.GONE
                    }
                    ApiStatus.ERROR -> {
                        binding.btnNext.visibility = View.VISIBLE
                        Toast.makeText(
                            this,
                            "Can't go to next Activity",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else -> {
                        binding.btnNext.visibility = View.VISIBLE
                        Toast.makeText(
                            this,
                            "Can't go to next Activity",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            })
        }
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
