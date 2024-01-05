package com.github.savrov.research.compose.topic.state

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun StateTextField() {
    var state by rememberSaveable { mutableStateOf("") }
    val onChange = { newState: String -> state = newState }
    TextField(value = state, onValueChange = onChange)
}

@Preview
@Composable
fun StateTextFieldPreview() {
    StateTextField()
}