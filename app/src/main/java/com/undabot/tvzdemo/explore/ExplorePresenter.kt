package com.undabot.tvzdemo.explore

import java.lang.ref.WeakReference

class ExplorePresenter(private val repository: Explore.Repository,
                       private val view: WeakReference<Explore.View>) : Explore.Presenter {

    override fun loadExplorePage() {
        view.get()?.displayState(Explore.State.Loading)
        repository.fetchExploreItems(success = {
            view.get()?.displayState(Explore.State.Ready)
            view.get()?.displayExplorePage(it)
        }, error = {
            view.get()?.displayState(Explore.State.ErrorState(it))
        })
        //TODO
    }

    override fun loadMoreItems() {
        view.get()?.displayState(Explore.State.Loading)
        repository.fetchMoreExploreItems({
            view.get()?.displayState(Explore.State.Ready)
            view.get()?.displayMoreItems(it)
        }, error = {
            view.get()?.displayState(Explore.State.ErrorState(it))

        })
    }

}