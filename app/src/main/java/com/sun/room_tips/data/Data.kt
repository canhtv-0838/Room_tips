package com.sun.room_tips.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data")
data class Data(@PrimaryKey val id: String, val value: String)