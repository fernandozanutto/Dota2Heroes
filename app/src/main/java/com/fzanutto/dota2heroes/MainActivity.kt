package com.fzanutto.dota2heroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fzanutto.dota2heroes.model.Hero
import com.fzanutto.dota2heroes.repository.MockApi
import com.fzanutto.dota2heroes.ui.theme.Dota2HeroesTheme
import com.fzanutto.dota2heroes.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Dota2HeroesTheme {
                val viewModel = viewModel<MainViewModel>(factory = MainViewModel.MainViewModelFactory(MockApi()))
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 32.dp)) {
                        Greeting("Android")
                        HeroList(viewModel = viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun HeroList(viewModel: MainViewModel) {

    LaunchedEffect(Unit, block = {
        viewModel.loadHeroList()
    })
    
    Scaffold (content = {
        LazyColumn (modifier = Modifier.fillMaxHeight()) {
            items(viewModel.heroList.toList()){ item ->
                HeroItem(item)
            }
        }
    })
}

@Composable
fun HeroItem(hero: Hero) {
    Row {
        Text(hero.name)
    }

}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Dota2HeroesTheme {
        Column {
            Greeting("Android")
            HeroItem(Hero(1, "teste", "aaaaa", listOf(), 2, "https://api.opendota.com/apps/dota2/images/dota_react/heroes/luna.png?"))
        }
    }
}