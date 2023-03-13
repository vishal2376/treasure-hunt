package com.vishal2376.treasurehint.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.vishal2376.treasurehint.R
import com.vishal2376.treasurehint.util.Constants.LocationCount
import com.vishal2376.treasurehint.util.Constants.LocationNames

class ProgressAdapter(private val checkpointArray: Array<Int>) :
    Adapter<ProgressAdapter.ProgressViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgressViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.progress_item, parent, false)
        return ProgressViewHolder(view)
    }

    override fun getItemCount(): Int {
        return checkpointArray.size
    }

    override fun onBindViewHolder(holder: ProgressViewHolder, position: Int) {

        if (position >= LocationCount - 1) {
            holder.locationName.setBackgroundResource(R.drawable.round_box_grey)
        }

        if (LocationNames[checkpointArray[position]] == "4H") {
            holder.itemView.visibility = View.GONE
        } else {

            holder.locationName.text = LocationNames[checkpointArray[position]].toString()
            if (position >= checkpointArray.size - 1) {
                holder.line.visibility = View.GONE
            }
        }
    }

    inner class ProgressViewHolder(itemView: View) : ViewHolder(itemView) {
        val locationName: TextView = itemView.findViewById(R.id.tvLocationName)
        val line: ImageView = itemView.findViewById(R.id.ivLine)
    }
}