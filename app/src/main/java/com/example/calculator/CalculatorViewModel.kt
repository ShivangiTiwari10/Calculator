package com.example.calculator

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

    var state by mutableStateOf(CalculatorState())
        private set

    fun onAction(action: CalculatorActions) {

        when (action) {
            CalculatorActions.Calculate -> performCalculation()
            CalculatorActions.Clear -> state = CalculatorState()
            CalculatorActions.Decimal -> enterDecimal()
            CalculatorActions.Delete -> performDeletion()
            is CalculatorActions.Number -> enterNumber(action.number)
            is CalculatorActions.Operation -> enterOperation(action.operation)
        }
    }

    private fun performDeletion() {
        when {
            state.number2.isNotBlank() -> state = state.copy(
                number2 = state.number2.dropLast(1)
            )

            state.operation != null -> state = state.copy(
                operation = null
            )

            state.number1.isNotBlank() -> state = state.copy(
                number1 = state.number1.dropLast(1)

            )
        }

    }

    private fun performCalculation() {
        var number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()

        if (number1 != null && number2 != null && state.operation != null) {
            when (state.operation) {
              CalculatorOperation.Add -> number1 += number2
                 CalculatorOperation.Subtract -> number1 -= number2
                CalculatorOperation.Multiply -> number1 *= number2
                CalculatorOperation.Divide -> number1 /= number2
                null -> return
            }

            state = CalculatorState(number1 = number1.toString().take(10))
        }
        Log.d(CalculatorOperation.Add.toString(), "Adding")

    }

    private fun enterOperation(operation: CalculatorOperation) {


        if (state.operation == null && state.number1.isNotBlank()) {
            state = state.copy(operation = operation)
        }


    }

    private fun enterDecimal() {


        if (state.operation == null && state.number1.isNotBlank() && !state.number1.contains(".")) {
            state = state.copy(number1 = state.number1.plus("."))
            return
        }

        if (state.number2.isNotBlank() && !state.number2.contains(".")) {
            state = state.copy(number2 = state.number2.plus("."))
            return
        }
    }

    private fun enterNumber(number: Int) {
        state = if (state.operation == null) {
            state.copy(
                number1 = state.number1.plus(number)
            )
        } else {
            state.copy(
                number2 = state.number2.plus(number)
            )
        }
    }


}