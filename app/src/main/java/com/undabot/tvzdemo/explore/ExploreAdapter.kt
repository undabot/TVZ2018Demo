package com.undabot.tvzdemo.explore

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.undabot.tvzdemo.R
import com.undabot.tvzdemo.explore.models.ExploreItem
import com.undabot.tvzdemo.explore.models.ExploreUser
import kotlinx.android.synthetic.main.explore_users.view.*
import kotlinx.android.synthetic.main.feed_item.view.*


class ExploreAdapter(private val users: List<ExploreUser>,
                     private val gridItems: MutableList<ExploreItem>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun updateItemsWith(list: List<ExploreItem>) {
        val itemSize = gridItems.size + 1
        gridItems.addAll(list)
        notifyItemRangeInserted(itemSize, list.size)
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> R.layout.explore_users
            else -> R.layout.explore_item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.explore_users -> ExploreUsersListHolder(LayoutInflater.from(parent.context).inflate(R.layout.explore_users, parent, false))
            R.layout.explore_item -> ExploreItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.explore_item, parent, false))
            else -> throw IllegalStateException("This should never happen")
        }
    }

    override fun getItemCount(): Int {
        return gridItems.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ExploreItemHolder -> holder.bind(gridItems[position - 1])
            is ExploreUsersListHolder -> holder.bind(users)
        }

    }

    class ExploreItemHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(exploreItem: ExploreItem) {
            Glide.with(view).load(exploreItem.photoLink).apply(RequestOptions().centerCrop()).into(view.image)
        }
    }

    class ExploreUsersListHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(users: List<ExploreUser>) {
            view.users_list.adapter = ExploreUsersAdapter(users)
            view.users_list.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

}

