package com.example.compose_ui.ui.screens.intro.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import com.example.compose_ui.ui.theme.colorDesc

@Composable
fun FinalIntro(item: IntroData) {
    item.run {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(bgIntroColor)
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Column(
                Modifier.paint(
                    painter = painterResource(id = image),
                    alignment = Alignment.TopCenter
                )
            ) {
                Spacer(modifier = Modifier.height(50.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    JPLocalImage(
                        url = R.drawable.ic_first_intro_3,
                        width = 50.dp,
                        height = 50.dp
                    )
                    Spacer(modifier = Modifier.width(100.dp))
                }
                JPLocalImage(
                    mTop = 160.dp,
                    url = R.drawable.img_shadow,
                    modifier = Modifier.offset {
                        IntOffset(22, 0)
                    }, mStart = 40.dp
                )
                Spacer(modifier = Modifier.height(80.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .paint(
                            painter = painterResource(id = R.drawable.img_logo),
                            alignment = Alignment.TopCenter
                        ),
                ) {
                    JPText(
                        text = title,
                        mTop = 28.dp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        size = 36.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 38.sp,
                        color = Color.White
                    )
                    JPText(
                        text = desc,
                        modifier = Modifier.fillMaxWidth(),
                        isCenter = true,
                        size = 18.sp,
                        mTop = 10.dp,
                        color = colorDesc
                    )
                    Spacer(modifier = Modifier.height(50.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        INTRO_DATA.forEachIndexed { index, _ ->
                            DotView(isActive = index == 2)
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun FinalIntroPreview() {
    INTRO_DATA.getOrNull(2)?.run {
        FinalIntro(this)
    }
}