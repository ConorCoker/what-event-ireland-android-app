package com.example.whateventireland_androidapp

import java.util.Random

class Event(
    val title: String,
    val description: String,
    val venue: String,
    val price: Double,
    val date: String
) {

    private val random: Random = Random()
    private val id = random.nextInt(10000)

    override fun toString(): String {
        return " Title='$title', Description='$description', Venue='$venue', Price=$price, Date='$date', ID='$id')"
    }

    fun getId(): Int {
        return id
    }
}