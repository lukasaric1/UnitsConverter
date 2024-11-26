package com.example.unitsconverter


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class CardItem(
    val name: String,
    val icon: Painter,
    val color: Color,
)

@Composable
fun MainScreen(navController: NavController) {


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = colorResource(R.color.background_color),
        topBar = { TopAppBarComponent() }
    ) { innerPadding ->
        CardGrid(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComponent() {
    val myTopAppBarColor = colorResource(id = R.color.purple_900)

    CenterAlignedTopAppBar(
        title = {
            Text(
                "UNIT CONVERTER",
                style = MaterialTheme.typography.headlineMedium
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = myTopAppBarColor,
            titleContentColor = Color.White,
        )
    )
}

@Composable
fun CardGrid(navController: NavController, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val cardData = listOf(
        CardItem("LENGTH", painterResource(id = R.drawable.length), color = colorResource(id = R.color.length_complementary)),
        CardItem("TIME", painterResource(id = R.drawable.time), color = colorResource(id = R.color.time_complementary)),
        CardItem("MASS", painterResource(id = R.drawable.mass), color = colorResource(id = R.color.mass_complementary)),
        CardItem("TEMPERATURE", painterResource(id = R.drawable.temperature), color = colorResource(id = R.color.temp_complementary)),
        CardItem("VOLUME", painterResource(id = R.drawable.cube), color = colorResource(id = R.color.volume_complementary)),
        CardItem("AREA", painterResource(id = R.drawable.area), color = Color.White),
        CardItem("SPEED", painterResource(id = R.drawable.speed), color = Color.White),
        CardItem("ENERGY", painterResource(id = R.drawable.energy), color = colorResource(id = R.color.energy_complementary)),
        CardItem("POWER", painterResource(id = R.drawable.power), color = Color.Yellow),
        CardItem("PRESSURE", painterResource(id = R.drawable.pressure), color = colorResource(id = R.color.pressure_complementary)),
        CardItem("FREQUENCY", painterResource(id = R.drawable.frequency), color = colorResource(id = R.color.freq_complementary)),
        CardItem("CURRENCY", painterResource(id = R.drawable.currency), color = Color.Green)
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp, top = 70.dp, bottom = 2.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(cardData) { item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.wrapContentSize(Alignment.Center)
            ) {
                Card(
                    modifier = Modifier.size(width = 150.dp, height = 100.dp),
                    onClick = {
                        val route = when (item.name) {
                            "LENGTH" -> Routes.LENGTH
                            "TIME" -> Routes.TIME
                            "MASS" -> Routes.MASS
                            "TEMPERATURE" -> Routes.TEMPERATURE
                            "VOLUME" -> Routes.VOLUME
                            "AREA" -> Routes.AREA
                            "SPEED" -> Routes.SPEED
                            "ENERGY" -> Routes.ENERGY
                            "POWER" -> Routes.POWER
                            "PRESSURE" -> Routes.PRESSURE
                            "FREQUENCY" -> Routes.FREQUENCY
                            "CURRENCY" -> Routes.CURRENCY
                            else -> null
                        }
                        route?.let {
                            navController.navigate(it)
                        }
                    },
                    shape = RoundedCornerShape(15.dp),
                    colors = CardDefaults.cardColors(containerColor = item.color),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            painter = item.icon,
                            contentDescription = item.name,
                            modifier = Modifier.size(45.dp),
                            tint = Color.Unspecified
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}
