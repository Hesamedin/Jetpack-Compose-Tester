package com.example.jctester.livedata

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class LiveDataActivity: ComponentActivity() {

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, LiveDataActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @Composable
    fun MainScreen(vm : LiveDataVM = LiveDataVM()) {
        val namesListState = vm.nameListState.observeAsState(mutableMapOf())
        val textEditState = vm.textFieldState.observeAsState("")

        Surface(
            color = Color.DarkGray,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        modifier = Modifier.weight(1f),
                        value = textEditState.value,
                        onValueChange = { e -> vm.onTextChanged(e) }
                    )
                    Button(
                        onClick = {
                            if (textEditState.value.isEmpty()) return@Button
                            vm.addName()
                            vm.onTextChanged("")
                        }) {
                        Text(
                            text = "Add Name",
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Column {
                    for (name in namesListState.value) {
                        Name(name.key, name.value)
                    }
                }
            }
        }
    }

    @Composable
    fun Name(name: String, bgColor: Color) {
        Surface(
            color = bgColor,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.labelMedium.copy(color = Color.White),
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MainScreen()
    }
}