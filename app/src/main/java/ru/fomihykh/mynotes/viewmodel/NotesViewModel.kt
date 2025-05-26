package ru.fomihykh.mynotes.viewmodel

import android.app.Application
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.fomihykh.mynotes.Note
import ru.fomihykh.mynotes.NoteRepository
import ru.fomihykh.mynotes.NoteRoomDatabase

class NotesViewModel(application: Application): ViewModel() {
    val notes: LiveData<List<Note>>
    private val repository: NoteRepository
    var text by mutableStateOf("")
    var isError by mutableStateOf(false)
    var lazyListState by mutableStateOf(0)

    init {
        val noteDb = NoteRoomDatabase.Companion.getInstance(application)
        val noteDao = noteDb.noteDao()
        repository = NoteRepository(noteDao)
        notes = repository.noteList
    }

    fun addNote(){
        if (text.isEmpty()){
            isError = true
        }else{
            isError = false
            repository.addNote(Note(text))
            text = ""
        }
    }
    fun changeText(value: String){
        text = value
    }
    fun delete(id: Int){
        repository.deleteNote(id)
    }
    fun changeIsChecked(id: Int,isChecked: Boolean){
        repository.updateIsChecked(id,isChecked)
    }
}