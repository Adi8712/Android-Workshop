package com.example.androidworkshop.model

data class MatchInfoX(
    val currBatTeamId: Int?,
    val endDate: String?,
    val isTimeAnnounced: Boolean?,
    val matchDesc: String?,
    val matchFormat: String?,
    val matchId: Int?,
    val seriesEndDt: String?,
    val seriesId: Int?,
    val seriesName: String?,
    val seriesStartDt: String?,
    val startDate: String?,
    val state: String?,
    val status: String?,
    val team1: Team1?,
    val team2: Team1?,
    val venueInfo: VenueInfo?,
)