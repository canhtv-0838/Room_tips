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

    fun startTip3Activity(view: View) {
        startActivity(Tip3Activity::class.java)
    }

    fun startTip4Activity(view: View) {
        startActivity(Tip4Activity::class.java)
    }

//    fun startTip5Activity(view: View) {
//        startActivity(Tip5Activity::class.java)
//    }

    fun startTip6Activity(view: View) {
        startActivity(Tip6Activity::class.java)
    }

    private fun startActivity(cls: Class<*>) {
        startActivity(Intent(this, cls))
    }
}
