package com.fzanutto.dota2heroes.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.fzanutto.dota2heroes.R
import com.fzanutto.dota2heroes.model.Hero
import com.fzanutto.dota2heroes.viewmodel.MainViewModel
import com.skydoves.landscapist.glide.GlideImage

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

    Column {
        Text(text = "Hero Details Screen")
        HeroDetails(hero)
    }
}

@Composable
fun HeroDetails(hero: Hero) {
    Row(
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
            placeHolder = ImageBitmap.imageResource(R.drawable.dota_logo)
        )

        Spacer(Modifier.size(16.dp))

        Column(modifier = Modifier) {
            Text(hero.name, fontSize = 24.sp)
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
