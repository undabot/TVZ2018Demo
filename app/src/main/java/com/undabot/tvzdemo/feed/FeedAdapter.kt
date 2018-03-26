package com.undabot.tvzdemo.feed

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.disklrucache.DiskLruCache
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.cache.DiskCache
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory
import com.bumptech.glide.request.RequestOptions
import com.undabot.tvzdemo.R
import com.undabot.tvzdemo.feed.models.FeedItem
import kotlinx.android.synthetic.main.feed_item.view.*

class FeedAdapter(val list: MutableList<FeedItem>) : RecyclerView.Adapter<FeedAdapter.FeedItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedAdapter.FeedItemHolder{
        return FeedItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.feed_item,parent,false))
    }

    override fun getItemCount(): Int {
            return list.size
    }

    override fun onBindViewHolder(holder: FeedAdapter.FeedItemHolder, position: Int) {
        holder.bind(list[position])
    }


    class FeedItemHolder(val view: View): RecyclerView.ViewHolder(view){
        fun bind(feedItem: FeedItem){
            view.username.text = feedItem.userName
            view.likes.text = "${feedItem.likes} likes"
            Glide.with(view).load(feedItem.photoLink)
                    .apply(RequestOptions().centerCrop().placeholder(R.drawable.ic_downloading)).into(view.image)
        }
    }
}

