package com.fzanutto.dota2heroes.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fzanutto.dota2heroes.R
import com.fzanutto.dota2heroes.model.Hero
import com.fzanutto.dota2heroes.navigation.NavScreens
import com.fzanutto.dota2heroes.ui.components.BaseTopBar
import com.fzanutto.dota2heroes.viewmodel.MainViewModel
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroListScreen(
    viewModel: MainViewModel,
    navController: NavController
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarScrollState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            BaseTopBar(
                title = { Text("Dota 2 Hero List") },
                scrollBehavior = scrollBehavior,
                actions = {
                    Button(
                        onClick = {
                            viewModel.reverseList()
                        }
                    ) {
                        Text("ID")
                        Spacer(modifier = Modifier.size(4.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.icon_filter_list),
                            contentDescription = "Sort List",
                            modifier = Modifier
                                .rotate(if (viewModel.sortOrder.value) 180.0f else 0f)
                                .width(16.dp)
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                HeroList(viewModel.heroList.toList()) {
                    navController.navigate(NavScreens.HeroDetails.createRoute(it.id))
                }
            }
        }
    )
}

@Composable
fun HeroList(heroList: List<Hero>, onClick: (Hero) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(heroList) { item ->
            HeroItem(item, onClick)
        }
    }
}

@Composable
fun HeroItem(hero: Hero, onClick: (Hero) -> Unit) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick(hero)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            imageModel = hero.img,
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.FillHeight,
            placeHolder = ImageBitmap.imageResource(R.drawable.dota_logo)
        )

        Spacer(Modifier.size(16.dp))

        Column(modifier = Modifier) {
            Text(hero.name, fontSize = 24.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = hero.primaryAttribute.getIcon()), contentDescription = "${hero.primaryAttribute.toString(LocalContext.current)} icon")

                Spacer(Modifier.size(4.dp))

                Text(hero.primaryAttribute.toString(LocalContext.current), fontSize = 16.sp)
            }
        }
    }
}
