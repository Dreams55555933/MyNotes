package ru.fomihykh.mynotes

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
class Note{
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "noteId")
    var id: Int = 0
    @ColumnInfo(name = "text")
    var text: String? = null
    @ColumnInfo(name = "isChecked")
    var isChecked: Boolean = false

    constructor(){}

    constructor(id: Int,text: String){
        this.id = id
        this.text = text
    }

    constructor(text: String){
        this.text = text
    }
}