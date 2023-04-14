package com.vishal2376.treasurehint.models

data class TeamX(
    val __v: Int,
    val _id: String,
    val checkpoints: List<Checkpoint>,
    val email: String,
    val helpers: Helpers,
    val members: List<Any>,
    val name: String,
    val registered: Boolean,
    val score: Int
)