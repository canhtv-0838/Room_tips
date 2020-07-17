package com.sun.room_tips.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sun.room_tips.data.Data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

@Database(entities = arrayOf(Data::class), version = 1)
abstract class DataDatabase : RoomDatabase() {

    abstract fun dataDao(): DataDao

    companion object {

        @Volatile private var INSTANCE: DataDatabase? = null

        fun getInstance(context: Context): DataDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }


        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                DataDatabase::class.java, "Sample.db"
            )
                // prepopulate the database after onCreate was called
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // insert the data on the IO Thread
                        CoroutineScope(Dispatchers.IO).launch {
                            getInstance(context).dataDao().insert(PREPOPULATE_DATA)
                        }
                    }

//                    override fun onOpen(db: SupportSQLiteDatabase) {
//                        super.onOpen(db)
//                        CoroutineScope(Dispatchers.IO).launch {
//                            getInstance(context).dataDao().insertData(PREPOPULATE_DATA)
//                        }
//                    }
                })
                .build()

        val PREPOPULATE_DATA = listOf(Data("1", "val 1"), Data("2", "val 2"))

    }
}