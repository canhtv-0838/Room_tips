package com.sun.room_tips.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.sun.room_tips.data.Data
import com.sun.room_tips.data.DataAndUser
import com.sun.room_tips.data.DataValue

@Dao
interface DataDao : BaseDao<Data> {

    @Query("SELECT * FROM Data")
    fun getData(): List<Data>

    @Transaction
    fun updateData(deletedData: Data, insertedData: Data) {
        delete(deletedData)
        insert(insertedData)
    }

    @Query("SELECT value FROM data")
    fun readOnlyValue(): List<DataValue>

    @Transaction
    @Query("SELECT * FROM Data")
    fun getDataAndUser(): List<DataAndUser>

    @Query("SELECT * FROM Data WHERE id=:dataId")
    fun getDataChanged(dataId: String): LiveData<Data>
}
