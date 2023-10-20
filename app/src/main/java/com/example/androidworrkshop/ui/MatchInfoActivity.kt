package com.example.androidworrkshop.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidworrkshop.R
import com.example.androidworrkshop.databinding.ActivityMatchInfoBinding
import com.example.androidworrkshop.di.Resource
import com.example.androidworrkshop.model.leanback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchInfoActivity : AppCompatActivity() {

    lateinit var binding : ActivityMatchInfoBinding
    lateinit var viewModel : MainViewModel
    lateinit var scoreCard:leanback
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMatchInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receivedIntent = intent
        var matchId = receivedIntent.getStringExtra("matchId")

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        if (matchId != null) {
            viewModel.getMatchScore(matchId)
        }else{
//            matchId="75451"
//            viewModel.getMatchScore(matchId)
        }

        setObservers()
    }

    private fun setObservers() {
        viewModel.performFetchMatchesScoreStatus.observe(this, Observer {
            when(it.status){
                Resource.Status.LOADING -> {
                    Log.e("setObservers", "Loading")
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
                    Log.d("Result", it.data.toString())
                    scoreCard= it.data!!
                    binding.tossDecision.text = scoreCard.matchHeader.status
                    binding.playom.text=scoreCard.matchHeader.playersOfTheMatch[0].fullName
                    binding.team1.text=scoreCard.matchHeader.matchTeamInfo[0].battingTeamShortName
                    binding.team2.text=scoreCard.matchHeader.matchTeamInfo[0].bowlingTeamShortName
                    binding.team1score.text=scoreCard.miniscore.matchScoreDetails.inningsScoreList[1].score.toString() + "/" + scoreCard.miniscore.matchScoreDetails.inningsScoreList[1].wickets.toString()
                    binding.team2score.text=scoreCard.miniscore.matchScoreDetails.inningsScoreList[0].score.toString() + "/" + scoreCard.miniscore.matchScoreDetails.inningsScoreList[0].wickets.toString()
                    binding.team1over.text="(" + scoreCard.miniscore.matchScoreDetails.inningsScoreList[1].overs.toString() + ")"
                    binding.team2over.text="(" + scoreCard.miniscore.matchScoreDetails.inningsScoreList[0].overs.toString() + ")"
                    binding.decision.text=scoreCard.miniscore.matchScoreDetails.customStatus
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
}