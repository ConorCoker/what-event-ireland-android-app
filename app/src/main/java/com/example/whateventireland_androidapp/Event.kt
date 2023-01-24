package com.example.whateventireland_androidapp

import java.util.Random

class Event(
    var title: String,
    var description: String,
    var venue: String,
    var price: Double,
    var date: String
) {

    private val random: Random = Random()
    private val id = random.nextInt(10000)

    override fun toString(): String {
        return " Title='$title', Description='$description', Venue='$venue', Price=$price, Date='$date', ID='$id')\n"
    }

    fun getId(): Int {
        return id
    }
}