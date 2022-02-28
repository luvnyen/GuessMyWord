package com.example.latihanfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class adapterHistory(private val listHistory: ArrayList<History>) : RecyclerView.Adapter<adapterHistory.ListViewHolder>() {
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var _name : TextView = itemView.findViewById(R.id.tvName)
        var _time : TextView = itemView.findViewById(R.id.tvTime)
        var _score : TextView = itemView.findViewById(R.id.tvScore)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): adapterHistory.ListViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: adapterHistory.ListViewHolder, position: Int) {
        var history = listHistory[position]

        holder._name.setText(history.name)
        if (history.score == 65) {
            holder._score.setText("Score: ${history.score.toString()} (Perfect! 💯)")
        } else {
            holder._score.setText("Score: ${history.score.toString()}")
        }
        holder._time.setText("Time taken: ${history.time}")
    }

    override fun getItemCount(): Int {
        return listHistory.size
    }
}
