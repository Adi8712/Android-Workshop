package com.example.androidworkshop.model

data class Result(
    var resultType: String,
    var winByInnings: Boolean,
    var winByRuns: Boolean,
    var winningMargin: Int,
    var winningTeam: String,
    var winningteamId: Int,
)