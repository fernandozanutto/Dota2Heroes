package com.fzanutto.dota2heroes.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.ImageBitmap
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
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroDetailsScreen(
    hero: Hero?,
    navController: NavController
) {
    hero ?: run {
        navController.popBackStack()
        return
    }

    Scaffold(
        topBar = {
            BaseTopBar(
                title = {
                    Text(hero.name, fontWeight = FontWeight.Bold)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Top
            ) {
                HeroImage(hero)
                HeroDetails(hero)
            }
        }
    )
}

@Composable
private fun HeroImage(hero: Hero) {
    GlideImage(
        imageModel = hero.img,
        placeHolder = ImageBitmap.imageResource(R.drawable.dota_logo)
    )
}

@Composable
fun HeroDetails(hero: Hero) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(hero.name, fontSize = 24.sp)

        Spacer(modifier = Modifier.size(12.dp))

        GlideImage(
            imageModel = hero.icon,
            modifier = Modifier
                .size(28.dp)
        )
    }

    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(top = 32.dp).fillMaxWidth()
        ) {
            Column {
                MoveStats(hero)

                AttackRangeStats(hero)

                Row {
                    Text("${hero.baseAttackMin} - ${hero.baseAttackMax}", fontSize = 24.sp)
                }
            }

            Column {
                AttributeStats(hero, HeroAttribute.Str, hero.primaryAttribute == HeroAttribute.Str)
                AttributeStats(hero, HeroAttribute.Agi, hero.primaryAttribute == HeroAttribute.Agi)
                AttributeStats(hero, HeroAttribute.Int, hero.primaryAttribute == HeroAttribute.Int)
            }
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
        verticalAlignment = Alignment.CenterVertically
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

        Image(
            painter = resource,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
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
            contentDescription = attribute.toString(LocalContext.current),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(text, fontSize = 24.sp, fontWeight = if (isPrimary) FontWeight.Bold else null)
    }
}
