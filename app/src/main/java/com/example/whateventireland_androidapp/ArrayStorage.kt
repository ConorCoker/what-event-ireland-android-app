package com.example.whateventireland_androidapp

class ArrayStorage private constructor() {

    private val users: ArrayList<User> = ArrayList()
    private val events: ArrayList<Event> = ArrayList()

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
}