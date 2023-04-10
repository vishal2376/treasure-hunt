package com.vishal2376.treasurehint.models

data class Team(
    val __v: Int,
    val _id: String,
    val checkpoints: List<Checkpoint>,
    val email: String,
    val members: List<Any>,
    val name: String,
    val registered: Boolean,
    val score: Int
)