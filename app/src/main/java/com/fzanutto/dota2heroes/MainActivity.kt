package com.fzanutto.dota2heroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fzanutto.dota2heroes.model.Hero
import com.fzanutto.dota2heroes.repository.HeroesRepositoryImpl
import com.fzanutto.dota2heroes.ui.theme.Dota2HeroesTheme
import com.fzanutto.dota2heroes.viewmodel.MainViewModel
import com.skydoves.landscapist.glide.GlideImage
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Dota2HeroesTheme {
                val viewModel = viewModel<MainViewModel>(factory = MainViewModel.MainViewModelFactory(HeroesRepositoryImpl))

                LaunchedEffect(Unit, block = {
                    viewModel.loadHeroList()
                })

                val toolbarHeight = 48.dp
                val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.roundToPx().toFloat() }
                val toolbarOffsetHeightPx = remember { mutableStateOf(0f) }

                val nestedScrollConnection = remember {
                    object : NestedScrollConnection {
                        override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {

                            val delta = available.y
                            val newOffset = toolbarOffsetHeightPx.value + delta
                            toolbarOffsetHeightPx.value = newOffset.coerceIn(-toolbarHeightPx, 0f)
                            return Offset.Zero
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .nestedScroll(nestedScrollConnection)
                ) {
                    HeroList(viewModel.heroList.toList())

                    TopAppBar (
                        title = { Text("toolbar offset is ${toolbarOffsetHeightPx.value}") },
                        modifier = Modifier.height(toolbarHeight)
                            .offset { IntOffset(x = 0, y = toolbarOffsetHeightPx.value.roundToInt()) }
                    )


                }
            }
        }
    }
}

@Composable
fun HeroList(heroList: List<Hero>) {
    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(heroList){ item ->
            HeroItem(item)
        }
    }
}

@Composable
fun HeroItem(hero: Hero) {
    Row (
        modifier = Modifier.wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            imageModel = hero.img,
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.FillHeight,
        )

        Spacer(Modifier.size(16.dp))

        Column(modifier = Modifier) {
            Text(hero.name)
            Text(hero.primaryAttribute.toString(LocalContext.current))
        }
    }
}