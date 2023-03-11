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

class ProgressAdapter(private val locationArray: Array<Int>) :
    Adapter<ProgressAdapter.ProgressViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgressViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.progress_item, parent, false)
        return ProgressViewHolder(view)
    }

    override fun getItemCount(): Int {
        return locationArray.size
    }

    override fun onBindViewHolder(holder: ProgressViewHolder, position: Int) {

        if (position >= LocationCount - 1) {
            holder.locationName.setBackgroundResource(R.color.colorGrey)
        }

        if (LocationNames[locationArray[position]] == "4H") {
            holder.itemView.visibility = View.GONE
        } else {

            holder.locationName.text = LocationNames[locationArray[position]].toString()
            if (position >= locationArray.size - 2) {
                holder.line.visibility = View.GONE
            }
        }
    }

    inner class ProgressViewHolder(itemView: View) : ViewHolder(itemView) {
        val locationName: TextView = itemView.findViewById(R.id.tvLocationName)
        val line: ImageView = itemView.findViewById(R.id.ivLine)
    }
}