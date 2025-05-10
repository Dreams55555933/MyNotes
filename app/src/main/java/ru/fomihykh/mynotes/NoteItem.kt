package ru.fomihykh.mynotes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.fomihykh.mynotes.viewmodel.NotesViewModel

@Composable
fun NoteItem(vm: NotesViewModel,notes:List<Note>){
    LazyColumn(
        Modifier
            .fillMaxWidth().padding(10.dp)
            .border(
                width = 0.dp, color = Color.White, shape = RoundedCornerShape(20.dp)
            )
            .background(Color.White, shape = RoundedCornerShape(20.dp))
            .weight(1f).padding(bottom = 20.dp)

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
                Text(note.text.toString(), fontSize = 20.sp, textAlign = TextAlign.Center)
                IconButton(
                    { vm.delete(note.id) },
                ) {
                    Icon(
                        Icons.Filled.Clear, contentDescription = "delete",
                        tint = Color.Red
                    )
                }
            }
            Box(
                Modifier.fillMaxWidth().height(2.dp)
                    .background(Color(red = 229, green = 230, blue = 234))
            )
        }
    }
}