package com.example.androidworkshop.repo

import com.example.androidworkshop.model.MatchInfo
import com.example.androidworkshop.model.leanback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface CricketAPI {

    @Headers(
        "X-RapidAPI-Key:6d9dbf758fmsh11fca4984bbcb11p16baafjsne279fed3636a",
        "X-RapidAPI-Host:cricbuzz-cricket.p.rapidapi.com"
    )
    @GET("series/v1/6732")
    suspend fun getMatchList(): Response<MatchInfo>


    @Headers(
        "X-RapidAPI-Key:6d9dbf758fmsh11fca4984bbcb11p16baafjsne279fed3636a",
        "X-RapidAPI-Host:cricbuzz-cricket.p.rapidapi.com"
    )
    @GET("mcenter/v1/{id}/leanback")
    suspend fun getMatchScore(@Path("id") id: String): Response<leanback>


}