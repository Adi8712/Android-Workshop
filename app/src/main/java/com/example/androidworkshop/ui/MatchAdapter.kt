package com.example.androidworkshop.ui

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidworkshop.databinding.ItemMatchBinding
import com.example.androidworkshop.model.Match

class MatchAdapter(
    private val context: Context,
    private val MatchDetails: MutableList<Match>,
    private val up: Int,
    private val live: Int,
) : RecyclerView.Adapter<MatchAdapter.ViewHolder>() {


    class ViewHolder(binding: ItemMatchBinding) : RecyclerView.ViewHolder(binding.root) {
        val team1 = binding.cvTeam1
        val team2 = binding.cvTeam2
        val time = binding.cvTime
        val location = binding.location
        val heading = binding.heading
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        MatchDetails.sortWith(Comparator { lhs, rhs ->
            when {
                lhs.matchInfo.startDate!! > rhs.matchInfo.startDate.toString() -> {
                    -1
                }

                lhs.matchInfo.startDate < rhs.matchInfo.startDate.toString() -> {
                    1
                }

                else -> {
                    0
                }
            }
        })
        return ViewHolder(
            ItemMatchBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return MatchDetails.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = MatchDetails[position]
        holder.team1.text = model.matchInfo.team1?.teamSName
        holder.team2.text = model.matchInfo.team2?.teamSName
        when {
            model.matchInfo.status?.indexOf("(")!! > 0 -> holder.time.text =
                model.matchInfo.status.substringBefore(" (") + "\n(" + model.matchInfo.status.substringAfter(
                    "("
                )

            model.matchInfo.status.indexOf(",") > 0 -> holder.time.text =
                model.matchInfo.status.substringBefore(",") + "\n" + model.matchInfo.status.substringAfter(
                    ", "
                )

            else -> holder.time.text = model.matchInfo.status
        }
        holder.location.text = model.matchInfo.venueInfo?.city
        when (position) {
            0 -> {
                holder.heading.text = "Upcoming Matches"
                holder.heading.visibility = VISIBLE
            }

            up -> {
                holder.heading.text = "Live Matches"
                holder.heading.visibility = VISIBLE
            }

            up + live -> {
                holder.heading.text = "Complete Matches"
                holder.heading.visibility = VISIBLE
            }
        }

        Log.d("Position", position.toString())

        holder.itemView.setOnClickListener {
            if (model.matchInfo.state == "Complete") {
                val intent = Intent(context, MatchInfoActivity::class.java)
                intent.putExtra("matchId", model.matchInfo.matchId.toString())
                context.startActivity(intent)
            }
        }

    }

}