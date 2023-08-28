package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }
            }
        }
    }
}

// Chain a fillMaxSize() method onto the Modifier
// object so that the layout fills the entire screen.
/*
Composables are stateless by default, which means that
they don't hold a value and can be recomposed any time by the system,
which results in the value being reset. However, Compose provides
a convenient way to avoid this. Composable functions can
store an object in memory using the remember composable.
 */
@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    //When the value of this variable changes the Ui will build just like setState in flutter
    var result by remember {
        mutableStateOf(1)
    }

    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    Column(modifier = modifier
        .fillMaxSize()
        .wrapContentSize(align = Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = imageResource), contentDescription = result.toString())

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { result = (1..6).random() }) {
            Text(text = stringResource(id = R.string.roll))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DiceRollerApp(){
    DiceWithButtonAndImage()
}