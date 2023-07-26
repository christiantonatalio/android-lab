package com.christianto.natalio.android.lab.model

import java.time.LocalDateTime
import java.util.UUID

data class Note  constructor(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val description: String,
    val entryDate: LocalDateTime = LocalDateTime.now()
)