package com.vishal2376.treasurehint.models

data class LeaderBoard(
    val message: String,
    val success: Boolean,
    val teams: List<Team>
)