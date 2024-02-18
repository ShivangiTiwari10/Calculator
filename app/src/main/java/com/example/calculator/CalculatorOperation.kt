package com.example.calculator


sealed class CalculatorOperation() {


    data object Add : CalculatorOperation(){
        override fun toString(): String = "+"
    }
    data object Subtract : CalculatorOperation(){
        override fun toString(): String = "-"
    }
    data object Multiply : CalculatorOperation(){
        override fun toString(): String = "*"
    }
    data object Divide : CalculatorOperation(){
        override fun toString(): String = "%"
    }
}

