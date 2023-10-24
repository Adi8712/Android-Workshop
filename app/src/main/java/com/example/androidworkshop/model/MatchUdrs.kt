package com.example.androidworkshop.model

data class MatchUdrs(
    var inningsId: Int,
    var matchId: Int,
    var team1Id: Int,
    var team1Remaining: Int,
    var team1Successful: Int,
    var team1Unsuccessful: Int,
    var team2Id: Int,
    var team2Remaining: Int,
    var team2Successful: Int,
    var team2Unsuccessful: Int,
    var timestamp: String,
)