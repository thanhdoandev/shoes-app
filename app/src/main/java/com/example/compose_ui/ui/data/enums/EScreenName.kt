package com.example.compose_ui.ui.data.enums

enum class EScreenName(val screenName: String) {
    APP_NAV("APP_NAV"),
    FEATURE_ROUTE("feature/route"),

    INTRO_ROUTE("introRoute"),
    INTRO("intro"),

    AUTH_ROUTE("Auth"),
    LOGIN("Login"),
    REGISTER("Register"),


    HOME_ROUTE("home/"),
    HOME("home"),
    SEARCH_SCREEN("home/search"),
    SHOES_DETAIL("home/shoes/{shoesId}"),
    SHOES("home/shoes/"),

    FAVORITES_ROUTE("Favorite_Route"),
    FAVORITES("Favorites"),

    ORDERS_ROUTE("Order_Route"),
    ORDERS("Orders"),

    NOTIFICATIONS_ROUTE("Notification_Route"),
    NOTIFICATIONS("Notifications"),

    PROFILE_ROUTE("Profile_route"),
    PROFILE("Profile"),

    SETTING_ROUTER("menu/settings"),
    SETTINGS("menu/settings/screen"),
    LANGUAGE("menu/settings/language"),
    PRIVACY_SECURITY("menu/settings/privacy"),
    HELP("menu/settings/help"),
    SUPPORT("menu/settings/support"),
    ABOUT("menu/settings/menu"),

    HISTORY_ROUTE("menu/histories"),
    HISTORY("menu/histories/screen"),

    DELIVERY_ROUTER("menu/delivery"),
    DELIVERY("menu/delivery/screen"),
    MAP_VIEW("menu/delivery/maps")
    ;

    companion object {
        fun getScreenName(text: EScreenName): String {
            return entries.firstOrNull { it == text }?.screenName ?: HOME.name
        }
    }
}