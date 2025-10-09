package com.example.project.view.calculator

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun CalculatorUI() {
    val dig = listOf(
        "C", "D", "/", "*", "8", "7", "6", "-", "5", "4", "3", "+", "2", "1", "0", "="
    )
    var numberString by remember { mutableStateOf("") }
    val scope =    rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Calculator",
            modifier = Modifier.padding(10.dp),
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.weight(1f))


        Text(
            text = numberString,
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Right
        )

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = DividerDefaults.Thickness,
            color = DividerDefaults.color
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
        ) {
            items(dig) { dig ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                        .border(
                            width = 0.5.dp, color = Color.Black, shape = RoundedCornerShape(10.dp)
                        )
                        .clickable{
                            when (dig) {
                                "D" -> {
                                    numberString = numberString.dropLast(1)
                                }
                                "C" -> {
                                    numberString = ""
                                }
                                "=" -> {
                                    scope.launch(Dispatchers.Default){
                                        try {
                                            val result = calculateExpression(numberString).toString()

                                            launch(Dispatchers.Main) {
                                                numberString = result
                                            }                                        }catch (
                                            e: Exception
                                        ){
                                            numberString = "Error"
                                        }
                                    }

                                }
                                else -> {
                                    numberString += dig
                                }
                            }
                        }
                ) {
                    Text(
                        text = dig,
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp)
                    )
                }
            }
        }
    }
}

fun calculateExpression(expr: String): Any {
    val tokens = expr.replace("\\s".toRegex(), "")
    if (tokens.isEmpty()) return 0

    val numbers = mutableListOf<Double>()
    val operators = mutableListOf<Char>()

    var num = ""
    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    fun applyOp() {
        if (numbers.size >= 2 && operators.isNotEmpty()) {
            val b = numbers.removeLast()
            val a = numbers.removeLast()
            val op = operators.removeLast()
            numbers.add(
                when (op) {
                    '+' -> a + b
                    '-' -> a - b
                    '*' -> a * b
                    '/' -> a / b
                    else -> b
                }
            )
        }
    }

    for (ch in tokens) {
        when {
            ch.isDigit() || ch == '.' -> num += ch
            ch in listOf('+', '-', '*', '/') -> {
                if (num.isNotEmpty()) {
                    numbers.add(num.toDouble())
                    num = ""
                }
                while (operators.isNotEmpty() &&
                    precedence(operators.last()) >= precedence(ch)
                ) {
                    applyOp()
                }
                operators.add(ch)
            }
        }
    }

    if (num.isNotEmpty()) numbers.add(num.toDouble())
    while (operators.isNotEmpty()) applyOp()

    return numbers.last()
}

fun precedence(op: Char): Int = when (op) {
    '+', '-' -> 1
    '*', '/' -> 2
    else -> 0
}
