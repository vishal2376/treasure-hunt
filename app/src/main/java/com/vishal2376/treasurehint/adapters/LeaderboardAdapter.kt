package com.vishal2376.treasurehint.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vishal2376.treasurehint.R
import com.vishal2376.treasurehint.models.Team

class LeaderboardAdapter(val list: List<Team>):RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val tvScore:TextView
        val tvName:TextView
        val tvPosition:TextView
        val llLeaderboardItem:LinearLayout
        init {
            tvName = view.findViewById(R.id.tvName)
            tvScore = view.findViewById(R.id.tvScore)
            tvPosition = view.findViewById(R.id.tvPosition)
            llLeaderboardItem = view.findViewById(R.id.llLeaderboardItem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.leaderboard_item,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if(position>2){
            val rank = position + 1
            holder.tvPosition.text = rank.toString()
            holder.tvName.text = list[position].name
            holder.tvScore.text = list[position].score.toString()

        }
        else{
            holder.llLeaderboardItem.visibility = View.GONE
        }

    }

}