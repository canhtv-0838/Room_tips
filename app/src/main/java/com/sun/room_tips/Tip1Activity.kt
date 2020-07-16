package com.sun.room_tips

import android.os.Bundle
import com.sun.room_tips.database.DataDatabase
import kotlinx.android.synthetic.main.activity_tip1.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Tip1Activity : BaseActivity() {
    override var actionBarSubtitle: String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tip1)
        CoroutineScope(Dispatchers.IO).launch {
            val dao = DataDatabase.getInstance(this@Tip1Activity).dataDao()
            val data = dao.getData()
            if (dao.getData().isNotEmpty()) {
                withContext(Dispatchers.Main) {
                    tip1Content.text = data.toString()
                }
            }
        }
    }
}
