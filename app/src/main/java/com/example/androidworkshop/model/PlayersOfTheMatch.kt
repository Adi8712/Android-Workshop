package com.example.androidworkshop.model

data class PlayersOfTheMatch(
    var captain: Boolean,
    var faceImageId: Int,
    var fullName: String,
    var id: Int,
    var keeper: Boolean,
    var name: String,
    var nickName: String,
    var substitute: Boolean,
    var teamName: String,
)