package id.aej.firstapp.ui.page.lemonade

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.aej.firstapp.R

@Preview(showBackground = true)
@Composable
fun LemonadeApp(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        var currentStep by remember { mutableStateOf(1) }
        var squeezeCount by remember { mutableStateOf(0) }

        when(currentStep){
            1 -> {
                LemonTextAndImage(
                    textLabel = "Tap the lemon tree to select a lemon",
                    drawableResourceId = R.drawable.lemon_tree,
                    contentDescription = "Tap the lemon tree to select a lemon",
                    onImageClick = {
                        currentStep = 2
                        squeezeCount = (2..4).random()
                    })
            }
            2 -> {
                LemonTextAndImage(
                    textLabel = "Keep tapping the lemon to squeeze it",
                    drawableResourceId = R.drawable.lemon_squeeze,
                    contentDescription = "Keep tapping the lemon to squeeze it",
                    onImageClick = {
                        squeezeCount--
                        if (squeezeCount == 0) currentStep = 3
                    })
            }
            3 -> {
                LemonTextAndImage(
                    textLabel = "Tap the lemonade to drink it",
                    drawableResourceId = R.drawable.lemon_drink,
                    contentDescription = "Tap the lemonade to drink it",
                    onImageClick = {
                        currentStep = 4
                    })
            }
            4 -> {
                LemonTextAndImage(
                    textLabel = "Tap the empty glass to start again",
                    drawableResourceId = R.drawable.lemon_restart,
                    contentDescription = "Tap the empty glass to start again",
                    onImageClick = {
                        currentStep = 1
                    })
            }
        }
    }
}

@Composable
fun LemonTextAndImage(
    textLabel: String,
    drawableResourceId: Int,
    contentDescription: String,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = textLabel, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = drawableResourceId),
            contentDescription = contentDescription,
            modifier = Modifier
                .wrapContentSize()
                .clickable(onClick = onImageClick)
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
        )
    }
}