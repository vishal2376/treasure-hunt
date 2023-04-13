package com.vishal2376.treasurehint.util

object Constants {
    var Email:String? = null
    var Password:String? = null
    var LocationCount: Int = 1
    var Locations = mutableListOf<Int>(2, 1, 4, 5, 3)
    val Checkpoints = Locations.filter { it != 1 }.toTypedArray()
    val LocationNames = mapOf(
        1 to "Wheel of Treasure",
        2 to "Fitness for a Clue",
        3 to "MapRoulette",
        4 to "SmashTech Frenzy",
        5 to "Treasure Triumph"
    )
//    var Locations1 = mutableListOf<Int>(2, 1, 4, 5, 3)
//    val Checkpoints1 = Locations1.filter { it != 1 }.toTypedArray()
//    val LocationNames1 = mapOf(
//
//    )
}