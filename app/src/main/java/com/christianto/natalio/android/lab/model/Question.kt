package com.christianto.natalio.android.lab.model

data class Question(
    val answer: String,
    val category: String,
    val choices: List<String>,
    val question: String
)