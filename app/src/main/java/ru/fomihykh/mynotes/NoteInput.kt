package ru.fomihykh.mynotes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.fomihykh.mynotes.viewmodel.NotesViewModel

@Composable
fun NoteInput(vm: NotesViewModel){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = vm.text,
            isError = vm.isError,
            supportingText = { if (vm.isError)Text("Заполните заметку") },
            textStyle = TextStyle(fontSize = 20.sp),
            label = {Text("Заметка", fontWeight = FontWeight.Bold)},
            onValueChange = {
                vm.changeText(it)
                vm.isError = false
            },
            shape = RoundedCornerShape(20.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                unfocusedBorderColor = Color.White,
                focusedBorderColor = Color.White,
            ),
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        )
    }
}