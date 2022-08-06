package com.hhk.spendwise.models

data class BudgetItem(
    val category: FinanceType,
    val name: String,
    val amount: Double
)

sealed class FinanceType {
    sealed class Income() : FinanceType() {
        data class SALARY(val data: String = "Salary") : Income()
        data class FREELANCE(val data: String = "Freelance") : Income()
    }

    sealed class Expense() : FinanceType() {
        data class FOOD(val data: String = "Food") : Expense()
        data class CLOTH(val data: String = "Cloth") : Expense()
    }

    data class Balance(val data: String = "Balance") : FinanceType()
}
