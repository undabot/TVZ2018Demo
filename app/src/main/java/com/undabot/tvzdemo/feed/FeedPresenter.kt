package com.undabot.tvzdemo.feed

import java.lang.ref.WeakReference

class FeedPresenter(private val repository: Feed.Repository,
                    private val view: WeakReference<Feed.View>) : Feed.Presenter {

    override fun loadFeed() {
        view.get()?.displayState(Feed.State.Loading)
        repository.fetchFeedItems(success = {
            view.get()?.displayState(Feed.State.Ready)
            view.get()?.displayFeed(it)
        }, error = {
            view.get()?.displayState(Feed.State.ErrorState(it))
        })
        //TODO
    }

    override fun loadMoreItems() {
        view.get()?.displayState(Feed.State.Loading)

        repository.fetchMoreFeedItems({
            view.get()?.displayState(Feed.State.Ready)
            view.get()?.displayMoreItems(it)


        }, error = {
            view.get()?.displayState(Feed.State.ErrorState(it))

        })
    }

}