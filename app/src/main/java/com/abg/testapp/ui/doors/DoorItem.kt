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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abg.testapp.R
import com.abg.testapp.model.Door
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
fun DoorItem(door: Door, onClickFavorite: (Door) -> Unit, onClickRenamed: (Door) -> Unit) {
    RevealSwipe(
        maxRevealDp = 100.dp,
        modifier = Modifier.padding(vertical = 5.dp),
        backgroundCardEndColor = Beige,
        directions = setOf(RevealDirection.EndToStart),
        hiddenContentEnd = {
            IconButton(onClick = { onClickRenamed.invoke(door) }) {

                Icon(
                    modifier = Modifier
                        .size(30.dp)
                        .border(
                            width = 0.5.dp,
                            color = BeigeDark,
                            shape = RoundedCornerShape(20.dp)
                        ),
                    painter = painterResource(id = R.drawable.baseline_edit_24),
                    contentDescription = "",
                    tint = Blue
                )
            }

            IconButton(onClick = { onClickFavorite.invoke(door) }) {
                Icon(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .size(30.dp)
                        .border(
                            width = 0.5.dp,
                            color = BeigeDark,
                            shape = RoundedCornerShape(20.dp)
                        ),
                    painter = painterResource(id = R.drawable.baseline_star_border_24),
                    contentDescription = "",
                    tint = Yellow
                )
            }
        }
    ) {
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
                    .padding(10.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column {
                    if (door.snapshot != "") {
                        Box(modifier = Modifier.height(200.dp)) {

                            GlideImage(
                                model = door.snapshot,
                                contentDescription = "camera ${door.name}",
                                contentScale = ContentScale.Crop
                            )

                            Image(
                                painter = painterResource(id = R.drawable.baseline_play_circle_outline_24),
                                contentDescription = "",
                                Modifier
                                    .size(50.dp)
                                    .align(Alignment.Center),
                            )
                        }
                    }
                    Row(modifier = Modifier.padding(16.dp)) {
                        Column {
                            Text(text = door.name, fontSize = 16.sp)
                            if (door.snapshot != "") {
                                Text(text = "V seti", fontSize = 14.sp, color = Color.Gray)
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Image(
                                painter = painterResource(id = if (door.favorites) R.drawable.baseline_lock_open_24 else R.drawable.baseline_lock_24),
                                contentDescription = "",
                            )
                        }
                    }

                }
            }
        }
    }
}