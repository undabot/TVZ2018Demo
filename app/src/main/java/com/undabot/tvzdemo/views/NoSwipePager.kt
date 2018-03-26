package com.undabot.tvzdemo.views

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import java.util.jar.Attributes

class NoSwipePager(context: Context, attributes: AttributeSet) : ViewPager(context, attributes) {


    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }
}