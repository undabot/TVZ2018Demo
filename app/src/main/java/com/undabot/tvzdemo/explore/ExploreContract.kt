package com.undabot.tvzdemo.explore

import com.undabot.tvzdemo.explore.models.ExploreItem
import com.undabot.tvzdemo.explore.models.ExploreUser
import com.undabot.tvzdemo.networking.RestService

interface Explore {

    interface View {

        fun displayExplorePage(items: Pair<List<ExploreUser>,List<ExploreItem>>)
        fun displayMoreItems(loadedItems: List<ExploreItem>)
        fun displayState(state: Explore.State)
    }

    interface Presenter {
        fun loadExplorePage()
        fun loadMoreItems()
    }

    interface Repository {
        fun fetchExploreItems(success: (Pair<List<ExploreUser>,List<ExploreItem>>) -> Unit, error: (Error) -> Unit)
        fun fetchMoreExploreItems(success: (List<ExploreItem>) -> Unit, error: (Error) -> Unit)
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

