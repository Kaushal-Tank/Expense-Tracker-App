package com.example.expensetracker.model

data class Expense(
    val title : String,
    val amount: Double,
    val date:com.google.firebase.Timestamp = com.google.firebase.Timestamp.now()
)
