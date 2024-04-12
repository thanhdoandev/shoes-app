package com.example.compose_ui.ui.components.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_ui.R

@Composable
fun BannerCard() {
    Card(
        modifier = Modifier
            .padding(16.dp, 1.dp)
            .fillMaxWidth()
            .height(100.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.img_banner_sale),
                    contentDescription = ""
                )
            }
            Column {
                Row {
                    Image(
                        modifier = Modifier
                            .height(24.dp)
                            .width(24.dp),
                        painter = painterResource(id = R.drawable.ic_new),
                        contentDescription = ""
                    )
                    Image(
                        painter = painterResource(id = R.drawable.img_banner),
                        contentDescription = ""
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun BannerCardPreview() {
    BannerCard()
}