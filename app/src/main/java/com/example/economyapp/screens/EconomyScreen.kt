package com.example.economyapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Percent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun EconomyScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Funds()
    }
}

@Composable
fun Funds(economyViewModel: EconomyViewModel = viewModel()) {
    val stockFunds = economyViewModel.stockFunds
    LazyColumn(content = {
        items(stockFunds) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = it.name)
                Text(text = it.valueCurrent.toString())
            }
        }
        item { AddNewFund() }
    })
}

data class StockFund(val name: String, val valueCurrent: Int, val targetAllocationPercentage: Int)

@Composable
fun AddNewFund(economyViewModel: EconomyViewModel = viewModel()) {

    val name = economyViewModel.name
    val valueCurrent = economyViewModel.valueCurrent
    val targetAllocationPercentage = economyViewModel.targetAllocationPercentage

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },
            singleLine = true,
            label = {
                Text(
                    text = "Name"
                )
            })

        OutlinedTextField(
            value = valueCurrent.value,
            onValueChange = { valueCurrent.value = it },
            singleLine = true,
            label = {
                Text(
                    text = "Value today"
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AttachMoney,
                    contentDescription = "Money Icon"
                )
            }
        )

        OutlinedTextField(
            value = targetAllocationPercentage.value,
            onValueChange = { targetAllocationPercentage.value = it },
            singleLine = true,
            label = {
                Text(
                    text = "Target Allocation"
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Percent,
                    contentDescription = "Percent Icon"
                )
            }
        )

        Button(onClick = {
            economyViewModel.addStockFund(
                stockFund = StockFund(
                    name = name.value,
                    valueCurrent = valueCurrent.value.toInt(),
                    targetAllocationPercentage = targetAllocationPercentage.value.toInt()
                )
            )
        }) {
            Text(text = "Add")
        }
    }
}