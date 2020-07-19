package com.sun.room_tips

import android.os.Bundle
import com.sun.room_tips.data.Data
import com.sun.room_tips.database.DataDatabase
import kotlinx.android.synthetic.main.activity_tip3.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Tip3Activity : BaseActivity() {
    override var actionBarSubtitle: String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tip3)
        CoroutineScope(Dispatchers.IO).launch {
            val dao = DataDatabase.getInstance(this@Tip3Activity).dataDao()
            dao.updateData(Data("1", "val 1"), Data("insertedId", "insertedValue"))
            val data = dao.getData()
            if (dao.getData().isNotEmpty()) {
                withContext(Dispatchers.Main) {
                    tip3Content.text = data.toString()
                }
            }
        }
    }
}