package com.example.androidworkshop.model

data class InningsScore(
    var ballNbr: Int,
    var batTeamId: Int,
    var batTeamName: String,
    var inningsId: Int,
    var isDeclared: Boolean,
    var isFollowOn: Boolean,
    var overs: Double,
    var score: Int,
    var wickets: Int,
)