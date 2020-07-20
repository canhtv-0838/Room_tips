package com.sun.room_tips.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "user"
//    , foreignKeys = arrayOf(
//        ForeignKey(
//            entity = Data::class,
//            parentColumns = arrayOf("id"),
//            childColumns = arrayOf("user_id"),
//            onDelete = ForeignKey.NO_ACTION
//        )
//    )
)
data class User(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val userId: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name ="data_id")
    val dataId: String
)
