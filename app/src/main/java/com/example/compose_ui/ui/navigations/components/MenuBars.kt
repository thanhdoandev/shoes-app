package com.example.compose_ui.ui.navigations.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.DeliveryDining
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import com.example.compose_ui.R
import com.example.compose_ui.ui.components.cores.JPColumn
import com.example.compose_ui.ui.components.cores.JPIcon
import com.example.compose_ui.ui.components.cores.JPRow
import com.example.compose_ui.ui.components.cores.JPText
import com.example.compose_ui.ui.data.enums.EScreenName
import com.example.compose_ui.ui.extensions.onClickNoEffect
import com.example.compose_ui.ui.navigations.getCurrentRoute
import com.example.compose_ui.ui.theme.CustomComposeTheme
import com.example.compose_ui.ui.theme.font_18
import com.example.compose_ui.ui.theme.font_22
import com.example.compose_ui.ui.theme.primaryColor
import com.example.compose_ui.ui.theme.size_100
import com.example.compose_ui.ui.theme.size_32
import com.example.compose_ui.ui.theme.size_6
import com.example.compose_ui.ui.theme.size_60
import com.example.compose_ui.ui.theme.size_80
import com.example.compose_ui.ui.utils.getDeviceSize

sealed class Screens(
    val title: Int,
    val route: EScreenName,
    val icon: ImageVector,
    val isToggle: Boolean = false
) {
    data object Home : Screens(
        title = R.string.homeNameTab,
        route = EScreenName.HOME,
        icon = Icons.Default.Home,
    )

    data object Settings : Screens(
        title = R.string.settingLabel,
        route = EScreenName.SETTINGS,
        icon = Icons.Default.Settings
    )

    data object Delivery : Screens(
        title = R.string.orderDeliveryLabel,
        route = EScreenName.DELIVERY,
        icon = Icons.Default.DeliveryDining,
    )

    data object HistoriesOrder : Screens(
        title = R.string.historyOrder,
        route = EScreenName.HISTORY,
        icon = Icons.Default.History
    )

    data object DarkMode : Screens(
        title = R.string.darkMode,
        route = EScreenName.LANGUAGE,
        icon = Icons.Default.DarkMode,
        isToggle = true
    )
}

val MENU_SCREENS = listOf(
    Screens.Home,
    Screens.Delivery,
    Screens.HistoriesOrder,
    Screens.Settings,
    Screens.DarkMode
)

@Composable
fun MenuItem(
    isDarkMode: Boolean = false,
    screen: Screens,
    isSelected: Boolean = false,
    onClick: (route: EScreenName) -> Unit = {},
    onSwitch: (isChecked: Boolean) -> Unit = {}
) {
    screen.run {
        HorizontalDivider(thickness = 1.dp)
        NavigationDrawerItem(
            shape = RectangleShape,
            label = {
                JPRow(modifier = Modifier.fillMaxSize(), isCenterVer = true, pEnd = size_6) {
                    JPText(
                        text = stringResource(id = title),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = if (isSelected) font_22 else font_18,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (isToggle) Switch(
                        checked = isDarkMode,
                        onCheckedChange = onSwitch,
                        colors = SwitchDefaults.colors().copy(
                            uncheckedBorderColor = primaryColor,
                            checkedThumbColor = Color.Red,
                            checkedTrackColor = primaryColor,
                            uncheckedTrackColor = CustomComposeTheme.appCustomColors.bgMenu,
                            uncheckedThumbColor = primaryColor
                        )
                    )
                }
            },
            icon = { JPIcon(icon = icon, size = size_32) },
            selected = isSelected,
            onClick = {
                if (!isToggle) onClick(route)
            },
            colors = NavigationDrawerItemDefaults.colors(
                selectedContainerColor = primaryColor,
                unselectedContainerColor = CustomComposeTheme.appCustomColors.bgMenu,
                selectedIconColor = Color.White,
                unselectedTextColor = primaryColor,
                unselectedIconColor = primaryColor,
                selectedTextColor = Color.White
            ),
            modifier = Modifier.height(size_60)
        )
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun MenuItemPreview() {
    MenuItem(screen = Screens.Home)
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun MenuItemSelectedPreview() {
    MenuItem(screen = Screens.DarkMode)
}

@Composable
fun MenuHeader() {
    JPColumn(
        Modifier
            .fillMaxWidth()
            .height(size_100)
            .background(primaryColor),
        isCenterVer = true,
        isCenterHoz = true
    ) {
        JPText(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun MenuHeaderPreview() {
    MenuHeader()
}

@Composable
fun MenuContent(
    isDarkMode: Boolean = false,
    navBackStackEntry: NavBackStackEntry?,
    onClickItem: (route: EScreenName) -> Unit,
    onSwitch: (isChecked: Boolean) -> Unit,
    onLogout: () -> Unit = {},
) {
    ModalDrawerSheet(
        Modifier.width((getDeviceSize().width / 1.3).dp),
        drawerShape = RectangleShape,
        drawerContainerColor = CustomComposeTheme.appCustomColors.bgMenu
    ) {
        MenuHeader()
        JPColumn(Modifier.weight(1f)) {
            MENU_SCREENS.forEach { screen ->
                val isSelected =
                    navBackStackEntry.getCurrentRoute() == EScreenName.getScreenName(screen.route)
                MenuItem(
                    isDarkMode = isDarkMode,
                    screen = screen,
                    isSelected = isSelected,
                    onClick = onClickItem,
                    onSwitch = onSwitch
                )
            }
        }
        JPRow(
            Modifier
                .height(size_80)
                .fillMaxWidth()
                .background(primaryColor)
                .onClickNoEffect { onLogout() },
            isCenterHoz = true,
            isCenterVer = true
        ) {
            JPText(
                text = stringResource(id = R.string.logoutButton),
                style = MaterialTheme.typography.titleMedium.copy(fontSize = font_18),
                color = Color.White
            )
        }
    }
}