package com.abg.testapp.ui.cameras

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
import com.abg.testapp.model.Camera
import com.abg.testapp.ui.theme.Beige
import com.abg.testapp.ui.theme.BeigeDark
import com.abg.testapp.ui.theme.Yellow
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import de.charlex.compose.RevealDirection
import de.charlex.compose.RevealSwipe

// I think it's not very good to write everything in one function, but it's faster
@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterialApi::class)
@Composable
fun CameraItem(camera: Camera, onClickFavorite: (Int) -> Unit) {

    //use default icons
    val drawableStar = if (camera.favorites) R.drawable.star else R.drawable.star_2

    //RevealSwipe imported from dependency
    RevealSwipe(
        maxRevealDp = 50.dp,
        modifier = Modifier.padding(vertical = 5.dp),
        backgroundCardEndColor = Beige,
        directions = setOf(RevealDirection.EndToStart),
        hiddenContentEnd = {

            // button for choose favorite
            IconButton(onClick = { onClickFavorite.invoke(camera.id) }) {
                Icon(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .size(30.dp)
                        .border(width = 0.5.dp, color = BeigeDark, shape = RoundedCornerShape(20.dp)),
                    painter = painterResource(id = drawableStar),
                    contentDescription = "favorite",
                    tint = Yellow
                )
            }
        }
    ) {

        // box show card with text and image
        Box {

            Card(
                colors = CardDefaults.cardColors(
                    contentColor = Color.Black,
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {

                // Column show image with icon player
                Column {
                    if (camera.snapshot != "") {
                        Box(modifier = Modifier.height(200.dp)) {

                            GlideImage(
                                model = camera.snapshot,
                                contentDescription = "camera ${camera.name}",
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
                    // row show text with name camera shield icon
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = camera.name, fontSize = 16.sp)

                        if (camera.favorites) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.vector),
                                    contentDescription = "secure",
                                )
                            }
                        }
                    }

                }
            }

            // row show camera icon if rec is true and favorite star if favorite is true
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {

                if (camera.rec) {
                    Image(
                        painter = painterResource(
                            id = R.drawable.rec
                        ),
                        contentDescription = "rec",
                    )
                }

                if (camera.favorites) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.star
                            ),
                            contentDescription = "favorite",
                        )
                    }
                }

            }
        }
    }
}