package org.d3if0024.mobproassement.navigation

sealed class Screen (val route: String) {
    data object Home: Screen("mainScreen")
}