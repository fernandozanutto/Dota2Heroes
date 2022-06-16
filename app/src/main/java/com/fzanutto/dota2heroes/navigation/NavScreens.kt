package com.fzanutto.dota2heroes.navigation

sealed class NavScreens(val route: String) {
    object HeroList : NavScreens("herolist")
    object HeroDetails : NavScreens("hero/{heroId}/details") {
        fun createRoute(id: Int) = "hero/$id/details"
    }
}
