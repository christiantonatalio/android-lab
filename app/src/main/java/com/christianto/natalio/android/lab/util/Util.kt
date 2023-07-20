package com.christianto.natalio.android.lab.util

fun calculateTipAmount(bill: Double, percentage: Int): Double = if (bill > 1) {
    (bill * percentage) / 100
} else 0.0