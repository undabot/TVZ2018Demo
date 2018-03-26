package com.undabot.tvzdemo.explore

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.undabot.tvzdemo.R
import com.undabot.tvzdemo.explore.models.ExploreUser
import kotlinx.android.synthetic.main.explore_user.view.*

class ExploreUsersAdapter(val list: List<ExploreUser>) : RecyclerView.Adapter<ExploreUsersAdapter.UserHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreUsersAdapter.UserHolder {
        return UserHolder(LayoutInflater.from(parent.context).inflate(R.layout.explore_user, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ExploreUsersAdapter.UserHolder, position: Int) {
        holder.bind(list[position])
    }

    class UserHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(exploreUser: ExploreUser) {
            view.setOnClickListener {
                view.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/${exploreUser.name}"))) }
            view.username.text = exploreUser.name
            Glide.with(view).load(exploreUser.profilePhotoLink).into(view.profile_image)
        }
    }
}