package com.vishal2376.treasurehint

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.vishal2376.treasurehint.ViewModels.ApiStatus
import com.vishal2376.treasurehint.ViewModels.UserViewModel
import com.vishal2376.treasurehint.adapters.LeaderboardAdapter
import com.vishal2376.treasurehint.databinding.ActivityLeaderboardBinding
import com.vishal2376.treasurehint.models.LeaderboardModel
import com.vishal2376.treasurehint.models.User

class LeaderboardActivity : AppCompatActivity() {
    private var _binding: ActivityLeaderboardBinding? = null
    private val binding get() = _binding!!
    private val leaderboardArray= ArrayList<LeaderboardModel>()
    private val leaderboardModel= LeaderboardModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLeaderboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userJson=intent.getStringExtra("UserJson")
        val user=Gson().fromJson(userJson, User::class.java)
        val viewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.getLeaderBoard()

        leaderboardArray.add(leaderboardModel)
        binding.rvLeaderboard.layoutManager=LinearLayoutManager(this)
        viewModel.allStatus.observe(this, Observer {

          when(viewModel.allStatus.value)
          {
              ApiStatus.LOADING->{}
              ApiStatus.ERROR->{
                  Toast.makeText(this,"UNABLE TO FETCH LEADERBOARD",Toast.LENGTH_SHORT).show()
              }

              ApiStatus.SUCCESS->{
                  val list=viewModel.leaderBoard.value?.teams?.sortedByDescending {it.score}
                  Log.i("adi ", "size is ${list?.size} ")
                  val size = list?.size
                  binding.tvYourName.text = user.team.name
                  binding.tvYourScore.text = user.team.score.toString()
                  Log.d("Network","${user.team}")
                  val index= list!!.indexOfFirst{
                      it.name == user.team.name
                  }

                  binding.tvYourPosition.text = (index+1).toString()


                  if (size != null) {
                      when (size) {
                          1 -> {
                              binding.tvFirst.text = list[0].name
                              binding.tvFirstScore.text = list[0].score.toString()
                          }
                          2 -> {
                              binding.tvFirst.text = list[0].name
                              binding.tvFirstScore.text = list[0].score.toString()
                              binding.tvSecond.text = list[1].name
                              binding.tvSecondScore.text = list[1].score.toString()
                          }
                          else -> {
                              binding.tvFirst.text = list[0].name
                              binding.tvFirstScore.text = list[0].score.toString()
                              binding.tvSecond.text = list[1].name
                              binding.tvSecondScore.text = list[1].score.toString()
                              binding.tvThird.text = list[2].name
                              binding.tvThirdScore.text = list[2].score.toString()

                          }
                      }
                  }
                  if (size != null) {
                      if(size>3){
                          binding.rvLeaderboard.adapter = LeaderboardAdapter(list)
                      }
                      else{
                          binding.rvLeaderboard.visibility = View.GONE
                      }
                  }

              }
              else->{}

          }




        })


    }
}