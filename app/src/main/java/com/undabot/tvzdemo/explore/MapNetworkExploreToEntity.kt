package com.undabot.tvzdemo.explore

import com.undabot.tvzdemo.FeedItemNetwork
import com.undabot.tvzdemo.explore.models.ExploreItem
import com.undabot.tvzdemo.explore.models.ExploreUser

class MapNetworkExploreToEntity{

    fun map(items: List<FeedItemNetwork>) : Pair<List<ExploreUser>,List<ExploreItem>>{
        return Pair(items.take(5).map {
          ExploreUser(it.user.instagramUsername,it.user.profileImage.small)
        },items.map { ExploreItem(it.urls.small) })
    }
}