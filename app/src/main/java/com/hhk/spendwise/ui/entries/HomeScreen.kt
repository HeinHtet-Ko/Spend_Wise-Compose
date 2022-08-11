package com.hhk.spendwise.ui.entries

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hhk.spendwise.models.BudgetItem
import com.hhk.spendwise.models.FinanceType
import com.hhk.spendwise.ui.components.home.BackgroundCurve
import com.hhk.spendwise.ui.components.home.HomeCard
import com.hhk.spendwise.ui.theme.LightBlue

@Composable
fun HomeScreen() {

    var income: Double by remember {
        mutableStateOf(800000.00)
    }

    val expense: Double by remember {
        mutableStateOf(300000.00)
    }

    val balance: Double by remember {
        mutableStateOf(200000.00)
    }

    BackgroundCurve(0.33f, LightBlue) {
        Box(contentAlignment = Alignment.TopCenter) {
            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
            ) {
                HomeCard(inc = income, exp = expense)
            }
            Column(
                modifier = Modifier
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
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
            }
            BudgetList()
        }
    }
}

@Composable
fun BudgetList(
    budgetList: List<BudgetItem> = listOf(
        BudgetItem(FinanceType.Expense.CLOTH(), "Clothes", 50000.00),
        BudgetItem(FinanceType.Income.SALARY(), "Salary", 50000.00),
        BudgetItem(FinanceType.Expense.CLOTH(), "Clothes", 50000.00),
        BudgetItem(FinanceType.Income.SALARY(), "Salary", 50000.00),
        BudgetItem(FinanceType.Expense.CLOTH(), "Clothes", 50000.00),
        BudgetItem(FinanceType.Income.SALARY(), "Salary", 50000.00),
        BudgetItem(FinanceType.Expense.CLOTH(), "Clothes", 50000.00),
        BudgetItem(FinanceType.Income.SALARY(), "Salary", 50000.00),
        BudgetItem(FinanceType.Expense.CLOTH(), "Clothes", 50000.00),
        BudgetItem(FinanceType.Income.SALARY(), "Salary", 50000.00),
        BudgetItem(FinanceType.Expense.CLOTH(), "Clothes", 50000.00),
        BudgetItem(FinanceType.Income.SALARY(), "Salary", 50000.00),
    )
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(1f),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column {
            Spacer(modifier = Modifier.fillMaxHeight(0.4f))
            LazyColumn {
                items(budgetList) {
                    Spacer(modifier = Modifier.height(50.dp))
                    Row(
                        modifier = Modifier
                            .height(70.dp)
                            .fillMaxWidth(0.8f)
                            .background(Color.DarkGray)
                    ) {
                        Text(text = "${it.name} and ${it.amount} and ${if (it.category is FinanceType.Expense) "--" else "++"}")
                    }
                }
            }
        }
    }
}
