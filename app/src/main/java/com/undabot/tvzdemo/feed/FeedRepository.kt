package com.undabot.tvzdemo.feed

import com.undabot.tvzdemo.feed.models.FeedItem
import com.undabot.tvzdemo.FeedItemNetwork
import com.undabot.tvzdemo.explore.models.ExploreItem
import com.undabot.tvzdemo.networking.RestService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedRepository(private val service: RestService) : Feed.Repository {
    override fun fetchFeedItems(success: (List<FeedItem>) -> Unit, error: (Error) -> Unit) {
        service.fetchPhotos()
                .enqueue(object : Callback<List<FeedItemNetwork>> {
                    override fun onFailure(call: Call<List<FeedItemNetwork>>?, t: Throwable?) {
                        error(errorFrom(t!!))
                    }

                    override fun onResponse(call: Call<List<FeedItemNetwork>>?, response: Response<List<FeedItemNetwork>>?) {
                        if (response?.body() == null || response.code() != 200) {
                            error(RequestError())
                        } else {
                            success(response.body()!!.map {
                                FeedItem(it.user.username, it.urls.small, it.likes)
                            })
                        }
                    }
                })
    }

    private var page = 1
    override fun fetchMoreFeedItems(success: (List<FeedItem>) -> Unit, error: (Error) -> Unit) {
        service.fetchPhotos(page).enqueue(object : Callback<List<FeedItemNetwork>>{
            override fun onFailure(call: Call<List<FeedItemNetwork>>?, t: Throwable?) {
                error(com.undabot.tvzdemo.explore.errorFrom(t!!))
            }

            override fun onResponse(call: Call<List<FeedItemNetwork>>?, response: Response<List<FeedItemNetwork>>?) {
                if (response?.body() == null || response.code() != 200) {
                    error(com.undabot.tvzdemo.explore.RequestError())
                } else {
                    success(response.body()!!.map { FeedItem(it.user.instagramUsername,it.urls.small,it.likes) })
                    page++
                }
            }
        })
    }
}