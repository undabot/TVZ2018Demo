package com.undabot.tvzdemo.explore

import com.undabot.tvzdemo.FeedItemNetwork
import com.undabot.tvzdemo.explore.models.ExploreItem
import com.undabot.tvzdemo.explore.models.ExploreUser
import com.undabot.tvzdemo.networking.RestService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExploreRepository(val service: RestService, val mapper: MapNetworkExploreToEntity) : Explore.Repository {

    private var page = 1
    override fun fetchMoreExploreItems(success: (List<ExploreItem>) -> Unit, error: (Error) -> Unit) {
        service.fetchPhotos(page).enqueue(object : Callback<List<FeedItemNetwork>>{
            override fun onFailure(call: Call<List<FeedItemNetwork>>?, t: Throwable?) {
                error(errorFrom(t!!))
            }

            override fun onResponse(call: Call<List<FeedItemNetwork>>?, response: Response<List<FeedItemNetwork>>?) {
                if (response?.body() == null || response.code() != 200) {
                    error(RequestError())
                } else {
                    success(response.body()!!.map { ExploreItem(it.urls.small)})
                    page++
                }
            }
        })
    }

    override fun fetchExploreItems(success: (Pair<List<ExploreUser>, List<ExploreItem>>) -> Unit, error: (Error) -> Unit) {
        service.fetchPhotos()
                .enqueue(object : Callback<List<FeedItemNetwork>> {
                    override fun onFailure(call: Call<List<FeedItemNetwork>>?, t: Throwable?) {
                        error(errorFrom(t!!))
                    }

                    override fun onResponse(call: Call<List<FeedItemNetwork>>?, response: Response<List<FeedItemNetwork>>?) {
                        if (response?.body() == null || response.code() != 200) {
                            error(RequestError())
                        } else {
                            success(mapper.map(response.body()!!))
                        }
                    }
                })
    }
}

