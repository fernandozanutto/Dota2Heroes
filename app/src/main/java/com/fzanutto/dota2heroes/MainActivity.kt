package com.fzanutto.dota2heroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarScrollState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fzanutto.dota2heroes.repository.HeroesRepositoryImpl
import com.fzanutto.dota2heroes.ui.HeroList
import com.fzanutto.dota2heroes.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = viewModel<MainViewModel>(factory = MainViewModel.MainViewModelFactory(HeroesRepositoryImpl))

            LaunchedEffect(Unit, block = {
                viewModel.loadHeroList()
            })

            val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarScrollState())
            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    SmallTopAppBar(
                        title = { Text("Dota 2 Hero List", color = Color.White) },
                        scrollBehavior = scrollBehavior,
                        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFFF74942)),
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
                            .padding(horizontal = 12.dp)
                    ) {
                        HeroList(viewModel.heroList.toList())
                    }
                }
            )
        }
    }
}
