package com.sena.biblioteca_compose_manual.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MangaCover(
    @DrawableRes imagenRes: Int,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    ancho: Dp = 75.dp,
    alto: Dp = 105.dp
) {
    Image(
        painter = painterResource(id = imagenRes),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(width = ancho, height = alto)
            .clip(RoundedCornerShape(8.dp))
    )
}
