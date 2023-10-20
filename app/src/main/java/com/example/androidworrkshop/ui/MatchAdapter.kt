package com.example.androidworrkshop.ui

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.androidworrkshop.R
import com.example.androidworrkshop.databinding.ItemMatchBinding
import com.example.androidworrkshop.model.Match
import com.example.androidworrkshop.model.MatchDetail
import com.example.androidworrkshop.model.MatchDetailsMap

class MatchAdapter(private val context: Context, private val MatchDetails : MutableList<Match>, private val up : Int, private val live : Int) : RecyclerView.Adapter<MatchAdapter.ViewHolder>() {


    class ViewHolder(binding: ItemMatchBinding) : RecyclerView.ViewHolder(binding.root){
        val team1 = binding.cvTeam1
        val team2 = binding.cvTeam2
        val time = binding.cvTime
        val location = binding.location
        val heading = binding.heading
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMatchBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun getItemCount(): Int {
        return MatchDetails.size
    }

    override fun onBindViewHolder(holder: MatchAdapter.ViewHolder, position: Int) {
       val model = MatchDetails[position]
        holder.team1.text = model.matchInfo.team1?.teamSName
        holder.team2.text = model.matchInfo.team2?.teamSName
        holder.time.text = model.matchInfo.status
        holder.location.text = model.matchInfo.venueInfo?.city

        when (position) {
            0 -> {
                holder.heading.visibility = View.VISIBLE
                holder.heading.text = "Upcoming Matches"
                Log.d("Matches","Upcoming")
            }
            up -> {
                holder.heading.visibility = View.VISIBLE
                holder.heading.text = "Live Matches"
                Log.d("Matches","Live")
            }
            up+live -> {
                holder.heading.visibility = View.VISIBLE
                holder.heading.text = "Past Matches"
                Log.d("Matches","Past")
            }
        }

        holder.itemView.setOnClickListener{
           if(model.matchInfo.state=="Complete") {
               val intent = Intent(context, MatchInfoActivity::class.java)
               intent.putExtra("matchId", model.matchInfo.matchId.toString())
               context.startActivity(intent)
           }
       }

    }

}