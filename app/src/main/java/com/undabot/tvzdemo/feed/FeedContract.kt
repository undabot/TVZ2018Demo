package com.undabot.tvzdemo.feed

import com.undabot.tvzdemo.feed.models.FeedItem
import com.undabot.tvzdemo.networking.RestService

interface Feed {

    interface View {

        fun displayFeed(items: List<FeedItem>)
        fun displayMoreItems(list: List<FeedItem>)
        fun displayState(state: Feed.State)
    }

    interface Presenter {
        fun loadFeed()
        fun loadMoreItems()
    }

    interface Repository {
        fun fetchFeedItems(success: (List<FeedItem>) -> Unit, error: (Error) -> Unit)
        fun fetchMoreFeedItems(success: (List<FeedItem>) -> Unit, error: (Error) -> Unit)
    }

    sealed class State {
        object Loading : State()
        object LoadingMore : State()
        object Ready : State()
        object Refreshing : State()
        class ErrorState(val error: Error) : State()
    }

    interface Context {

        fun presenter(): Presenter
        fun repository() : Repository
        fun service(): RestService
    }

}

