package com.vishal2376.treasurehint

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vishal2376.treasurehint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnNext.setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}