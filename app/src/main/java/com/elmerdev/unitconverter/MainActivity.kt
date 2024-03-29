package com.elmerdev.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elmerdev.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      UnitConverterTheme {
        // A surface container using the 'background' color from the theme
        Surface(  modifier = Modifier.fillMaxSize(),
                  color = MaterialTheme.colorScheme.background
        ) {
          UnitConverter()
        }
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitConverter() {
  // We will use remember to store the state of our input and output values
  var inputValue by remember { mutableStateOf("") }
  var outputValue by remember { mutableStateOf("") }
  var inputUnit by remember { mutableStateOf("Meters") }
  var outputUnit by remember { mutableStateOf("Meters") }
  var iExpanded by remember { mutableStateOf(false) }
  var oExpanded by remember { mutableStateOf(false) }
  var iConversionFactor by remember { mutableStateOf(1.00) }
  var oConversionFactor by remember { mutableStateOf(1.00) }

  // We can create a custom style for our Text composable
  val customTitleStyle = TextStyle(
    fontFamily = FontFamily.Monospace,
    fontSize = 34.sp,
    color = Color.Red
  )

  fun convertUnits(){
    // ?: -> Elvis Operator
    val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
    val result = (inputValueDouble * iConversionFactor * 100.0 / oConversionFactor).roundToInt() / 100.0
    outputValue = result.toString()
  }
  // All the UI elements will be stacked below each other
  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // Text element
    Text( text = "Unit Converter",
          style = customTitleStyle
    )
    Spacer(modifier = Modifier.height(16.dp))
    OutlinedTextField(  value = inputValue,
                        onValueChange = {
                          //Here goes what should happen when the value of our text field changes
                          inputValue = it
                          convertUnits()
                        },
                        label = { Text("Enter Value") }
    )
    Spacer(modifier = Modifier.height(16.dp))
    // All the UI elements will be placed next to each other
    Row {
      // Input Box
      Box {
        // Select Input Unit Button
        Button(onClick = {
          iExpanded = true
        }) {
          Text(text = inputUnit)
          Icon( Icons.Default.ArrowDropDown,
                contentDescription = "Arrow Down"
          )
        }
        DropdownMenu( expanded = iExpanded,
                      onDismissRequest = {
                        iExpanded = false
                      }
        ) {
          DropdownMenuItem( text = { Text("Centimeters") },
                            onClick = {
                              inputUnit = "Centimeters"
                              iExpanded = false
                              iConversionFactor = 0.01
                              convertUnits()
                            })
          DropdownMenuItem( text = { Text("Meters") },
                            onClick = {
                              inputUnit = "Meters"
                              iExpanded = false
                              iConversionFactor = 1.0
                              convertUnits()
                            })
          DropdownMenuItem( text = { Text("Millimeters") },
                            onClick = {
                              inputUnit = "Millimeters"
                              iExpanded = false
                              iConversionFactor = 0.001
                              convertUnits()
                            })
          DropdownMenuItem( text = { Text("Feet") },
                            onClick = {
                              inputUnit = "Feet"
                              iExpanded = false
                              iConversionFactor = 0.3048
                              convertUnits()
                            })
        }
      }

      Spacer(modifier = Modifier.width(16.dp))
      // Output Box
      Box {
        // Select Output Unit Button
        Button(onClick = {
          oExpanded = true
        }){
          Text( text = outputUnit)
          Icon( Icons.Default.ArrowDropDown,
                contentDescription = "Arrow Down"
              )
        }
        DropdownMenu( expanded = oExpanded,
                      onDismissRequest = {
                        oExpanded = false
                      }
        ) {
          DropdownMenuItem( text = { Text("Centimeters") },
                            onClick = {
                              outputUnit = "Centimeters"
                              oExpanded = false
                              oConversionFactor = 0.01
                              convertUnits()
                            })
          DropdownMenuItem( text = { Text("Meters") },
                            onClick = {
                              outputUnit = "Meters"
                              oExpanded = false
                              oConversionFactor = 1.0
                              convertUnits()
                            })
          DropdownMenuItem( text = { Text("Millimeters") },
                            onClick = {
                              outputUnit = "Millimeters"
                              oExpanded = false
                              oConversionFactor = 0.001
                              convertUnits()
                            })
          DropdownMenuItem( text = { Text("Feet") },
                            onClick = {
                              outputUnit = "Feet"
                              oExpanded = false
                              oConversionFactor = 0.3048
                              convertUnits()
                            })
        }
      }
    }
    Spacer(modifier = Modifier.height(16.dp))

    // Result Text
    Text( text = "Result: $outputValue $outputUnit",
          style = MaterialTheme.typography.headlineMedium
    )
  }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
  UnitConverter()
}