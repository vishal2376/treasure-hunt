package com.vishal2376.treasurehint.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.vishal2376.treasurehint.R
import com.vishal2376.treasurehint.models.LeaderBoard
import com.vishal2376.treasurehint.models.LeaderboardModel

class LeaderboardAdapter(val leaderboard:LeaderBoard):RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val tvScore:TextView
        val tvName:TextView
        init {
            tvName = view.findViewById(R.id.tvName)
            tvScore = view.findViewById(R.id.tvScore)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.leaderboard_item,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return leaderboard.teams.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = leaderboard.teams[position].name
        holder.tvScore.text = leaderboard.teams[position].score.toString()
    }

}