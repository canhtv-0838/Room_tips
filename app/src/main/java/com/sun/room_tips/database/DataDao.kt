package com.sun.room_tips.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.sun.room_tips.data.Data

@Dao
interface DataDao : BaseDao<Data> {

    @Query("SELECT * FROM Data")
    fun getData(): List<Data>

    @Transaction
    fun updateData(deletedData: Data, insertedData: Data) {
        delete(deletedData)
        insert(insertedData)
    }
}
