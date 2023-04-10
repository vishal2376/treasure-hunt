package com.vishal2376.treasurehint.models

data class Checkpoint(
    val _id: String,
    val cleared: Boolean,
    val endTime: Any,
    val hints: List<Any>,
    val startTime: String
)