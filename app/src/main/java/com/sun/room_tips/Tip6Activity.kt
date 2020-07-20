package com.sun.room_tips

import android.os.Bundle
import com.sun.room_tips.database.DataDatabase
import kotlinx.android.synthetic.main.activity_tip6.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Tip6Activity : BaseActivity() {
    override var actionBarSubtitle: String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tip6)
        CoroutineScope(Dispatchers.IO).launch {
            val daoData = DataDatabase.getInstance(this@Tip6Activity).dataDao()
            val userData = DataDatabase.getInstance(this@Tip6Activity).userDao()

            val data = daoData.getData()
            val user = userData.getUsers()
            val dataAndUser = daoData.getDataAndUser()

            if (daoData.getData().isNotEmpty()) {
                withContext(Dispatchers.Main) {
                    dataContent.text = data.toString()
                }
            }
            if (userData.getUsers().isNotEmpty()) {
                withContext(Dispatchers.Main) {
                    userContent.text = user.toString()
                }
            }
            if (daoData.getDataAndUser().isNotEmpty()) {
                withContext(Dispatchers.Main) {
                    dataAndUserContent.text = dataAndUser.toString()
                }
            }
        }
    }
}