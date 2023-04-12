package com.vishal2376.treasurehint.util

object Constants {
    var Email:String? = null
    var Password:String? = null
    var LocationCount: Int = 1
    var Locations = mutableListOf<Int>(2, 1, 4, 5, 3)
    val Checkpoints = Locations.filter { it != 1 }.toTypedArray()
    val LocationNames = mapOf(
        1 to "4H",
        2 to "Auditorium",
        3 to "Ground",
        4 to "Open Air Gym",
        5 to "SAC"
    )
}