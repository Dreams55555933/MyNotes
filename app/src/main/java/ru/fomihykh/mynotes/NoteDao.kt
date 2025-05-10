package ru.fomihykh.mynotes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    fun getNotes(): LiveData<List<Note>>

    @Insert
    fun addNote(note: Note)

    @Query("DELETE FROM notes WHERE noteId = :id")
    fun deleteNote(id: Int)

    @Query("UPDATE notes SET isChecked = :value WHERE noteId = :id")
    fun updateIsChecked(id: Int,value: Boolean)
}