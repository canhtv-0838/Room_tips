package com.sun.room_tips

import android.content.Intent
import android.os.Bundle
import android.view.View

class MainActivity : BaseActivity() {

    override var actionBarSubtitle: String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startTip1Activity(view: View) {
        startActivity(Tip1Activity::class.java)
    }

    fun startTip2Activity(view: View) {
        startActivity(Tip2Activity::class.java)
    }

    private fun startActivity(cls: Class<*>) {
        startActivity(Intent(this, cls))
    }
}
