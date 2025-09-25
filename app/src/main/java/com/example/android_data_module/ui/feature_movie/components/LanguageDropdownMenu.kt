package com.example.android_data_module.ui.feature_movie.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SimpleButtonDropdown(
    options: List<String>,
    modifier: Modifier = Modifier,
    placeholder: String = "Chọn...",
    onSelected: (String) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf<String?>(null) }

    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = { expanded = true }) {
            Text(text = selected ?: placeholder)
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Open"
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        selected = option
                        expanded = false
                        onSelected(option)
                    }
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SimpleButtonDropdownPreview() {
    val options = listOf("Option 1", "Option 2", "Option 3")
    SimpleButtonDropdown(options = options)
}