package ru.fomihykh.mynotes

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.fomihykh.mynotes.ui.theme.MyNotesTheme
import ru.fomihykh.mynotes.viewmodel.NotesViewModel

@Suppress("UNCHECKED_CAST")
class NoteViewModelFactory(val application: Application): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotesViewModel(application) as T
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val owner = LocalViewModelStoreOwner.current
            owner?.let {
                MyNotesTheme {
                    val viewModel: NotesViewModel = viewModel(
                        it,"NoteViewModel",
                        NoteViewModelFactory(LocalContext.current.applicationContext as Application)
                    )
                    Main(viewModel)
                }
            }
        }
    }
    @Preview(showSystemUi = true)
    @Composable
    fun Main(vm: NotesViewModel = viewModel()){
        val notes by vm.notes.observeAsState(listOf())
        Scaffold(
            bottomBar = {
                Button(
                    {vm.addNote()},
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(red = 134, green = 181, blue = 249)
                    )
                ) { Text("Добавить") }
            }
        ) {innerPadding->
            Column(
                Modifier
                    .fillMaxSize()
                    .background(Color(red = 231, green = 231, blue = 239))
                    .statusBarsPadding()
                    .navigationBarsPadding()
                    .imePadding()
                    .padding(top = 40.dp)
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NoteInput(vm)
                NoteItem(vm,notes)
            }
        }
    }
}

