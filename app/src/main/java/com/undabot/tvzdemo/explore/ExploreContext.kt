package com.undabot.tvzdemo.explore

import com.undabot.tvzdemo.CachableContext
import com.undabot.tvzdemo.TvzApp
import com.undabot.tvzdemo.explore.Explore
import com.undabot.tvzdemo.networking.RestService
import java.lang.ref.WeakReference

class ExploreContext(private val view: Explore.View) : Explore.Context, CachableContext() {

    override fun service(): RestService {
        return TvzApp.instance.service

    }

    override fun presenter(): Explore.Presenter {
        return cacheMap.getOrPut(ExplorePresenter::class.java, {
            ExplorePresenter(repository(), WeakReference(view))
        }) as Explore.Presenter
    }

    override fun repository(): Explore.Repository {
        return cacheMap.getOrPut(ExploreRepository::class.java, {
            ExploreRepository(service(), MapNetworkExploreToEntity())
        }) as Explore.Repository
    }
}