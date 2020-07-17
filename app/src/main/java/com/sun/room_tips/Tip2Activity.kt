package com.sun.room_tips

import android.os.Bundle
import com.sun.room_tips.data.Data
import com.sun.room_tips.database.DataDatabase
import kotlinx.android.synthetic.main.activity_tip2.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Tip2Activity : BaseActivity() {
    override var actionBarSubtitle: String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tip2)
        CoroutineScope(Dispatchers.IO).launch {
            val dao = DataDatabase.getInstance(this@Tip2Activity).dataDao()
            dao.insert(Data("3", "val 3"))
            dao.insert(Data("4", "val 4"))
            val data = dao.getData()
            if (dao.getData().isNotEmpty()) {
                withContext(Dispatchers.Main) {
                    tip2Content.text = data.toString()
                }
            }
        }
    }
}
