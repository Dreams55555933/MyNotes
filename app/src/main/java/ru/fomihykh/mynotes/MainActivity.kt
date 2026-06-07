package ru.fomihykh.mynotes

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
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
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .windowInsetsPadding(WindowInsets.navigationBars), // <-- ЭТО ВАЖНО
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        {vm.addNote()},
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        enabled = !vm.text.isEmpty(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(red = 2, green = 28, blue = 130, alpha = 255),
                            contentColor = Color.White
                        )
                    ) { Text("Добавить") }
                }

            }
        ) {innerPadding->
            Column(
                Modifier
                    .fillMaxSize()
                    .background(Color(red = 231, green = 231, blue = 239))
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

