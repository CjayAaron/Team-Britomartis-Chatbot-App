package com.britomartis.android.britobudget.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MessageDao {
    @Insert
    suspend fun insertMessage(message: Message)

    @Query("SELECT * FROM messages ORDER BY messageTime ASC")
    fun getMessages(): LiveData<List<Message>>

    @Update
    suspend fun updateMessage(message: Message)
}