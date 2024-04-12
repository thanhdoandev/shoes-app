package com.example.compose_ui.ui.screens.intro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_ui.R
import com.example.compose_ui.ui.components.cores.JPButton
import com.example.compose_ui.ui.screens.intro.components.FinalIntro
import com.example.compose_ui.ui.screens.intro.components.FirstIntro
import com.example.compose_ui.ui.screens.intro.components.SecondIntro
import com.example.compose_ui.ui.theme.bgIntroColor
import com.example.compose_ui.ui.theme.primaryText
import com.example.compose_ui.ui.theme.yellowColor

data class IntroData(val id: Int, val title: String, val image: Int, val desc: String)

val INTRO_DATA: List<IntroData> = listOf(
    IntroData(
        id = 0,
        title = "WELL COME TO\nNIKE",
        image = R.drawable.img_first_ntro,
        desc = ""
    ),
    IntroData(
        id = 1,
        title = "Let’s Start Journey\nWith Nike",
        image = R.drawable.img_second_intro,
        desc = "Smart, Gorgeous & Fashionable\nCollection Explor Now"
    ),
    IntroData(
        id = 2,
        title = "You Have the\nPower To",
        image = R.drawable.img_final_intro,
        desc = "There Are Many Beautiful And Attractive\nPlants To Your Room"
    )
)

@Composable
fun DotView(isActive: Boolean) {
    Row {
        Spacer(
            modifier = Modifier
                .width(6.dp)
                .height(4.dp)
        )
        Spacer(
            modifier = Modifier
                .width(if (isActive) 50.dp else 24.dp)
                .height(6.dp)
                .background(
                    if (isActive) Color.White else yellowColor,
                    shape = RoundedCornerShape(2.dp)
                )
        )
    }
}

@Composable
fun IntroScreen(onPress: () -> Unit) {
    var indexScreen by rememberSaveable {
        mutableIntStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = bgIntroColor)
    ) {
        Column(
            modifier = Modifier
                .weight(6f)
        ) {
            INTRO_DATA.getOrNull(indexScreen)?.run {
                when (indexScreen) {
                    0 -> FirstIntro(item = this)
                    1 -> SecondIntro(item = this)
                    else -> FinalIntro(item = this)
                }
            }
        }
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            JPButton(
                label = stringResource(if (indexScreen == 0) R.string.introButtonLabel else R.string.nextLabelButton),
                bgColor = Color.White,
                textColor = primaryText
            ) {
                if (indexScreen == 2) onPress() else indexScreen += 1
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun IntroScreenPreview() {
    IntroScreen {

    }
}