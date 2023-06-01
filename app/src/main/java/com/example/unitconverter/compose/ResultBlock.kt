package com.example.unitconverter.compose

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ResultBlock(
    convertedTo: String,
    result: String,
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    modifier: Modifier = Modifier
) {
    Log.i("***mss unitconverter.compose.ResultBlock", "ResultBlock: start")

    val clipboardManager = LocalClipboardManager.current
    val textFieldLabel = buildAnnotatedString {
        append("Conversion result (")
        withStyle(
            style = SpanStyle(fontWeight = FontWeight.Bold)
        ) {
            append(convertedTo)
        }
        append(")")
    }

    OutlinedTextField(
        value = result,
        onValueChange = {},
        label = {
            Text(text = textFieldLabel)
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(0.dp, 25.dp, 0.dp, 0.dp),
        textStyle = TextStyle(color = Color.DarkGray, fontSize = 18.sp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
        ),
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_content_copy_24),
                contentDescription = "copy icon",
                modifier.clickable {
                    coroutineScope.launch {
                        clipboardManager.setText(AnnotatedString(result))
                        snackbarHostState.showSnackbar(message = "Copied to clipboard.")
                    }
                }
            )
        },
        readOnly = true
    )

    Log.i("***mss unitconverter.compose.ResultBlock", "ResultBlock: end")
}