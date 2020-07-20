package com.sun.room_tips.database

import androidx.room.Dao
import androidx.room.Query
import com.sun.room_tips.data.User

@Dao
interface UserDao : BaseDao<User> {
    @Query("SELECT * FROM user")
    fun getUsers(): List<User>
}