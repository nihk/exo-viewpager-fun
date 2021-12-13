package com.example.videopager.ui.extensions

import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.drop

fun ViewPager2.pageIdlings(): Flow<Unit> = callbackFlow {
    val callback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrollStateChanged(state: Int) {
            if (state != ViewPager2.SCROLL_STATE_IDLE) return
            trySend(Unit)
        }
    }

    registerOnPageChangeCallback(callback)

    awaitClose { unregisterOnPageChangeCallback(callback) }
}

fun ViewPager2.pageChanges(): Flow<Unit> = callbackFlow {
    val callback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            trySend(Unit)
        }
    }

    registerOnPageChangeCallback(callback)

    awaitClose { unregisterOnPageChangeCallback(callback) }
}.drop(1) // Ignore the initial emission which happens immediately on subscription

val ViewPager2.isIdle get() = scrollState == ViewPager2.SCROLL_STATE_IDLE
