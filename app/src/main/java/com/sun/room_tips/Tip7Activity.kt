package com.sun.room_tips

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.sun.room_tips.data.Data
import com.sun.room_tips.database.DataDatabase
import kotlinx.android.synthetic.main.activity_tip7.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Tip7Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tip7)
        CoroutineScope(Dispatchers.IO).launch {
            val dao = DataDatabase.getInstance(this@Tip7Activity).dataDao()
            val data = dao.getDataChanged("2").getDistinct()
            withContext(Dispatchers.Main) {
                data.observe(this@Tip7Activity, Observer {
                    tip7Content.text = it?.toString() ?: "";
                })
            }
            dao.update(Data("2", "id 2 is updated"))
        }
    }
}
