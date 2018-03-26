package com.undabot.tvzdemo.home.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.undabot.tvzdemo.explore.ExploreScreen
import com.undabot.tvzdemo.feed.FeedScreen

class HomePagerAdapter(private val fragments: List<Fragment>,
                       manager: FragmentManager) : FragmentStatePagerAdapter(manager) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> FeedScreen()
            1 -> ExploreScreen()
            else -> {
                throw IllegalStateException("We don't want an illegal state so we throw an exception.")
            }
        }
    }

    override fun getCount(): Int {
        return fragments.size
    }
}

