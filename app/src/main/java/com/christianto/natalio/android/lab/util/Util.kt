package com.christianto.natalio.android.lab.util

fun calculateTipAmount(bill: Double, percentage: Int) = if (bill > 1) {
    (bill * percentage) / 100
} else 0.0

fun calculateTotalPerPerson(
    totalBill: Double,
    splitBy: Int,
    tipPercentage: Int
) = (calculateTipAmount(totalBill, tipPercentage) + totalBill) / splitBy