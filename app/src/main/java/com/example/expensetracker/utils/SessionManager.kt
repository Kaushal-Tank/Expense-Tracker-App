package com.example.expensetracker.utils

import android.content.Context

class SessionManager(context: Context) {

    private val prefs = context.getSharedPreferences("UserSession", Context.MODE_PRIVATE)
    private val editor = prefs.edit()

}