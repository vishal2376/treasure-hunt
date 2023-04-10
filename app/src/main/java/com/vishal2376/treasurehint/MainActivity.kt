package com.vishal2376.treasurehint

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.vishal2376.treasurehint.ViewModels.UserViewModel
import com.vishal2376.treasurehint.databinding.ActivityMainBinding
import com.vishal2376.treasurehint.models.LoginData
import com.vishal2376.treasurehint.models.LoginDetails
import com.vishal2376.treasurehint.network.NetworkApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: UserViewModel

    private fun initializeViewModel()
    {
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.getLoginDetails(
            LoginData(
                   binding.editEmail.text.toString(),
                   binding.editPassword.text.toString()
            )
        )
        viewModel.loginDetails.observe(this, Observer { changeActivity(viewModel.loginDetails.value?.message.toString())})

    }

    private fun changeActivity(message:String?) {
        if (message=="Login success") {
            val intent = Intent(this, TeamActivity::class.java)
            startActivity(intent)
            finish()
        }
        if(message=="Team not found") {
            Toast.makeText(this, viewModel.loginDetails.value?.message.toString() , Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {

            initializeViewModel()
        }
    }
}