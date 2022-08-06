package com.hhk.spendwise.ui.theme.entries

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hhk.spendwise.R
import com.hhk.spendwise.models.BudgetItem
import com.hhk.spendwise.models.FinanceType
import com.hhk.spendwise.ui.theme.LightBlue
import com.hhk.spendwise.ui.theme.Purple200
import com.hhk.spendwise.ui.theme.WhiteBlue

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen() {

    var income: Double by remember {
        mutableStateOf(800000.00)
    }

    var isIncrease by remember {
        mutableStateOf(false)
    }

    val expense: Double by remember {
        mutableStateOf(300000.00)
    }

    val balance: Double by remember {
        mutableStateOf(200000.00)
    }

    val onNextMonth: () -> (Unit) = remember {
        {
            isIncrease = true
            income += 10000
        }
    }

    val onPrevious: () -> (Unit) = remember {
        {
            isIncrease = false
            income -= 10000
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
                        backgroundColor = Purple200,
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .height(150.dp),
                        shape = RoundedCornerShape(7.dp),
                        elevation = 5.dp
                    ) {
                        Column {
                            UpperCard(
                                onNextMonth,
                                onPrevious,
                                isIncrease
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

            BudgetList()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun UpperCard(
    onNext: () -> Unit,
    onPrev: () -> Unit,
    isIncrease: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .padding(5.dp, 20.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        var date by remember { mutableStateOf("January 2020") }

        var data by remember {
            mutableStateOf(0)
        }

        Icon(
            modifier = Modifier
                .clip(CircleShape)
                .clickable {
                    onPrev.invoke()
                    date = "decrease ${--data}"
                }
                .padding(5.dp),
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = null,
        )

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
                text = date, modifier = Modifier.padding(horizontal = 50.dp, 0.dp),
                style = MaterialTheme.typography.caption
            )
        }

        Icon(
            modifier = Modifier
                .clip(CircleShape)
                .clickable {
                    onNext.invoke()
                    date = "increase ${++data}"
                }
                .padding(5.dp),
            painter = painterResource(id = R.drawable.ic_arrow_forward),
            contentDescription = null,
        )
    }
}

@Composable
fun LowerCard(income: Double, expense: Double) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        HomeCardText(key = FinanceType.Expense.CLOTH(), value = expense)
        HomeCardText(key = FinanceType.Income.SALARY(), value = income)
    }
}

@Composable
fun HomeCardText(key: FinanceType, value: Double) {
    Column(
        modifier = Modifier
            .padding(7.dp, 3.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (key is FinanceType.Expense) "Expense" else "Income",
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "$value Ks", style = MaterialTheme.typography.subtitle1)
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
                    center = Offset(x = size.width / 2f, -250f)
                )
            }
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            content.invoke()
        }
    }
}

@Composable
fun BudgetList(
    budgetList: List<BudgetItem> = listOf(
        BudgetItem(FinanceType.Expense.CLOTH(), "Clothes", 50000.00),
        BudgetItem(FinanceType.Income.SALARY(), "Salary", 50000.00)
    )
) {
    LazyColumn {
        items(budgetList, key = { it.name }) {
            Row(modifier = Modifier.fillMaxWidth(0.8f)) {
                Text(text = "${it.name} and ${it.amount} and ${if (it.category is FinanceType.Expense) "--" else "++"}")
            }
        }
    }
}
