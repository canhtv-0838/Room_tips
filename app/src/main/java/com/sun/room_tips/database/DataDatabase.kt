package com.sun.room_tips.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sun.room_tips.data.Data
import com.sun.room_tips.data.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Data::class, User::class), version = 1)
abstract class DataDatabase : RoomDatabase() {

    abstract fun dataDao(): DataDao
    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: DataDatabase? = null

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
                            getInstance(context).userDao().insert(PREPOPULATE_USER)
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
        val PREPOPULATE_USER = listOf(
            User("1", "name 1", "1"),
            User("2", "name 2", "1"),
            User("3", "name 3", "1"),
            User("4", "name 4", "2"),
            User("5", "name 5", "2")
        )

    }
}