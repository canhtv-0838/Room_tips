package com.sun.room_tips.data

import androidx.room.Embedded
import androidx.room.Relation

data class DataAndUser(
    @Embedded
    var data: Data? = null,

    @Relation(
        parentColumn = "id",
        entityColumn = "data_id"
    )
    var users: List<User> = ArrayList()
)
