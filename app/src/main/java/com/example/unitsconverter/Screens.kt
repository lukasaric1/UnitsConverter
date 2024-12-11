package com.example.unitsconverter

import android.accounts.NetworkErrorException
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalSoftwareKeyboardController



object Screens {


    @Composable
    fun LengthScreen(navController: NavController) {

        TopAppBar(
            title = "LENGTH",
            onBackClick = {navController.popBackStack()}
        )

        var fromUnit by remember { mutableStateOf("Meter") }
        var toUnit by remember { mutableStateOf("Meter") }
        var fromValue by remember { mutableStateOf("") }
        var toValue by remember { mutableStateOf("") }

        // Tracks which field is currently being edited
        var isEditingFromField by remember { mutableStateOf(true) }

        // Conversion logic
        val conversionRates = mapOf(
            "Meter" to 1.0,
            "Centimeter" to 100.0,
            "Kilometer" to 0.001,
            "Millimeter" to 1000.0,
            "Decimeter" to 0.1,
            "Micrometer" to 1000000.0,
            "Nanometer" to 1000000000.0,
            "Mile" to 0.000621371,
            "Yard" to 1.09361,
            "Inch" to 39.3701,
            "Exameter" to 1e-18,
            "Petameter" to 1e-15,
            "Terameter" to 1e-12,
            "Gigameter" to 1e-9,
            "Megameter" to 1e-6,
            "Hectometer" to 0.01,
            "Dekameter" to 0.1,
            "Picometer" to 1e15,
            "Femtometer" to 1e18,
            "Attometer" to 1e21,
        )

        fun convertLength(value: String, fromUnit: String, toUnit: String): String {
            val input = value.toDoubleOrNull() ?: return ""
            val fromRate = conversionRates[fromUnit] ?: 1.0
            val toRate = conversionRates[toUnit] ?: 1.0
            return ((input / fromRate) * toRate).toString()
        }


        LaunchedEffect(fromValue, fromUnit, toUnit) {
            if (isEditingFromField) {
                toValue = convertLength(fromValue, fromUnit, toUnit)
            }
        }

        LaunchedEffect(toValue, fromUnit, toUnit) {
            if (!isEditingFromField) {
                fromValue = convertLength(toValue, toUnit, fromUnit)
            }
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                "From Length",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                DropdownOutlinedTextField(
                    options = listOf("Meter",
                        "Centimeter", "Kilometer", "Millimeter", "Decimeter", "Micrometer", "Nanometer",
                        "Mile", "Yard", "Inch", "Exameter", "Petameter", "Terameter", "Gigameter",
                        "Megameter", "Hectometer", "Dekameter", "Picometer", "Femtometer", "Attometer" ),
                    label = "Select Unit",
                    selectedOption = fromUnit,
                    onOptionSelected = { fromUnit = it }
                )
                OutlinedTextField(
                    value = fromValue,
                    onValueChange = {
                        fromValue = it.filter { char -> char.isDigit() || char == '.' }
                        isEditingFromField = true
                    },
                    singleLine = true,
                    label = { Text("Enter value") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier
                        .width(170.dp)
                        .height(70.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "To Length",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                DropdownOutlinedTextField(
                    options = listOf("Meter",
                        "Centimeter", "Kilometer", "Millimeter", "Decimeter", "Micrometer", "Nanometer",
                        "Mile", "Yard", "Inch", "Exameter", "Petameter", "Terameter", "Gigameter",
                        "Megameter", "Hectometer", "Dekameter", "Picometer", "Femtometer", "Attometer" ),
                    label = "Select Unit",
                    selectedOption = toUnit,
                    onOptionSelected = { toUnit = it }
                )
                OutlinedTextField(
                    value = toValue,
                    onValueChange = {
                        toValue = it.filter { char -> char.isDigit() || char == '.' }
                        isEditingFromField = false
                    },
                    singleLine = true,
                    label = { Text("Enter value") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier
                        .width(170.dp)
                        .height(70.dp)
                )
            }
        }
    }

    @Composable
    fun TimeScreen(navController: NavController) {

        TopAppBar(
            title = "TIME",
            onBackClick = {navController.popBackStack()}
        )

        var fromUnit by remember { mutableStateOf("Hour") }
        var toUnit by remember { mutableStateOf("Hour") }
        var fromValue by remember { mutableStateOf("") }
        var toValue by remember { mutableStateOf("") }

        // Tracks which field is currently being edited
        var isEditingFromField by remember { mutableStateOf(true) }

        // Conversion logic
        val conversionRates = mapOf(
            "Hour" to 1.0,
            "Minute" to 60.0,
            "Seconds" to 3600.0,
            "Milliseconds" to 3600000.0,

        )

        fun convertTime(value: String, fromUnit: String, toUnit: String): String {
            val input = value.toDoubleOrNull() ?: return ""
            val fromRate = conversionRates[fromUnit] ?: 1.0
            val toRate = conversionRates[toUnit] ?: 1.0
            return ((input / fromRate) * toRate).toString()
        }


        LaunchedEffect(fromValue, fromUnit, toUnit) {
            if (isEditingFromField) {
                toValue = convertTime(fromValue, fromUnit, toUnit)
            }
        }

        LaunchedEffect(toValue, fromUnit, toUnit) {
            if (!isEditingFromField) {
                fromValue = convertTime(toValue, toUnit, fromUnit)
            }
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                "From Time",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                DropdownOutlinedTextField(
                    options = listOf("Hour", "Minute", "Seconds", "Milliseconds"),
                    label = "Select Unit",
                    selectedOption = fromUnit,
                    onOptionSelected = { fromUnit = it }
                )
                OutlinedTextField(
                    value = fromValue,
                    onValueChange = {
                        fromValue = it.filter { char -> char.isDigit() || char == '.' }
                        isEditingFromField = true
                    },
                    singleLine = true,
                    label = { Text("Enter value") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier
                        .width(170.dp)
                        .height(70.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "To Time",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                DropdownOutlinedTextField(
                    options = listOf("Hour", "Minute", "Seconds", "Milliseconds"),
                    label = "Select Unit",
                    selectedOption = toUnit,
                    onOptionSelected = { toUnit = it }
                )
                OutlinedTextField(
                    value = toValue,
                    onValueChange = {
                        toValue = it.filter { char -> char.isDigit() || char == '.' }
                        isEditingFromField = false
                    },
                    singleLine = true,
                    label = { Text("Enter value") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier
                        .width(170.dp)
                        .height(70.dp)
                )
            }
        }
    }

    @Composable
    fun MassScreen(navController: NavController) {

        TopAppBar(
            title = "MASS",
            onBackClick = {navController.popBackStack()}
        )

        var fromUnit by remember { mutableStateOf("Kilogram") }
        var toUnit by remember { mutableStateOf("Kilogram") }
        var fromValue by remember { mutableStateOf("") }
        var toValue by remember { mutableStateOf("") }

        // Tracks which field is currently being edited
        var isEditingFromField by remember { mutableStateOf(true) }

        // Conversion logic
        val conversionRates = mapOf(
            "Kilogram" to 1.0,        // Base unit
            "Gram" to 1000.0,         // 1 kg = 1000 g
            "Milligram" to 1_000_000.0, // 1 kg = 1,000,000 mg
            "Ton" to 0.001,           // 1 kg = 0.001 metric tons
            "Pound" to 2.20462,       // 1 kg ≈ 2.20462 pounds
            "Ounce" to 35.274,        // 1 kg ≈ 35.274 ounces
            "Carat" to 5000.0,        // 1 kg = 5000 carats
            "Exagram" to 1e-18,       // 1 kg = 10^-18 exagrams
            "Petagram" to 1e-15,      // 1 kg = 10^-15 petagrams
            "Teragram" to 1e-12,      // 1 kg = 10^-12 teragrams
            "Gigagram" to 1e-9,       // 1 kg = 10^-9 gigagrams
            "Megagram" to 0.001,      // 1 kg = 10^-3 megagrams (same as a metric ton)
            "Hectogram" to 10.0,      // 1 kg = 10 hectograms
            "Dekagram" to 100.0,      // 1 kg = 100 dekagrams
            "Decigram" to 10_000.0,   // 1 kg = 10,000 decigrams
            "Centigram" to 100_000.0, // 1 kg = 100,000 centigrams
            "Microgram" to 1e9,       // 1 kg = 1,000,000,000 micrograms
            "Nanogram" to 1e12,       // 1 kg = 1,000,000,000,000 nanograms
            "Picogram" to 1e15,       // 1 kg = 1,000,000,000,000,000 picograms
            "Femtogram" to 1e18       // 1 kg = 1,000,000,000,000,000,000 femtograms
        )

        fun convertMass(value: String, fromUnit: String, toUnit: String): String {
            val input = value.toDoubleOrNull() ?: return ""
            val fromRate = conversionRates[fromUnit] ?: 1.0
            val toRate = conversionRates[toUnit] ?: 1.0
            return ((input / fromRate) * toRate).toString()
        }


        LaunchedEffect(fromValue, fromUnit, toUnit) {
            if (isEditingFromField) {
                toValue = convertMass(fromValue, fromUnit, toUnit)
            }
        }

        LaunchedEffect(toValue, fromUnit, toUnit) {
            if (!isEditingFromField) {
                fromValue = convertMass(toValue, toUnit, fromUnit)
            }
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                "From Mass",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                DropdownOutlinedTextField(
                    options = listOf("Kilogram", "Gram", "Milligram", "Ton", "Pound", "Ounce", "Carat","Exagram",
                        "Petagram", "Teragram", "Gigagram", "Megagram",
                        "Hectogram", "Dekagram", "Decigram", "Centigram",
                        "Microgram","Nanogram", "Picogram", "Femtogram" ),
                    label = "Select Unit",
                    selectedOption = fromUnit,
                    onOptionSelected = { fromUnit = it }
                )
                OutlinedTextField(
                    value = fromValue,
                    onValueChange = {
                        fromValue = it.filter { char -> char.isDigit() || char == '.' }
                        isEditingFromField = true
                    },
                    singleLine = true,
                    label = { Text("Enter value") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier
                        .width(170.dp)
                        .height(70.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "To Mass",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                DropdownOutlinedTextField(
                    options = listOf("Kilogram", "Gram", "Milligram", "Ton", "Pound", "Ounce", "Carat","Exagram",
                        "Petagram", "Teragram", "Gigagram", "Megagram",
                        "Hectogram", "Dekagram", "Decigram", "Centigram",
                        "Microgram","Nanogram", "Picogram", "Femtogram" ),
                    label = "Select Unit",
                    selectedOption = toUnit,
                    onOptionSelected = { toUnit = it }
                )
                OutlinedTextField(
                    value = toValue,
                    onValueChange = {
                        toValue = it.filter { char -> char.isDigit() || char == '.' }
                        isEditingFromField = false
                    },
                    singleLine = true,
                    label = { Text("Enter value") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier
                        .width(170.dp)
                        .height(70.dp)
                )
            }
        }
    }

    @Composable
    fun TemperatureScreen(navController: NavController) {

        TopAppBar(
            title = "TEMPERATURE",
            onBackClick = {navController.popBackStack()}
        )

        var fromUnit by remember { mutableStateOf("Celsius") }
        var toUnit by remember { mutableStateOf("Celsius") }
        var fromValue by remember { mutableStateOf("") }
        var toValue by remember { mutableStateOf("") }

        // Tracks which field is currently being edited
        var isEditingFromField by remember { mutableStateOf(true) }

        // Conversion logic
        val conversionRates = mapOf(
            "Celsius" to 1.0,
            "Fahrenheit" to 33.8,
            "Kelvin" to 274.15,
            "Rankine" to 493.47,
            "Reaumur" to 0.8

            )

        fun convertTemperature(value: String, fromUnit: String, toUnit: String): String {
            val input = value.toDoubleOrNull() ?: return ""
            val fromRate = conversionRates[fromUnit] ?: 1.0
            val toRate = conversionRates[toUnit] ?: 1.0
            return ((input / fromRate) * toRate).toString()
        }


        LaunchedEffect(fromValue, fromUnit, toUnit) {
            if (isEditingFromField) {
                toValue = convertTemperature(fromValue, fromUnit, toUnit)
            }
        }

        LaunchedEffect(toValue, fromUnit, toUnit) {
            if (!isEditingFromField) {
                fromValue = convertTemperature(toValue, toUnit, fromUnit)
            }
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                "From Temperature",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                DropdownOutlinedTextField(
                    options = listOf("Celsius", "Fahrenheit","Kelvin","Rankine","Reaumur"),
                    label = "Select Unit",
                    selectedOption = fromUnit,
                    onOptionSelected = { fromUnit = it }
                )
                OutlinedTextField(
                    value = fromValue,
                    onValueChange = {
                        fromValue = it.filter { char -> char.isDigit() || char == '.' }
                        isEditingFromField = true
                    },
                    singleLine = true,
                    label = { Text("Enter value") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier
                        .width(170.dp)
                        .height(70.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "To Temperature",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                DropdownOutlinedTextField(
                    options = listOf("Celsius", "Fahrenheit","Kelvin","Rankine","Reaumur"),
                    label = "Select Unit",
                    selectedOption = toUnit,
                    onOptionSelected = { toUnit = it }
                )
                OutlinedTextField(
                    value = toValue,
                    onValueChange = {
                        toValue = it.filter { char -> char.isDigit() || char == '.' }
                        isEditingFromField = false
                    },
                    singleLine = true,
                    label = { Text("Enter value") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier
                        .width(170.dp)
                        .height(70.dp)
                )
            }
        }
    }

    @Composable
    fun VolumeScreen(navController: NavController) {

        TopAppBar(
            title = "VOLUME",
            onBackClick = {navController.popBackStack()}
        )

        var fromUnit by remember { mutableStateOf("m3") }
        var toUnit by remember { mutableStateOf("m3") }
        var fromValue by remember { mutableStateOf("") }
        var toValue by remember { mutableStateOf("") }

        // Tracks which field is currently being edited
        var isEditingFromField by remember { mutableStateOf(true) }

        // Conversion logic
        val conversionRates = mapOf(
            "m3" to 1.0,
            "Liter[dm3]" to 1000.0,
            "ml [cm3]" to 1_000_000.0,
            "km3" to 1e-9,
            "Gallon" to 0.000264,
            "Pint" to 0.0021133764,
            "Cup" to 0.0042267528,
            "Tablespoon" to 0.0676280454,
            "Teaspoon" to 0.2028841362

        )

        fun convertVolume(value: String, fromUnit: String, toUnit: String): String {
            val input = value.toDoubleOrNull() ?: return ""
            val fromRate = conversionRates[fromUnit] ?: 1.0
            val toRate = conversionRates[toUnit] ?: 1.0
            return ((input / fromRate) * toRate).toString()
        }


        LaunchedEffect(fromValue, fromUnit, toUnit) {
            if (isEditingFromField) {
                toValue = convertVolume(fromValue, fromUnit, toUnit)
            }
        }

        LaunchedEffect(toValue, fromUnit, toUnit) {
            if (!isEditingFromField) {
                fromValue = convertVolume(toValue, toUnit, fromUnit)
            }
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                "From Volume",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                DropdownOutlinedTextField(
                    options = listOf("m3",
                        "Liter[dm3]", "Ml [cm3]", "km3", "Gallon",
                        "Pint", "Cup","Tablespoon", "Teaspoon"),
                    label = "Select Unit",
                    selectedOption = fromUnit,
                    onOptionSelected = { fromUnit = it }
                )
                OutlinedTextField(
                    value = fromValue,
                    onValueChange = {
                        fromValue = it.filter { char -> char.isDigit() || char == '.' }
                        isEditingFromField = true
                    },
                    singleLine = true,
                    label = { Text("Enter value") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier
                        .width(170.dp)
                        .height(70.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "To Volume",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                DropdownOutlinedTextField(
                    options = listOf("Liter[dm3]", "Ml[cm3]", "km3", "Gallon",
                    "Pint", "Cup","Tablespoon", "Teaspoon"),
                    label = "Select Unit",
                    selectedOption = toUnit,
                    onOptionSelected = { toUnit = it }
                )
                OutlinedTextField(
                    value = toValue,
                    onValueChange = {
                        toValue = it.filter { char -> char.isDigit() || char == '.' }
                        isEditingFromField = false
                    },
                    singleLine = true,
                    label = { Text("Enter value") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier
                        .width(170.dp)
                        .height(70.dp)
                )
            }
        }
    }

    @Composable
    fun AreaScreen(navController: NavController) {

        TopAppBar(
            title = "AREA",
            onBackClick = {navController.popBackStack()}
        )

        var fromUnit by remember { mutableStateOf("m2") }
        var toUnit by remember { mutableStateOf("m2") }
        var fromValue by remember { mutableStateOf("") }
        var toValue by remember { mutableStateOf("") }

        // Tracks which field is currently being edited
        var isEditingFromField by remember { mutableStateOf(true) }

        // Conversion logic
        val conversionRates = mapOf(
            "Square meter [m2]" to 1.0,
            "Square kilometer [km2]" to 1e-6,
            "Square centimeter [cm2]" to 1_000_000.0,
            "Square millimeter [mm2]" to 1_000_000_000.0,
            "Hectare [ha]" to 0.0001,
            "Acre [ac]" to 0.0002471054,
            "Square mile [mi2]" to 3.861021585E-7,
            "Square yard [yd2]" to 1.1959900463,
            "Square foot [ft2]" to 10.763910417,
            "Square inch [in2]" to 1550.0031,
            "Square hectometer [hm2]" to 0.0001,
            "Square dekameter [dam2]" to 0.01,
            "Square decimeter [dm2]" to 100.0,
            "Square nanometer [nm2]" to 1e-12,

        )

        fun convertArea(value: String, fromUnit: String, toUnit: String): String {
            val input = value.toDoubleOrNull() ?: return ""
            val fromRate = conversionRates[fromUnit] ?: 1.0
            val toRate = conversionRates[toUnit] ?: 1.0
            return ((input / fromRate) * toRate).toString()
        }


        LaunchedEffect(fromValue, fromUnit, toUnit) {
            if (isEditingFromField) {
                toValue = convertArea(fromValue, fromUnit, toUnit)
            }
        }

        LaunchedEffect(toValue, fromUnit, toUnit) {
            if (!isEditingFromField) {
                fromValue = convertArea(toValue, toUnit, fromUnit)
            }
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                "From Area",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                DropdownOutlinedTextField(
                    options = listOf("m2",
                        "km2", "cm2", "mm2",
                        "ha", "ac", "mi2",
                        "yd2","ft2", "in2",
                        "hm2", "dam2",
                        "dm2", "nm2"),
                    label = "Select Unit",
                    selectedOption = fromUnit,
                    onOptionSelected = { fromUnit = it }
                )
                OutlinedTextField(
                    value = fromValue,
                    onValueChange = {
                        fromValue = it.filter { char -> char.isDigit() || char == '.' }
                        isEditingFromField = true
                    },
                    singleLine = true,
                    label = { Text("Enter value") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier
                        .width(170.dp)
                        .height(70.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "To Area",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                DropdownOutlinedTextField(
                    options = listOf("m2",
                        "km2", "cm2", "mm2",
                        "ha", "ac", "mi2",
                        "yd2","ft2", "in2",
                        "hm2", "dam2",
                        "dm2", "nm2"),
                    label = "Select Unit",
                    selectedOption = toUnit,
                    onOptionSelected = { toUnit = it }
                )
                OutlinedTextField(
                    value = toValue,
                    onValueChange = {
                        toValue = it.filter { char -> char.isDigit() || char == '.' }
                        isEditingFromField = false
                    },
                    singleLine = true,
                    label = { Text("Enter value") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier
                        .width(170.dp)
                        .height(70.dp)
                )
            }
        }
    }

    @Composable
    fun SpeedScreen(navController: NavController) {

        TopAppBar(
            title = "SPEED",
            onBackClick = {navController.popBackStack()}
        )

        var fromUnit by remember { mutableStateOf("m/s") }
        var toUnit by remember { mutableStateOf("m/s") }
        var fromValue by remember { mutableStateOf("") }
        var toValue by remember { mutableStateOf("") }


        var isEditingFromField by remember { mutableStateOf(true) }

        // Conversion logic
        val conversionRates = mapOf(
            "m/s" to 1.0,           // Base unit
            "km/h" to 1 / 3.6,      // 1 km/h = 1/3.6 m/s
            "mph" to 0.44704,       // 1 mph = 0.44704 m/s
            "m/min" to 1 / 60.0,    // 1 m/min = 1/60 m/s
            "km/min" to 1000 / 60.0, // 1 km/min = 1000/60 m/s
            "km/s" to 1000.0,       // 1 km/s = 1000 m/s
            "cm/h" to 1 / 360000.0, // 1 cm/h = 1/360000 m/s
            "cm/min" to 1 / 6000.0, // 1 cm/min = 1/6000 m/s
            "cm/s" to 0.01,         // 1 cm/s = 0.01 m/s
            "mm/h" to 1 / 3600000.0, // 1 mm/h = 1/3600000 m/s
            "mm/min" to 1 / 60000.0, // 1 mm/min = 1/60000 m/s
            "mm/s" to 0.001         // 1 mm/s = 0.001 m/s
            )

        fun convertSpeed(value: String, fromUnit: String, toUnit: String): String {
            val input = value.toDoubleOrNull() ?: return ""
            val fromRate = conversionRates[fromUnit] ?: 1.0
            val toRate = conversionRates[toUnit] ?: 1.0
            return ((input / fromRate) * toRate).toString()
        }


        LaunchedEffect(fromValue, fromUnit, toUnit) {
            if (isEditingFromField) {
                toValue = convertSpeed(fromValue, fromUnit, toUnit)
            }
        }

        LaunchedEffect(toValue, fromUnit, toUnit) {
            if (!isEditingFromField) {
                fromValue = convertSpeed(toValue, toUnit, fromUnit)
            }
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                "From Speed",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                DropdownOutlinedTextField(
                    options = listOf( "meter/second [m/s]",
                        "km/h", "mile/hour [mph]", "meter/hour [mps]",
                        "meter/minute [m/min]", "kilometer/minute [km/min]", "kilometer/second [km/s]",
                        "centimeter/hour [cm/h]", "centimeter/minute [cm/min]", "centimeter/second [cm/s]",
                        "millimeter/hour [mm/h]", "millimeter/minute [mm/min]", "millimeter/second [mm/s]"),
                    label = "Select Unit",
                    selectedOption = fromUnit,
                    onOptionSelected = { fromUnit = it }
                )
                OutlinedTextField(
                    value = fromValue,
                    onValueChange = {
                        fromValue = it.filter { char -> char.isDigit() || char == '.' }
                        isEditingFromField = true
                    },
                    singleLine = true,
                    label = { Text("Enter value") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier
                        .width(170.dp)
                        .height(70.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "To Speed",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                DropdownOutlinedTextField(
                    options = listOf("m/s", "km/h", "mph", "mps", "m/min", "km/min", "km/s",
                        "cm/h", "cm/min", "cm/s", "mm/h", "mm/min", "mm/s"),
                    label = "Select Unit",
                    selectedOption = toUnit,
                    onOptionSelected = { toUnit = it }
                )
                OutlinedTextField(
                    value = toValue,
                    onValueChange = {
                        toValue = it.filter { char -> char.isDigit() || char == '.' }
                        isEditingFromField = false
                    },
                    singleLine = true,
                    label = { Text("Enter value") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier
                        .width(170.dp)
                        .height(70.dp)
                )
            }
        }
    }

    @Composable
    fun EnergyScreen(navController: NavController) {

        TopAppBar(
            title = "ENERGY",
            onBackClick = {navController.popBackStack()}
        )

        var fromUnit by remember { mutableStateOf("J") }
        var toUnit by remember { mutableStateOf("J") }
        var fromValue by remember { mutableStateOf("") }
        var toValue by remember { mutableStateOf("") }

        var isEditingFromField by remember { mutableStateOf(true) }

        // Conversion logic
        val energyConversionRates = mapOf(
            "J" to 1.0,                // Base unit
            "kJ" to 1000.0,       // 1 kJ = 1000 J
            "cal" to 4.184,         // 1 cal = 4.184 J
            "kcal" to 4184.0,   // 1 kcal = 4184 J
            "Wh" to 3600.0,       // 1 Wh = 3600 J
            "kWh" to 3.6e6,   // 1 kWh = 3.6e6 J
            "eV" to 1.60218e-19, // 1 eV = 1.60218e-19 J
            "BTU" to 1055.06 // 1 BTU = 1055.06 J
        )

        fun convertEnergy(value: String, fromUnit: String, toUnit: String): String {
            val input = value.toDoubleOrNull() ?: return ""
            val fromRate = energyConversionRates[fromUnit] ?: 1.0
            val toRate = energyConversionRates[toUnit] ?: 1.0
            return ((input * fromRate) / toRate).toString()
        }

        LaunchedEffect(fromValue, fromUnit, toUnit) {
            if (isEditingFromField) {
                toValue = convertEnergy(fromValue, fromUnit, toUnit)
            }
        }

        LaunchedEffect(toValue, fromUnit, toUnit) {
            if (!isEditingFromField) {
                fromValue = convertEnergy(toValue, toUnit, fromUnit)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                "From Energy",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                DropdownOutlinedTextField(
                    options = listOf(
                        "J", "kJ", "cal",
                        "kcal", "Wh",
                        "kWh", "eV",
                        "BTU"
                    ),
                    label = "Select Unit",
                    selectedOption = fromUnit,
                    onOptionSelected = { fromUnit = it }
                )
                OutlinedTextField(
                    value = fromValue,
                    onValueChange = {
                        fromValue = it.filter { char -> char.isDigit() || char == '.' }
                        isEditingFromField = true
                    },
                    singleLine = true,
                    label = { Text("Enter value") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier
                        .width(170.dp)
                        .height(70.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "To Energy",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                DropdownOutlinedTextField(
                    options = listOf(
                        "J", "kJ", "cal",
                        "kcal", "Wh",
                        "kWh", "eV",
                        "BTU"
                    ),
                    label = "Select Unit",
                    selectedOption = toUnit,
                    onOptionSelected = { toUnit = it }
                )
                OutlinedTextField(
                    value = toValue,
                    onValueChange = {
                        toValue = it.filter { char -> char.isDigit() || char == '.' }
                        isEditingFromField = false
                    },
                    singleLine = true,
                    label = { Text("Enter value") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier
                        .width(170.dp)
                        .height(70.dp)
                )
            }
        }
    }


    @Composable
    fun PowerScreen(navController: NavController) {

        TopAppBar(
            title = "POWER",
            onBackClick = { navController.popBackStack() }
        )

        val conversionRates = mapOf(
            "W" to 1.0,
            "kW" to 1000.0,
            "MW" to 1000000.0,
            "hp" to 745.7,
            "BTU/h" to 0.293071,
            "cal/s" to 4.184
        )

        var fromUnit by remember { mutableStateOf("W") }
        var toUnit by remember { mutableStateOf("W") }
        var fromValue by remember { mutableStateOf("") }
        var toValue by remember { mutableStateOf("") }

        var isEditingFromField by remember { mutableStateOf(true) }

        fun convertPower(value: String, from: String, to: String): String {
            val input = value.toDoubleOrNull() ?: return ""
            val fromRate = conversionRates[from] ?: 1.0
            val toRate = conversionRates[to] ?: 1.0
            return ((input / fromRate) * toRate).toString()
        }

        LaunchedEffect(fromValue, fromUnit, toUnit) {
            if (isEditingFromField) {
                toValue = convertPower(fromValue, fromUnit, toUnit)
            }
        }

        LaunchedEffect(toValue, fromUnit, toUnit) {
            if (!isEditingFromField) {
                fromValue = convertPower(toValue, toUnit, fromUnit)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                "From Power",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                DropdownOutlinedTextField(
                    options = listOf ("W", "kW", "MW",
                        "hp", "BTU/h", "cal/s"),
                    label = "Select Unit",
                    selectedOption = fromUnit,
                    onOptionSelected = { fromUnit = it }
                )
                OutlinedTextField(
                    value = fromValue,
                    onValueChange = {
                        fromValue = it.filter { char -> char.isDigit() || char == '.' }
                        isEditingFromField = true
                    },
                    singleLine = true,
                    label = { Text("Enter value") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier
                        .width(170.dp)
                        .height(70.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "To Power",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                DropdownOutlinedTextField(
                    options = listOf ("W", "kW", "MW",
                        "hp", "BTU/h", "cal/s"),
                    label = "Select Unit",
                    selectedOption = toUnit,
                    onOptionSelected = { toUnit = it }
                )
                OutlinedTextField(
                    value = toValue,
                    onValueChange = {
                        toValue = it.filter { char -> char.isDigit() || char == '.' }
                        isEditingFromField = false
                    },
                    singleLine = true,
                    label = { Text("Enter value") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier
                        .width(170.dp)
                        .height(70.dp)
                )
            }
        }
    }

    @Composable
    fun PressureScreen(navController: NavController) {

        TopAppBar(
            title = "PRESSURE",
            onBackClick = {navController.popBackStack()}
        )

        var fromUnit by remember { mutableStateOf("Pa") }
        var toUnit by remember { mutableStateOf("Pa") }
        var fromValue by remember { mutableStateOf("") }
        var toValue by remember { mutableStateOf("") }
        var isEditingFromField by remember { mutableStateOf(true) }

        // Conversion rates
        val conversionRates = mapOf(
            "Pa" to 1.0,
            "kPa" to 1000.0,
            "bar" to 100000.0,
            "atm" to 101325.0,
            "mmHg" to 133.322,
            "psi" to 6894.76
        )

        fun convertPressure(value: String, fromUnit: String, toUnit: String): String {
            val input = value.toDoubleOrNull() ?: return ""
            val fromRate = conversionRates[fromUnit] ?: 1.0
            val toRate = conversionRates[toUnit] ?: 1.0
            return ((input * fromRate) / toRate).toString()
        }

        LaunchedEffect(fromValue, fromUnit, toUnit) {
            if (isEditingFromField) {
                toValue = convertPressure(fromValue, fromUnit, toUnit)
            }
        }

        LaunchedEffect(toValue, fromUnit, toUnit) {
            if (!isEditingFromField) {
                fromValue = convertPressure(toValue, toUnit, fromUnit)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text("From Pressure", modifier = Modifier.align(Alignment.Start).padding(start = 16.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                DropdownOutlinedTextField(
                    options = listOf("Pa",
                        "kPa", "bar",
                        "atm", "mmHg",
                        "psi"),
                    label = "Select Unit",
                    selectedOption = fromUnit,
                    onOptionSelected = { fromUnit = it }
                )
                OutlinedTextField(
                    value = fromValue,
                    onValueChange = {
                        fromValue = it.filter { char -> char.isDigit() || char == '.' }
                        isEditingFromField = true
                    },
                    singleLine = true,
                    label = { Text("Enter value") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.width(170.dp).height(70.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text("To Pressure", modifier = Modifier.align(Alignment.Start).padding(start = 16.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                DropdownOutlinedTextField(
                     options = listOf("Pa",
                        "kPa", "bar",
                        "atm", "mmHg",
                        "psi"),
                    label = "Select Unit",
                    selectedOption = toUnit,
                    onOptionSelected = { toUnit = it }
                )
                OutlinedTextField(
                    value = toValue,
                    onValueChange = {
                        toValue = it.filter { char -> char.isDigit() || char == '.' }
                        isEditingFromField = false
                    },
                    singleLine = true,
                    label = { Text("Enter value") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.width(170.dp).height(70.dp)
                )
            }
        }
    }


    @Composable
    fun FrequencyScreen(navController: NavController) {

        TopAppBar(
            title = "FREQUENCY",
            onBackClick = {navController.popBackStack()}
        )

        var fromUnit by remember { mutableStateOf("Hz") }
        var toUnit by remember { mutableStateOf("Hz") }
        var fromValue by remember { mutableStateOf("") }
        var toValue by remember { mutableStateOf("") }
        var isEditingFromField by remember { mutableStateOf(true) }

        // Conversion rates
        val conversionRates = mapOf(
            "Hz" to 1.0,
            "kHz" to 1000.0,
            "MHz" to 1_000_000.0,
            "GHz" to 1_000_000_000.0,
            "THz" to 1_000_000_000_000.0
        )

        fun convertFrequency(value: String, fromUnit: String, toUnit: String): String {
            val input = value.toDoubleOrNull() ?: return ""
            val fromRate = conversionRates[fromUnit] ?: 1.0
            val toRate = conversionRates[toUnit] ?: 1.0
            return ((input * fromRate) / toRate).toString()
        }

        LaunchedEffect(fromValue, fromUnit, toUnit) {
            if (isEditingFromField) {
                toValue = convertFrequency(fromValue, fromUnit, toUnit)
            }
        }

        LaunchedEffect(toValue, fromUnit, toUnit) {
            if (!isEditingFromField) {
                fromValue = convertFrequency(toValue, toUnit, fromUnit)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text("From Frequency", modifier = Modifier.align(Alignment.Start).padding(start = 16.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                DropdownOutlinedTextField(
                    options = listOf ("Hz",
                        "kHz", "MHz",
                        "GHz", "THz"),
                    label = "Select Unit",
                    selectedOption = fromUnit,
                    onOptionSelected = { fromUnit = it }
                )
                OutlinedTextField(
                    value = fromValue,
                    onValueChange = {
                        fromValue = it.filter { char -> char.isDigit() || char == '.' }
                        isEditingFromField = true
                    },
                    singleLine = true,
                    label = { Text("Enter value") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.width(170.dp).height(70.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text("To Frequency", modifier = Modifier.align(Alignment.Start).padding(start = 16.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                DropdownOutlinedTextField(
                    options = listOf ("Hz",
                        "kHz", "MHz",
                        "GHz", "THz"),
                    label = "Select Unit",
                    selectedOption = toUnit,
                    onOptionSelected = { toUnit = it }
                )
                OutlinedTextField(
                    value = toValue,
                    onValueChange = {
                        toValue = it.filter { char -> char.isDigit() || char == '.' }
                        isEditingFromField = false
                    },
                    singleLine = true,
                    label = { Text("Enter value") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.width(170.dp).height(70.dp)
                )
            }
        }
    }


    @Composable
    fun CurrencyScreen(navController: NavController) {

        TopAppBar(
            title = "CURRENCY",
            onBackClick = { navController.popBackStack() }
        )

        val apiKey = BuildConfig.API_KEY // Access API key from BuildConfig
        val apiService = CurrencyApiService.create()
        val repository = remember { CurrencyRepository(apiService) }
        var exchangeRates by remember { mutableStateOf<Map<String, Double>?>(null) }


        LaunchedEffect(Unit) {
            try {
                val response = repository.getLatestRates(apiKey)
                if (response.success) {
                    exchangeRates = response.rates
                } else {
                    // Handle API error (e.g., display an error message)
                }
            } catch (e: NetworkErrorException) {
                // Handle network error (e.g., display an error message)
            }
        }

        var fromCurrency by remember { mutableStateOf("USD") }
        var toCurrency by remember { mutableStateOf("USD") }
        var fromAmount by remember { mutableStateOf("") }
        var toAmount by remember { mutableStateOf("") }
        var isEditingFromField by remember { mutableStateOf(true) }

        fun convertCurrency(amount: String, from: String, to: String): String {
            val input = amount.toDoubleOrNull() ?: return ""
            val fromRate = exchangeRates?.get(from) ?: 1.0
            val toRate = exchangeRates?.get(to) ?: 1.0
            return ((input / fromRate) * toRate).toString()
        }

        LaunchedEffect(fromAmount, fromCurrency, toCurrency) {
            if (isEditingFromField) {
                toAmount = convertCurrency(fromAmount, fromCurrency, toCurrency)
            }
        }

        LaunchedEffect(toAmount, fromCurrency, toCurrency) {
            if (!isEditingFromField) {
                fromAmount = convertCurrency(toAmount, toCurrency, fromCurrency)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                "From Currency",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                DropdownOutlinedTextField(
                    options = exchangeRates?.keys?.toList() ?: emptyList(),
                    label = "Select Currency",
                    selectedOption = fromCurrency,
                    onOptionSelected = { fromCurrency = it }
                )
                OutlinedTextField(
                    value = fromAmount,
                    onValueChange = {
                        fromAmount = it.filter { char -> char.isDigit() || char == '.' }
                        isEditingFromField = true
                    },
                    singleLine = true,
                    label = { Text("Enter amount") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier
                        .width(170.dp)
                        .height(70.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "To Currency",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                DropdownOutlinedTextField(
                    options = exchangeRates?.keys?.toList() ?: emptyList(),
                    label = "Select Currency",
                    selectedOption = toCurrency,
                    onOptionSelected = { toCurrency = it }
                )
                OutlinedTextField(
                    value = toAmount,
                    onValueChange = {
                        toAmount = it.filter { char -> char.isDigit() || char == '.' }
                        isEditingFromField = false
                    },
                    singleLine = true,
                    label = { Text("Enter amount") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier
                        .width(170.dp)
                        .height(70.dp)
                )
            }
        }
    }

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(title: String, onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = { Text(title) },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                )
                HorizontalDivider()
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            // Your content here
        }
    }
}

@Composable
fun CustomDropdownMenu(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    ) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Row(verticalAlignment = Alignment.CenterVertically) { // Use Row for horizontal alignment
            Text(
                text = selectedOption,
                modifier = Modifier.clickable { expanded = !expanded }
            )
            Icon( // Add dropdown arrow icon
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "Dropdown Arrow"
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            options = options,
            onOptionSelected = onOptionSelected
        )
    }
}

@Composable
fun DropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    options: List<String>,
    onOptionSelected: (String) -> Unit,
) {
    if (expanded) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .wrapContentSize()
        ) {
            options.forEach { option ->
                Text(
                    text = option,
                    modifier = Modifier
                        .clickable {
                            onOptionSelected(option)
                            onDismissRequest()
                        }
                        .padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun ValueField(modifier: Modifier = Modifier) {
    var unitValue by remember { mutableStateOf("") }


    OutlinedTextField(
        value = unitValue,
        onValueChange = { unitValue = it },
        singleLine = true,
        label = { Text("Enter value") },
        modifier = Modifier
            .width(170.dp)
            .height(70.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownOutlinedTextField(
    options: List<String>,
    label: String,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    // ExposedDropdownMenuBox creates a dropdown menu that aligns with a text field
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        // The text field to trigger the dropdown
        OutlinedTextField(
            value = selectedOption,
            onValueChange = { },
            readOnly = true,
            label = { Text(label) },
            modifier = Modifier
                .menuAnchor()
                .width(170.dp)
                .height(70.dp),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    Modifier.clickable { expanded = !expanded }
                )
            }
        )

        // The dropdown menu itself
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(160.dp) // Set the width of the dropdown menu
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}



