package com.vishal2376.treasurehint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.vishal2376.treasurehint.adapters.ProgressAdapter
import com.vishal2376.treasurehint.databinding.ActivityProgressBinding
import com.vishal2376.treasurehint.util.Constants

class ProgressActivity : AppCompatActivity() {
    private var _binding: ActivityProgressBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProgressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvProgress.let {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = ProgressAdapter(Constants.Checkpoints)
        }


    }
}