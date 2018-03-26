package com.undabot.tvzdemo.explore

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.paginate.Paginate
import com.undabot.tvzdemo.R
import com.undabot.tvzdemo.explore.models.ExploreItem
import com.undabot.tvzdemo.explore.models.ExploreUser
import kotlinx.android.synthetic.main.explore_screen.view.*

class ExploreScreen : Fragment(), Explore.View {

    var state: Explore.State = Explore.State.Ready
    lateinit var adapter: ExploreAdapter
    private val exploreContext = ExploreContext(this)
    private val presenter: Explore.Presenter by lazy { exploreContext.presenter() }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.explore_screen, container, false)
    }

    override fun displayExplorePage(items: Pair<List<ExploreUser>, List<ExploreItem>>) {
        adapter = ExploreAdapter(items.first, items.second.toMutableList())
        view?.explore_feed!!.adapter = adapter
        Paginate.with(view?.explore_feed, object : Paginate.Callbacks {
            override fun isLoading(): Boolean {
                return state == Explore.State.Loading
            }

            override fun hasLoadedAllItems(): Boolean {
                return false
            }

            override fun onLoadMore() {
                presenter.loadMoreItems()
            }
        }).build()
    }

    override fun onResume() {
        super.onResume()
        presenter.loadExplorePage()
        view?.explore_feed!!.layoutManager = GridLayoutManager(this.context, 4).apply {
            this.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (position) {
                        0 -> 4
                        else -> 1
                    }
                }
            }
        }
    }

    override fun displayMoreItems(loadedItems: List<ExploreItem>) {
        adapter.updateItemsWith(loadedItems)
    }

    override fun displayState(state: Explore.State) {
        this.state = state
        when (state) {
            Explore.State.Refreshing -> displayRefreshing()
            Explore.State.Loading -> displayLoading()
            Explore.State.LoadingMore -> displayLoadingMore()
            Explore.State.Ready -> displayReady()
            is Explore.State.ErrorState -> displayError(state)
        }
    }

    private fun displayError(state: Explore.State.ErrorState) {
        view?.let { Snackbar.make(it, state.error.message, Snackbar.LENGTH_SHORT) }
    }

    private fun displayReady() {
        view!!.progress_bar!!.visibility = View.GONE
        view!!.explore_feed!!.visibility = View.VISIBLE
    }

    private fun displayLoadingMore() {
    }

    private fun displayLoading() {
        view?.progress_bar!!.visibility = View.VISIBLE
    }

    private fun displayRefreshing() {
    }

}

