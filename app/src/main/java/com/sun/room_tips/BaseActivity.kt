package com.sun.room_tips

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    private val actionBarTitle: String = "Room tips"
    protected abstract var actionBarSubtitle: String

    override fun onStart() {
        super.onStart()
        setActionBarSubtitle()
    }

    private fun setActionBarSubtitle() {
        supportActionBar?.title = actionBarTitle
        if (actionBarSubtitle.isNullOrBlank().not()) {
            supportActionBar?.subtitle = actionBarSubtitle
        }
    }

}