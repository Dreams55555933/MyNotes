package ru.fomihykh.mynotes

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteRepository(private val noteDao: NoteDao) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    val noteList: LiveData<List<Note>> = noteDao.getNotes()

    fun addNote(note: Note){
        coroutineScope.launch(Dispatchers.IO) {
            noteDao.addNote(note)
        }
    }
    fun deleteNote(id: Int){
        coroutineScope.launch(Dispatchers.IO) {
            noteDao.deleteNote(id)
        }
    }
    fun updateIsChecked(id: Int,value: Boolean){
        coroutineScope.launch(Dispatchers.IO) {
            noteDao.updateIsChecked(id,value)
        }
    }
}