package com.example.compose_ui.ui.screens.intro.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_ui.R
import com.example.compose_ui.ui.components.cores.JPLocalImage
import com.example.compose_ui.ui.components.cores.JPText
import com.example.compose_ui.ui.screens.intro.DotView
import com.example.compose_ui.ui.screens.intro.INTRO_DATA
import com.example.compose_ui.ui.screens.intro.IntroData
import com.example.compose_ui.ui.theme.bgIntroColor

@Composable
fun FirstIntro(item: IntroData) {
    item.run {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(bgIntroColor)
                .paint(
                    painter = painterResource(id = image)
                )
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                JPLocalImage(
                    url = R.drawable.ic_first_intro_2,
                    width = 27.dp,
                    height = 30.dp,
                    modifier = Modifier.offset {
                        IntOffset(10.dp.roundToPx(), 0.dp.roundToPx())
                    })
                JPText(
                    text = title,
                    isCenter = true,
                    color = Color(0xFFECECEC),
                    fontWeight = FontWeight.ExtraBold,
                    size = 36.sp,
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color.Blue, offset = Offset.Zero, blurRadius = 20f
                        )
                    ),
                    lineHeight = 34.sp,
                    mTop = 12.dp
                )
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                JPLocalImage(
                    url = R.drawable.ic_first_intro_1,
                    width = 134.dp,
                    height = 18.dp, mTop = 4.dp
                )
            }
            JPLocalImage(
                mTop = 70.dp,
                url = R.drawable.ic_first_intro_3,
                size = 45.dp,
                mStart = 40.dp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .paint(painter = painterResource(id = R.drawable.img_logo)),
            ) {
                Spacer(modifier = Modifier.height(260.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    JPLocalImage(
                        url = R.drawable.ic_first_intro_4,
                        mTop = 100.dp,
                        size = 50.dp
                    )
                    INTRO_DATA.forEachIndexed { index, _ ->
                        DotView(isActive = index == 0)
                    }
                    JPLocalImage(url = R.drawable.ic_first_intro_5)
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun IntroPreview() {
    INTRO_DATA.getOrNull(0)?.run {
        FirstIntro(this)
    }
}