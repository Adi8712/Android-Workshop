package com.example.androidworkshop.model

data class MatchScoreDetails(
    var customStatus: String,
    var highlightedTeamId: Int,
    var inningsScoreList: List<InningsScore>,
    var isMatchNotCovered: Boolean,
    var matchFormat: String,
    var matchId: Int,
    var matchTeamInfo: List<MatchTeamInfo>,
    var state: String,
    var tossResults: TossResults,
)