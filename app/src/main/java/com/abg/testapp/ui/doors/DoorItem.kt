package com.abg.testapp.ui.doors

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abg.testapp.R
import com.abg.testapp.model.Door
import com.abg.testapp.ui.Dialog
import com.abg.testapp.ui.theme.Beige
import com.abg.testapp.ui.theme.BeigeDark
import com.abg.testapp.ui.theme.Blue
import com.abg.testapp.ui.theme.Yellow
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import de.charlex.compose.RevealDirection
import de.charlex.compose.RevealSwipe

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterialApi::class)
@Composable
fun DoorItem(door: Door, onClickFavorite: (Int) -> Unit, onClickRenamed: (Int, String) -> Unit) {

    val showDialog = remember { mutableStateOf(false) }

    val drawableStar = if (door.favorites) R.drawable.star else R.drawable.star_2

    // EditDialog class wrapper over AlertDialog
    Dialog(openDialog = showDialog, onConfirm = {
        onClickRenamed.invoke(door.id, it)
    }, text = door.name)

    //RevealSwipe imported from dependency
    RevealSwipe(
        maxRevealDp = 100.dp,
        backgroundCardEndColor = Beige,
        directions = setOf(RevealDirection.EndToStart),
        hiddenContentEnd = {

            // button for edit door name
            IconButton(onClick = {
                showDialog.value = true
            }) {
                Icon(
                    modifier = Modifier
                        .size(30.dp)
                        .border(
                            width = 0.5.dp,
                            color = BeigeDark,
                            shape = RoundedCornerShape(30.dp)
                        ),
                    painter = painterResource(id = R.drawable.edit_3),
                    contentDescription = "edit",
                    tint = Blue
                )
            }

            // button for choose favorite
            IconButton(onClick = { onClickFavorite.invoke(door.id) }) {
                Icon(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .size(30.dp)
                        .border(
                            width = 0.5.dp,
                            color = BeigeDark,
                            shape = RoundedCornerShape(20.dp)
                        ),
                    painter = painterResource(id = drawableStar),
                    contentDescription = "favorite",
                    tint = Yellow
                )
            }
        }
    ) {

        // box show card with text and image
        Box(
            Modifier
                .fillMaxWidth()
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    contentColor = Color.Black,
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .padding(8.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {

                // Column show image with icon player
                Column {
                    if (door.snapshot != "") {
                        Box(modifier = Modifier.height(200.dp)) {

                            //Glide from dependencies
                            GlideImage(
                                model = door.snapshot,
                                contentDescription = "camera ${door.name}",
                                contentScale = ContentScale.Crop
                            )

                            Image(
                                painter = painterResource(id = R.drawable.play_button),
                                contentDescription = "play",
                                Modifier
                                    .size(50.dp)
                                    .align(Alignment.Center),
                            )
                        }
                    }

                    // bottom text: name camera and below "is online" if image exist
                    Row(modifier = Modifier.padding(18.dp)) {
                        Column {
                            Text(text = door.name, fontSize = 16.sp)
                            if (door.snapshot != "") {
                                Text(text = stringResource(id = R.string.online), fontSize = 14.sp, color = Color.Gray)
                            }
                        }

                        // row show icon open/close lock depending on favorite or not
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Image(
                                painter = painterResource(id = if (door.favorites) R.drawable.lockoff else R.drawable.lockon),
                                contentDescription = "lock",
                            )
                        }
                    }

                }
            }
        }
    }
}