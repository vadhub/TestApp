package com.abg.testapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abg.testapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dialog(openDialog: MutableState<Boolean>, onConfirm: (String) -> Unit, text: String) {
    val textState = remember { mutableStateOf(text) }

    if (openDialog.value) {
        AlertDialog(
            modifier = Modifier.size(200.dp, 250.dp),
            textContentColor = Color.Black,
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = stringResource(id = R.string.rename), fontSize = 16.sp)
            },
            text = {
                OutlinedTextField(
                    colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.Black),
                    value = textState.value,
                    onValueChange = { textState.value = it },
                    textStyle = TextStyle(fontSize = 14.sp)
                )
            },
            confirmButton = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(contentColor = Color.Black),
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            openDialog.value = false
                            onConfirm.invoke(textState.value)
                        }
                    ) {
                        Text(text = stringResource(id = R.string.ok))
                    }
                }
            }
        )
    }

}