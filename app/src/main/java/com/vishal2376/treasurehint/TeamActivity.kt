package com.vishal2376.treasurehint

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.gson.Gson
import com.vishal2376.treasurehint.databinding.ActivityTeamBinding
import com.vishal2376.treasurehint.locations.DestinationSPActivity
import com.vishal2376.treasurehint.models.User
import java.util.*

class TeamActivity : AppCompatActivity() {
    private var _binding: ActivityTeamBinding? = null
    private val binding get() = _binding!!
    lateinit var userJson: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //only light mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)


        userJson = intent.getStringExtra("UserJson")!!

        val user = Gson().fromJson(userJson, User::class.java)

        binding.tvTeamName.text = user.team.name

//        dateSetter(user.team.checkpoints[0].startTime)
        binding.btnStart.setOnClickListener {
            if (true) {
                startActivity(Intent(this@TeamActivity, DestinationSPActivity::class.java))

            } else {
                Toast.makeText(this, "The Hunt will start at 1:30 pm", Toast.LENGTH_SHORT).show()
            }

        }
    }

    //TODO
    fun isCurrentTimeEqualTo1PM(): Boolean {
        val currentTime = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)
        return hour >= 14 && minute >= 0
    }


}