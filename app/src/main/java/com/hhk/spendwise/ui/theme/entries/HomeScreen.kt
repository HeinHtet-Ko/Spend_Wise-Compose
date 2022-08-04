package com.hhk.spendwise.ui.theme.entries

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hhk.spendwise.R
import com.hhk.spendwise.ui.theme.LightBlue
import com.hhk.spendwise.ui.theme.Purple200
import com.hhk.spendwise.ui.theme.WhiteBlue

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen() {

    var income: String by remember {
        mutableStateOf("2000Ks")
    }

    var isIncrease by remember {
        mutableStateOf(false)
    }

    val expense: String by remember {
        mutableStateOf("50000Ks")
    }

    val balance: String by remember {
        mutableStateOf("1000000Ks")
    }

    val onNextMonth: () -> (String) = remember {
        {
            isIncrease = true
            income += "8yt90"
            income
        }
    }

    val onPrevious: () -> (String) = remember {
        {
            isIncrease = true
            income = income.toCharArray().toMutableList().removeLast().toString()
            income
        }
    }
    BackgroundCurve(0.33f, LightBlue) {
        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(60.dp))
                Text(
                    text = "Overview",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Here is a list of your transactions",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.caption,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(50.dp))
                Box(
                    contentAlignment = Alignment.BottomCenter,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Card(
                        backgroundColor = Purple200, modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .height(150.dp),
                        shape = RoundedCornerShape(7.dp),
                        elevation = 5.dp
                    ) {
                        Column {
                            UpperCard(
                                onNextMonth,
                                onPrevious
                            )
                            AnimatedContent(targetState = income, transitionSpec = {
                                if (isIncrease)
                                    slideInHorizontally(
                                        animationSpec = tween(200),
                                        initialOffsetX = { fullWidth -> fullWidth }
                                    ) with fadeOut(animationSpec = tween(0))
                                else
                                    slideInHorizontally(
                                        animationSpec = tween(200),
                                        initialOffsetX = { fullWidth -> -fullWidth }
                                    ) with fadeOut(animationSpec = tween(0))
                            }) {
                                LowerCard(
                                    income,
                                    expense
                                )
                            }

                        }

                    }
                }
            }

        }

    }

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun UpperCard(
    onNext: () -> String,
    onPrev: () -> String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .padding(5.dp, 20.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        var date by remember { mutableStateOf("January 2020") }
        var isIncrease by remember {
            mutableStateOf(false)
        }

        var data by remember {
            mutableStateOf(0)
        }

        Image(modifier = Modifier
            .clip(CircleShape)
            .background(Color.Red)
            .clickable {
                onPrev.invoke()
                isIncrease = false
                date = "decrease ${--data}"
            }.padding(7.dp) ,
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = null,
        contentScale = ContentScale.Crop)

        AnimatedContent(targetState = date, transitionSpec = {
            if (isIncrease)
                slideInHorizontally(
                    animationSpec = tween(200),
                    initialOffsetX = { fullWidth -> fullWidth }
                ) with fadeOut(animationSpec = tween(0))
            else
                slideInHorizontally(
                    animationSpec = tween(200),
                    initialOffsetX = { fullWidth -> -fullWidth }
                ) with fadeOut(animationSpec = tween(0))
        }) {
            Text(
                text = date, modifier = Modifier.padding(horizontal = 75.dp, 0.dp),
                style = MaterialTheme.typography.caption
            )
        }


        Image(modifier = Modifier
            .clip(CircleShape)
            .background(Color.Red)
            .clickable {
                onNext.invoke()
                isIncrease = true
                date = "increase ${++data}"
            }.padding(7.dp),
            painter = painterResource(id = R.drawable.ic_arrow_forward),
            contentDescription = null,
        contentScale = ContentScale.Crop)
    }

}

@Composable
fun LowerCard(income: String, expense: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            modifier = Modifier
                .padding(7.dp, 3.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Income", style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(7.dp))
            Text(text = income, style = MaterialTheme.typography.subtitle1)
        }
        Column(
            modifier = Modifier
                .padding(7.dp, 3.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Expense", style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(7.dp))
            Text(text = expense, style = MaterialTheme.typography.subtitle1)
        }

    }
}

@Composable
fun HomeCardText(key: FinanceType, value: String) {
    Column(
        modifier = Modifier
            .padding(7.dp, 3.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = key.name, style = MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = value, style = MaterialTheme.typography.subtitle1)
    }
}

@Composable
fun BackgroundCurve(curveHeight: Float, curveBackground: Color, content: @Composable () -> Unit) {
    Box {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(curveHeight),
            onDraw = {

                drawCircle(
                    brush = Brush.horizontalGradient(listOf(curveBackground, WhiteBlue)),
                    radius = size.height + 260,
                    center = Offset(x = size.width / 2f, -250f),

                    )
            })
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            content.invoke()
        }
    }

}

enum class FinanceType { Income, Expense, Balance }