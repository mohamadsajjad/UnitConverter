package com.example.unitconverter.compose

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.Conversion
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun InputBlock(
    conversion: Conversion,
    inputText: MutableState<String>,
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    calculate: (String) -> Unit
) {
    Log.i("***mss unitconverter.compose.InputBlock", "InputBlock: start")

    val textFieldLabel = buildAnnotatedString {
        append("Conversion input (")
        withStyle(
            style = SpanStyle(fontWeight = FontWeight.Bold)
        ) {
            append(conversion.convertFrom)
        }
        append(")")
    }

    Column(modifier = modifier.padding(0.dp, 15.dp, 0.dp, 0.dp)) {

        OutlinedTextField(
            label = {
                Text(text = textFieldLabel)
            },
            value = inputText.value,
            onValueChange = { inputText.value = it },
            modifier = modifier
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = true,
                keyboardType = KeyboardType.Number
            ),
            textStyle = TextStyle(color = Color.DarkGray, fontSize = 18.sp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
            )
        )

        Spacer(modifier = modifier.height(25.dp))

        TextButton(onClick = {
            if (inputText.value.isNotEmpty())
                calculate(inputText.value)
            else
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Please fill input field.")
                }
        }, modifier = modifier.fillMaxWidth(1f)) {
            Text(
                text = "Convert",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
        }
    }

    Log.i("***mss unitconverter.compose.InputBlock", "InputBlock: end")
}