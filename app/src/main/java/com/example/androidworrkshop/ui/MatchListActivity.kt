package com.example.androidworrkshop.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidworrkshop.R
import com.example.androidworrkshop.databinding.ActivityMatchListBinding
import com.example.androidworrkshop.di.Resource
import com.example.androidworrkshop.model.Match
import com.example.androidworrkshop.model.MatchDetail
import com.example.androidworrkshop.model.MatchDetailsMap
import com.example.androidworrkshop.model.MatchInfo
import com.example.androidworrkshop.model.MatchInfoX
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchListActivity : AppCompatActivity() {

    lateinit var binding : ActivityMatchListBinding
    lateinit var viewModel : MainViewModel
    private var MatchInfo : MatchInfo ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMatchListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.getMatches()

        setObservers()
    }

    private fun setObservers(){
        viewModel.performFetchMatchesStatus.observe(this, Observer {
            when(it.status){
                Resource.Status.LOADING -> {
                    Log.d("setObservers", "Loading")
//                    binding.progressBar.visibility = View.VISIBLE
                }
                Resource.Status.EMPTY -> {
                    Log.d("setObservers", "Empty")
//                    binding.progressBar.visibility = View.GONE
//                    binding.emptyDialog.visibility = View.VISIBLE
                }
                Resource.Status.SUCCESS -> {
                    Log.d("setObservers", "Success")
//                    binding.progressBar.visibility = View.GONE
//                    binding.emptyDialog.visibility = View.GONE
//                    ImageList = it.data
//                    Log.d("Result",it.data.toString())
                    MatchInfo=it.data
                    SetUpRecyclerView()
                }
                Resource.Status.ERROR -> {
                    Log.d("setObservers", it.error.toString())
//                    binding.progressBar.visibility = View.GONE
//                    binding.emptyDialog.visibility = View.VISIBLE
                    Toast.makeText(this, it.error.toString(), Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }
        })
    }

    fun SetUpRecyclerView(){
        var matchList:MutableList<Match> = arrayListOf()
//        Log.d("okay",MatchInfo.toString())
        val list=MatchInfo?.matchDetails

        for(i in 0 until list?.size!!){
//            Log.d("num",i.toString())
            try {
//                Log.d("Match", MatchInfo?.matchDetails!![i].matchDetailsMap.match[0].toString())
                matchList.add(list[i].matchDetailsMap.match[0])
            }catch (e:Exception){
                Log.d("error",e.toString())
            }

        }

        var matchListUpcoming:MutableList<Match> = arrayListOf()
        var matchListLive:MutableList<Match> = arrayListOf()
        var matchListPast:MutableList<Match> = arrayListOf()

        var up=0
        var live=0

        for(i in matchList){
            try {
                when (i.matchInfo.state) {
                    "Complete" -> {
                        matchListPast.add(i)
                    }
                    "Upcoming" -> {
                        matchListUpcoming.add(i)
                        up++
                    }
                    else -> {
                        matchListLive.add(i)
                        live++
                    }
                }
            }catch (e:Exception){
                Log.d("error_ll",e.toString())
            }
        }
        matchList=matchListUpcoming
        for(i in  matchListLive){
            matchList.add(i)
        }
        for(i in  matchListPast){
            matchList.add(i)
        }
        val rv =binding.rv

//        Log.d("Match Upcoming",matchListUpcoming.toString())
        val adapter=MatchAdapter(this,matchList,up,live)

        try {
            rv.layoutManager=LinearLayoutManager(this)
            rv.adapter=adapter
        }catch (e:Exception){
            Log.d("error",e.toString())
        }



        Log.d("Done","Done")
    }

}