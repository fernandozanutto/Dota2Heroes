package com.fzanutto.dota2heroes.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fzanutto.dota2heroes.R
import com.fzanutto.dota2heroes.model.Hero
import com.fzanutto.dota2heroes.ui.components.BaseTopBar
import com.fzanutto.dota2heroes.viewmodel.MainViewModel
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroDetailsScreen(
    heroId: Int,
    viewModel: MainViewModel,
    navController: NavController
) {
    val hero = viewModel.heroList.find { it.id == heroId } ?: run {
        navController.popBackStack()
        return
    }

    Scaffold(
        modifier = Modifier,
        topBar = {
            BaseTopBar(
                titleText = hero.name,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Go back",
                            tint = Color.White,
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                HeroDetails(hero)
            }
        }
    )
}

@Composable
fun HeroDetails(hero: Hero) {
    Column(
        modifier = Modifier.fillMaxHeight().verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()

        ) {
            GlideImage(
                imageModel = hero.img,
                alignment = Alignment.TopCenter,
                modifier = Modifier,
                placeHolder = ImageBitmap.imageResource(R.drawable.dota_logo)
            )
        }

        Column(modifier = Modifier) {
            repeat(5) {
                Text(hero.name, fontSize = 24.sp)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = hero.primaryAttribute.getIcon()),
                    contentDescription = "${hero.primaryAttribute.toString(
                        LocalContext.current
                    )} icon"
                )

                Spacer(Modifier.size(4.dp))

                Text(hero.primaryAttribute.toString(LocalContext.current), fontSize = 16.sp)
            }
        }
    }
}
