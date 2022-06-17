package com.fzanutto.dota2heroes.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
    Column(Modifier.padding(horizontal = 32.dp, vertical = 8.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(hero.name, fontSize = 32.sp)

            GlideImage(
                imageModel = hero.icon,
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(Modifier.size(16.dp))

        Text("Base stats", fontSize = 24.sp)

        Spacer(Modifier.size(8.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                MoveStats(hero)
                AttackRangeStats(hero)
                AttackDamageStats(hero)
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                AttributeStats(hero, HeroAttribute.Str, hero.primaryAttribute == HeroAttribute.Str)
                AttributeStats(hero, HeroAttribute.Agi, hero.primaryAttribute == HeroAttribute.Agi)
                AttributeStats(hero, HeroAttribute.Int, hero.primaryAttribute == HeroAttribute.Int)
            }
        }
    }
}

@Composable
private fun AttackDamageStats(hero: Hero) {
    Row {
        Box(modifier = Modifier.size(24.dp)) {
            Image(
                painter = painterResource(id = R.drawable.icon_damage),
                contentDescription = "Move speed",
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.size(8.dp))
        Text("${hero.baseAttackMin} - ${hero.baseAttackMax}", fontSize = 20.sp)
    }
}

@Composable
private fun MoveStats(hero: Hero) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(24.dp)) {
            Image(
                painter = painterResource(id = R.drawable.icon_movement_speed),
                contentDescription = "Move speed",
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        Text(hero.moveSpeed.toString(), fontSize = 20.sp)
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
                painterResource(id = 0)
            }
        }

        Box(modifier = Modifier.size(24.dp)) {
            Image(
                painter = painterResource(id = R.drawable.icon_attack_range),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.size(8.dp))
        Text("${hero.attackRange}", fontSize = 20.sp)
        Spacer(modifier = Modifier.size(4.dp))
        Text("( ", fontSize = 20.sp)
        Image(
            painter = resource,
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )
        Text(" )", fontSize = 20.sp)
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
        Box(modifier = Modifier.size(24.dp)) {
            Image(
                painter = painterResource(id = attribute.getIcon()),
                contentDescription = attribute.toString(LocalContext.current),
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        Text(text, fontSize = 20.sp, fontWeight = if (isPrimary) FontWeight.Black else null)
    }
}
