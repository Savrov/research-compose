package com.github.savrov.research.compose.topic.state

import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun UnidirectionalDataFlow() {
    var state by rememberSaveable { mutableStateOf(true) }
    val onChange = { newState: Boolean -> state = newState }
    InnerFunction(state = state, onChange = onChange)
}

@Composable
private fun InnerFunction(
    state: Boolean,
    onChange: (Boolean) -> Unit
) {
    Switch(checked = state, onCheckedChange = onChange)
}

@Preview
@Composable
fun UnidirectionalDataFlowPreview() {
    UnidirectionalDataFlow()
}