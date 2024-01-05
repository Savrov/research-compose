package com.github.savrov.research.compose.topic.state

import android.os.Parcelable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

@Composable
fun SmartRecomposition() {
    var items by rememberSaveable { mutableStateOf(listOf<Item>()) }
    val onAddAbove = {
        items = listOf(Item(ItemId(items.size + 1))) + items
    }
    val onAddBelow = {
        items = items + Item(ItemId(items.size + 1))
    }
    val onClear = { items = emptyList() }
    SmartListComponent(items = items, onAddAbove = onAddAbove, onAddBelow = onAddBelow, onClear = onClear)
}

@Composable
private fun SmartListComponent(
    modifier: Modifier = Modifier,
    items: List<Item>,
    onAddAbove: () -> Unit,
    onAddBelow: () -> Unit,
    onClear: () -> Unit
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        item(key = "buttons") {
            SmartListButtons(onAddAbove = onAddAbove, onAddBelow = onAddBelow, onClear = onClear)
        }
        items(items, key = { item -> item.id.value }) { item ->
            SmartListItem(item = item)
        }
    }
}

@Composable
private fun SmartListItem(
    modifier: Modifier = Modifier,
    item: Item
) {
    val backgroundColor = Color.random()
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = backgroundColor)
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "Item ${item.id}", color = Color.revert(backgroundColor))
    }
}

@Composable
private fun SmartListButtons(
    modifier: Modifier = Modifier,
    onAddAbove: () -> Unit,
    onAddBelow: () -> Unit,
    onClear: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = onAddAbove) {
            Text(text = "Add above")
        }
        Button(onClick = onClear) {
            Text(text = "Clear")
        }
        Button(onClick = onAddBelow) {
            Text(text = "Add below")
        }
    }
}

@Parcelize
data class Item(val id: ItemId): Parcelable {
    override fun toString(): String = "Item (id=${id.value})"
}

@Parcelize
data class ItemId(val value: Int): Parcelable

private fun Color.Companion.random(): Color {
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)
    return Color(red, green, blue)
}

private fun Color.Companion.revert(color: Color): Color {
    return Color(red = 1f - color.red, green = 1f - color.green, blue = 1f - color.blue, alpha = color.alpha)
}

@Preview(showBackground = true)
@Composable
fun SmartListComponentPreview() {
    SmartListComponent(items = listOf(Item(ItemId(1)), Item(ItemId(2)), Item(ItemId(3))), onAddAbove = {}, onAddBelow = {}, onClear = {})
}