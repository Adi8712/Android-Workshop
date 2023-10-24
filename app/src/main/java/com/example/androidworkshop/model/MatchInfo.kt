package com.example.androidworkshop.model

data class MatchInfo(
    val appIndex: AppIndex,
    val landingPosition: Int,
    val matchDetails: List<MatchDetail>,
)