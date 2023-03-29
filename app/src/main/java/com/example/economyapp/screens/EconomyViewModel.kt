package com.example.economyapp.screens

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class EconomyViewModel : ViewModel() {

    val stockFunds = mutableStateListOf<StockFund>()
    val name = mutableStateOf("")
    val valueCurrent = mutableStateOf("")
    val targetAllocationPercentage = mutableStateOf("")

    fun addStockFund(stockFund: StockFund) {
        stockFunds.add(stockFund)
    }
}