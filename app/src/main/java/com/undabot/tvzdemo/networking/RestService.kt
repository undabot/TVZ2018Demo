package com.undabot.tvzdemo.networking

import com.undabot.tvzdemo.FeedItemNetwork
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RestService {

    @GET("/photos")
    fun fetchPhotos(@Query("page") page: Int = 0,
                    @Query("per_page") perPage: Int = 50) : Call<List<FeedItemNetwork>>
}