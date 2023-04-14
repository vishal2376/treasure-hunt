package com.vishal2376.treasurehint.util

object Constants {
    var Email:String? = null
    var Password:String? = null
    var LocationCount: Int = 1
    var Locations = mutableListOf<Int>(2, 1, 4, 5, 3)
    val Checkpoints = Locations.filter { it != 1 }.toTypedArray()
    val LocationNames = mapOf(
        1 to "SAC",
        2 to "Open Air Gym",
        3 to "4H",
        4 to "OAT",
        5 to "Auditorium"
    )
    var Locations1 = mutableListOf<Int>(2, 1, 4, 5, 3)
    val Checkpoints1 = Locations.filter { it != 1 }.toTypedArray()
    val LocationNames1 = mapOf(
        1 to "Wheel of Treasure",
        2 to "Fitness for a Clue",
        3 to "MapRoulette",
        4 to "SmashTech Frenzy",
        5 to "Treasure Triumph"
    )
}