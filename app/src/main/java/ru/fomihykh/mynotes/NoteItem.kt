package ru.fomihykh.mynotes

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.fomihykh.mynotes.viewmodel.NotesViewModel


@Composable
fun NoteItem(vm: NotesViewModel,notes:List<Note>){
    var lazyListState = rememberLazyListState()
    LaunchedEffect(notes.size) {
        if (notes.isNotEmpty()){
            lazyListState.scrollToItem(notes.size-1)
        }
    }
    LazyColumn(
        Modifier
            .fillMaxWidth().padding(10.dp)
            .border(
                width = 0.dp, color = Color.White, shape = RoundedCornerShape(20.dp)
            )
            .background(Color.White, shape = RoundedCornerShape(20.dp)),
        state = lazyListState

    ) {
        items(notes) {note->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(
                    { vm.changeIsChecked(note.id,!note.isChecked) }
                ) {
                    Icon(
                        Icons.Filled.Check, contentDescription = "chech",
                        tint = if (note.isChecked) Color.Green else Color.Black
                    )
                }
                Text(note.text.toString(), fontSize = 20.sp, textAlign = TextAlign.Center, color = Color.Black)
                IconButton(
                    { vm.delete(note.id) },
                ) {
                    Icon(
                        Icons.Filled.Clear, contentDescription = "delete",
                        tint = Color.Red
                    )
                }
            }
            if (notes.indexOf(note)+1!=notes.size){
                Box(
                    Modifier.fillMaxWidth().height(2.dp)
                        .background(Color(red = 229, green = 230, blue = 234))
                )
            }
        }
    }
}