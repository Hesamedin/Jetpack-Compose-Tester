package com.example.jctester.livedata

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class LiveDataVM: ViewModel() {

    val nameListState = MutableLiveData(mutableMapOf<String, Color>())
    val textFieldState = MutableLiveData("")

    private val rand = Random(255)

    fun onTextChanged(newText: String) {
        textFieldState.value = newText
    }

    fun addName() {
        val name = textFieldState.value.orEmpty()
        nameListState.value?.set(name, Color(rand.nextInt()))
    }
}