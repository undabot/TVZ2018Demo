package com.undabot.tvzdemo.feed

import com.undabot.tvzdemo.CachableContext
import com.undabot.tvzdemo.TvzApp
import com.undabot.tvzdemo.networking.RestService
import java.lang.ref.WeakReference

class FeedContext(private val view: Feed.View) : Feed.Context, CachableContext() {

    override fun service(): RestService {
        return TvzApp.instance.service

    }

    override fun presenter(): Feed.Presenter {
        return cacheMap.getOrPut(FeedPresenter::class.java, {
            FeedPresenter(repository(), WeakReference(view))
        }) as Feed.Presenter
    }

    override fun repository(): Feed.Repository {
        return cacheMap.getOrPut(FeedRepository::class.java, {
            FeedRepository(service())
        }) as Feed.Repository
    }
}