package com.vishal2376.treasurehint

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.vishal2376.treasurehint.ViewModels.ApiStatus
import com.vishal2376.treasurehint.ViewModels.UserViewModel
import com.vishal2376.treasurehint.databinding.ActivityMainBinding
import com.vishal2376.treasurehint.models.LoginData
import com.vishal2376.treasurehint.util.Constants


class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: UserViewModel

    lateinit var jsonString: String

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.getLoginDetails(
            LoginData(
                binding.editEmail.text.toString(),
                binding.editPassword.text.toString()
            )
        )
        viewModel.getUserData(
            LoginData(
                binding.editEmail.text.toString(),
                binding.editPassword.text.toString()
            )
        )


        viewModel.loginStatus.observe(this, Observer {
            if (viewModel.loginStatus.value == ApiStatus.SUCCESS) {
                checkUserData()
            }
            if (viewModel.loginStatus.value == ApiStatus.LOADING) {
                binding.btnNext.visibility = View.GONE
                showPB()
            }
            if (viewModel.loginStatus.value == ApiStatus.ERROR) {
                hidePB()
                binding.btnNext.visibility = View.VISIBLE
                Toast.makeText(this, "UNABLE TO FETCH DATA", Toast.LENGTH_LONG).show()
                Log.d("Network", "${viewModel.user.value} hfdfggf")
            }


        })


    }

    private fun changeActivity(message: String?) {
        if (viewModel.user.value?.success==true) {
            val gson = Gson()
            jsonString = gson.toJson(viewModel.user.value)
            val intent = Intent(this, TeamActivity::class.java)
            intent.putExtra("UserJson", jsonString)
            startActivity(intent)
            finish()
        }

    }

    private fun showPB() {
        binding.pbMain.visibility = View.VISIBLE
    }

    private fun hidePB() {
        binding.pbMain.visibility = View.GONE

    }

    private fun checkUserData() {
        viewModel.userStatus.observe(this, Observer {
            Constants.Email = binding.editEmail.text.toString()
            Constants.Password = binding.editPassword.text.toString()

            if (viewModel.userStatus.value == ApiStatus.SUCCESS) {


                val str = viewModel.user.value?.team?.name.toString().slice(5..9)
                var arr = mutableListOf<Int>()
                for (i in str) {

                    arr.add(i.toString().toInt())
                }
                Constants.Locations = arr
                changeActivity(viewModel.loginDetails.value?.message.toString())

            }
            if (viewModel.userStatus.value == ApiStatus.LOADING) {
                binding.btnNext.visibility = View.GONE
                showPB()
            }
            if (viewModel.userStatus.value == ApiStatus.ERROR) {
                hidePB()
                Toast.makeText(this, "UNABLE TO FETCH DATA", Toast.LENGTH_LONG).show()
                binding.btnNext.visibility = View.VISIBLE
            }

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //only light mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)


        binding.btnNext.setOnClickListener {
            initializeViewModel()
        }
    }
}