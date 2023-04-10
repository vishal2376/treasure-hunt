package com.vishal2376.treasurehint

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vishal2376.treasurehint.ViewModels.UserViewModel
import com.vishal2376.treasurehint.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel:UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
         viewModel=ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}