package com.fzanutto.dota2heroes.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fzanutto.dota2heroes.R
import com.fzanutto.dota2heroes.model.AttackType
import com.fzanutto.dota2heroes.model.Hero
import com.fzanutto.dota2heroes.model.HeroAttribute
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
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(hero.name, color = Color.White)

                        Spacer(modifier = Modifier.size(12.dp))

                        GlideImage(
                            imageModel = hero.icon,
                            modifier = Modifier
                                .size(28.dp)
                        )
                    }
                },
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
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top
    ) {
        GlideImage(
            imageModel = hero.img,
            placeHolder = ImageBitmap.imageResource(R.drawable.dota_logo)
        )

        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            MoveStats(hero)

            AttackRangeStats(hero)

            AttributeStats(hero, HeroAttribute.Str, hero.primaryAttribute == HeroAttribute.Str)
            AttributeStats(hero, HeroAttribute.Agi, hero.primaryAttribute == HeroAttribute.Agi)
            AttributeStats(hero, HeroAttribute.Int, hero.primaryAttribute == HeroAttribute.Int)
        }
    }
}

@Composable
private fun MoveStats(hero: Hero) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.movespeed_icon),
            contentDescription = "Move speed",
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(hero.moveSpeed.toString(), fontSize = 24.sp)
    }
}

@Composable
fun AttackRangeStats(hero: Hero) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.height(40.dp)
    ) {
        val resource = when (hero.attackType) {
            AttackType.Melee -> {
                painterResource(id = R.drawable.melee_icon)
            }
            AttackType.Ranged -> {
                painterResource(id = R.drawable.ranged_icon)
            }
            AttackType.Unknown -> {
                painterResource(id = R.drawable.dota_logo)
            }
        }

        Image(painter = resource, contentDescription = "Ranged", modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.size(4.dp))
        Text("${hero.attackRange}", fontSize = 24.sp)
    }
}

@Composable
fun AttributeStats(hero: Hero, attribute: HeroAttribute, isPrimary: Boolean) {
    val text = when (attribute) {
        HeroAttribute.Agi -> "${hero.baseAgi} + ${hero.agiGain}"
        HeroAttribute.Int -> "${hero.baseInt} + ${hero.intGain}"
        HeroAttribute.Str -> "${hero.baseStr} + ${hero.strGain}"
        else -> ""
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = attribute.getIcon()),
            contentDescription = attribute.toString(LocalContext.current)
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(text, fontSize = 20.sp, fontWeight = if (isPrimary) FontWeight.Bold else null)
    }
}