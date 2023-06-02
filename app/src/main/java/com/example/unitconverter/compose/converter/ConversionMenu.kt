package com.example.unitconverter.compose.converter

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.unitconverter.data.Conversion

@Composable
fun ConversionMenu(
    list: List<Conversion>,
    modifier: Modifier = Modifier,
    menuItemSelectedEvent: (Conversion) -> Unit
) {
    Log.i("***mss unitconverter.compose.ConversionMenu", "ConversionMenu: start")

    var displayingText by remember { mutableStateOf("Select the conversion type") }
    //To assign the dropdown the same width as textField
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    //icon
    var expanded by remember { mutableStateOf(false) }
    val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

    Column {
        OutlinedTextField(
            value = displayingText,
            onValueChange = { displayingText = it },
            textStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            modifier = modifier
                .fillMaxWidth()
                .onGloballyPositioned { layoutCoordinates ->
                    textFieldSize = layoutCoordinates.size.toSize()
                },
            label = { Text(text = "Conversion type") },
            trailingIcon = {
                Icon(icon, contentDescription = "icon",
                    modifier.clickable { expanded = !expanded })
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
            ),
            readOnly = true
        )

        DropdownMenu(
            expanded = expanded, onDismissRequest = { expanded = false },
            modifier = modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            list.forEach {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = it.description,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    onClick = {
                        displayingText = it.description
                        expanded = false
                        menuItemSelectedEvent(it)
                    })
            }
        }
    }
    Log.i("***mss unitconverter.compose.ConversionMenu", "ConversionMenu: end")
}