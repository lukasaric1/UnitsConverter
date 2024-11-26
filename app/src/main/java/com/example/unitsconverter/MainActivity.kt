package com.example.unitsconverter

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.unitsconverter.ui.theme.UnitsConverterTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        enableEdgeToEdge()
        setContent {
            UnitsConverterTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.MAIN) {
                    composable(Routes.MAIN) { MainScreen(navController) }
                    composable(Routes.LENGTH) { Screens.LengthScreen(navController) }
                    composable(Routes.TIME) { Screens.TimeScreen(navController) }
                    composable(Routes.MASS) { Screens.MassScreen(navController) }
                    composable(Routes.TEMPERATURE) { Screens.TemperatureScreen(navController) }
                    composable(Routes.VOLUME) { Screens.VolumeScreen(navController) }
                    composable(Routes.AREA) { Screens.AreaScreen(navController) }
                    composable(Routes.SPEED) { Screens.SpeedScreen(navController) }
                    composable(Routes.ENERGY) { Screens.EnergyScreen(navController) }
                    composable(Routes.POWER) { Screens.PowerScreen(navController) }
                    composable(Routes.PRESSURE) { Screens.PressureScreen(navController) }
                    composable(Routes.FREQUENCY) { Screens.FrequencyScreen(navController) }
                    composable(Routes.CURRENCY) { Screens.CurrencyScreen(navController) }
                }
            }
        }
    }
}





