package com.example.whateventireland_androidapp


class ArrayStorage private constructor() {

    private val users: ArrayList<User> = ArrayList()
    private val events: ArrayList<Event> = ArrayList()
    private val spinnerValuesCategory = fillCategorySpinner()
    private val spinnerValuesLocation = fillLocationSpinner()

    companion object {
        private var instance: ArrayStorage? = null

        fun getInstance(): ArrayStorage {
            if (instance == null) {
                instance = ArrayStorage()
            }
            return instance!!
        }
    }

    fun addUser(user: User) {
        users.add(user)
    }

    fun getUsers(): ArrayList<User> {
        return users
    }

    fun getCategories(): ArrayList<String> {
        return spinnerValuesCategory
    }

    fun getLocations(): ArrayList<String> {
        return spinnerValuesLocation
    }

    fun isValidUserAndIsPasswordCorrect(email: String, password: String): Boolean {

        for (user in users) {
            if (user.email.contentEquals(email) && user.password.contentEquals(password)) {
                return true
            }
        }
        return false
    }

    fun getUserByEmail(email: String): User? {
        for (user in users) {
            if (email.contentEquals(user.email)) {
                return user
            }
        }
        return null
    }

    fun addEvent(event: Event) {
        events.add(event)
    }

    fun getEvents(): ArrayList<Event> {
        return events
    }

    fun deleteUserByEmail(email: String): Boolean {

        return users.remove(getUserByEmail(email))
    }

    fun searchEventsById(id: Int): Event? {

        for (event in events) {

            if (id == event.getId()) {
                return event
            }
        }
        return null
    }

    fun deleteEventById(id: Int): Boolean {

        if (searchEventsById(id) != null) {
            events.remove(searchEventsById(id))
            return true
        }
        return false
    }

    fun getIdByEvent(event: Event): Int {
        for (eventInArray in events) {
            if (eventInArray == event) {
                return eventInArray.getId()
            }
        }
        return -999
    }

    fun getEventById(id: Int): Event? {
        for (event in events) {
            if (event.getId() == id) {
                return event
            }
        }
        return null
    }

    private fun fillCategorySpinner(): ArrayList<String> {

        return arrayListOf(
            "Anything",
            "Music",
            "Arts & Theatre",
            "Sports",
            "Conferences",
            "Community",
            "Education",
            "Festivals",
            "Food & Drink",
            "Fundraisers",
            "Holiday",
            "Networking",
            "Nightlife",
            "Religious"
        )
    }

    private fun fillLocationSpinner(): ArrayList<String> {

        return arrayListOf(
            "Anywhere",
            "Antrim",
            "Armagh",
            "Carlow",
            "Cavan",
            "Clare",
            "Cork",
            "Derry",
            "Donegal",
            "Down",
            "Dublin",
            "Fermanagh",
            "Galway",
            "Kerry",
            "Kildare",
            "Kilkenny",
            "Laois",
            "Leitrim",
            "Limerick",
            "Longford",
            "Louth",
            "Mayo",
            "Meath",
            "Monaghan",
            "Offaly",
            "Roscommon",
            "Sligo",
            "Tipperary",
            "Tyrone",
            "Waterford",
            "Westmeath",
            "Wexford",
            "Wicklow",
            "Online"
        )
    }

    fun filterByLocation(location: String): ArrayList<Event> {

        val listToReturn: ArrayList<Event> = ArrayList()

        for (event in events) {
            if (event.location.contentEquals(location)) {
                listToReturn.add(event)
            }
        }
        return listToReturn

    }

    fun filterByCategory(category: String): ArrayList<Event> {

        val listToReturn: ArrayList<Event> = ArrayList()

        for (event in events) {
            if (event.category.contentEquals(category)) {
                listToReturn.add(event)
            }
        }
        return listToReturn

    }

    fun filterByCategoryAndLocation(location: String, category: String): ArrayList<Event> {

        val listToReturn: ArrayList<Event> = ArrayList()

        for (event in events) {
            if (event.location.contentEquals(location) && event.category.contentEquals(category)) {
                listToReturn.add(event)
            }
        }
        return listToReturn

    }
}
