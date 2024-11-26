package com.example.unitsconverter

class AppNavigation {

    enum class Screen {
        LENGTH,
        TIME,
        MASS,
        TEMPERATURE,
        VOLUME,
        AREA,
        SPEED,
        ENERGY,
        POWER,
        PRESSURE,
        FREQUENCY,
        CURRENCY,
    }

    sealed class NavigationItem(val route: String) {
        data object LENGTH : NavigationItem(Screen.LENGTH.name)
        data object TIME : NavigationItem(Screen.TIME.name)
        data object MASS : NavigationItem(Screen.MASS.name)
        data object TEMPERATURE : NavigationItem(Screen.TEMPERATURE.name)
        data object VOLUME : NavigationItem(Screen.VOLUME.name)
        data object AREA : NavigationItem(Screen.AREA.name)
        data object SPEED : NavigationItem(Screen.SPEED.name)
        data object ENERGY : NavigationItem(Screen.ENERGY.name)
        data object POWER : NavigationItem(Screen.POWER.name)
        data object PRESSURE : NavigationItem(Screen.PRESSURE.name)
        data object FREQUENCY : NavigationItem(Screen.FREQUENCY.name)
        data object CURRENCY : NavigationItem(Screen.CURRENCY.name)
    }
}