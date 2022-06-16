package com.fzanutto.dota2heroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fzanutto.dota2heroes.navigation.NavScreens
import com.fzanutto.dota2heroes.repository.HeroesRepositoryImpl
import com.fzanutto.dota2heroes.ui.HeroDetailsScreen
import com.fzanutto.dota2heroes.ui.HeroListScreen
import com.fzanutto.dota2heroes.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel = viewModel<MainViewModel>(
                factory = MainViewModel.MainViewModelFactory(
                    HeroesRepositoryImpl
                )
            )

            LaunchedEffect(Unit, block = {
                viewModel.loadHeroList()
            })

            NavHost(navController, startDestination = NavScreens.HeroList.route) {
                composable(route = NavScreens.HeroList.route) {
                    HeroListScreen(viewModel, navController)
                }
                composable(route = NavScreens.HeroDetails.route) {
                    val heroId = it.arguments?.getString("heroId")
                    requireNotNull(heroId)
                    HeroDetailsScreen(heroId = heroId.toInt(), viewModel, navController)
                }
            }
        }
    }
}
