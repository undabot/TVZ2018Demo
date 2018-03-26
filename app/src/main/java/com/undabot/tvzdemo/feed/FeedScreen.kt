package com.undabot.tvzdemo.feed

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.paginate.Paginate
import com.undabot.tvzdemo.R
import com.undabot.tvzdemo.feed.models.FeedItem
import kotlinx.android.synthetic.main.feed_screen.view.*
import timber.log.Timber

class FeedScreen : Fragment(), Feed.View {
    var state: Feed.State = Feed.State.Ready
    private val feedContext = FeedContext(this)
    private val presenter: Feed.Presenter by lazy { feedContext.presenter() }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val container = inflater.inflate(R.layout.feed_screen, container, false)
        return container
    }

    override fun displayFeed(items: List<FeedItem>) {
        view?.user_feed!!.adapter = FeedAdapter(items.toMutableList())
        Paginate.with(view?.user_feed, object : Paginate.Callbacks {
            override fun isLoading(): Boolean {
                return state == Feed.State.Loading
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
        presenter.loadFeed()
        view?.user_feed!!.layoutManager = LinearLayoutManager(this.context)

    }

    override fun displayMoreItems(list: List<FeedItem>) {

    }

    override fun displayState(state: Feed.State) {
        this.state = state

        when (state) {
            Feed.State.Refreshing -> displayRefreshing()
            Feed.State.Loading -> displayLoading()
            Feed.State.LoadingMore -> displayLoadingMore()
            Feed.State.Ready -> displayReady()
            is Feed.State.ErrorState -> displayError(state)
        }
    }

    private fun displayError(state: Feed.State.ErrorState) {
        view?.let { Snackbar.make(it, state.error.message, Snackbar.LENGTH_SHORT) }
    }

    private fun displayReady() {
        view!!.progress_bar!!.visibility = View.GONE
        view!!.user_feed!!.visibility = View.VISIBLE
    }

    private fun displayLoadingMore() {
    }

    private fun displayLoading() {
        Timber.e("Loading")
        view?.progress_bar!!.visibility = View.VISIBLE
    }

    private fun displayRefreshing() {
    }


}

