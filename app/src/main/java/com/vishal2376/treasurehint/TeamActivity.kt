package com.vishal2376.treasurehint

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.gson.Gson
import com.vishal2376.treasurehint.databinding.ActivityTeamBinding

import com.vishal2376.treasurehint.locations.*
import com.vishal2376.treasurehint.models.User
import com.vishal2376.treasurehint.util.Constants.LocationCount
import com.vishal2376.treasurehint.util.Constants.Locations
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
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

                val intent = Intent(this, DestinationSPActivity::class.java)
                startActivity(intent)

        }
    }

    private fun dateSetter(string: String) {
        var dateString = string;
        var odt = OffsetDateTime.parse(dateString);
        var dtf = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH);
        binding.tvTimeRemaining.text = "START TIME : ${dtf.format(odt)}"
    }


}