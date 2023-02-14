package com.example.whateventireland_androidapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.whateventireland_androidapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupSpinners()
        setupOnClickListeners()
        loadRecyclerView()
        val event1 = Event(
            "Chess",
            "Chess is fun",
            "The 3 arena",
            12.99,
            "12/12/08",
            null,
            "Antrim",
            "Education"
        )
        ArrayStorage.getInstance().addEvent(event1)
    }

    private fun loadRecyclerView() {
        val eventsAdapter = EventsAdapter(
            filter(
                binding.spinnerLocation.selectedItem.toString(),
                binding.spinnerCategory.selectedItem.toString()
            )
        ) {
            moveToInDepthView(it)
        }
        binding.recyclerView.adapter = eventsAdapter
    }

    private fun setupOnClickListeners() {
        binding.buttonRegister.setOnClickListener {
            val registerIntent = Intent(this, RegisterActivity::class.java)
            startActivity(registerIntent)
            setContentView(R.layout.activity_register)
        }
        binding.buttonLogin.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
            setContentView(R.layout.activity_login)
        }
        binding.buttonSearch.setOnClickListener {
          loadRecyclerView()
        }
    }

    private fun setupSpinners() {

        val categoryAdapter = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            ArrayStorage.getInstance().getCategories()
        )
        binding.spinnerCategory.adapter = categoryAdapter

        val locationAdapter = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            ArrayStorage.getInstance().getLocations()
        )
        binding.spinnerLocation.adapter = locationAdapter
    }

    private fun filter(location: String, category: String): ArrayList<Event> {

        return if (!location.contentEquals("Anywhere") && category.contentEquals("Anything")) {
            ArrayStorage.getInstance().filterByLocation(location)
        } else if (!category.contentEquals("Anything") && location.contentEquals("Anywhere")) {
            ArrayStorage.getInstance().filterByCategory(category)
        } else if (!location.contentEquals("Anywhere") && !category.contentEquals("Anything")) {
            ArrayStorage.getInstance().filterByCategoryAndLocation(location, category)
        } else {
            ArrayStorage.getInstance().getEvents()
        }

    }

    private fun moveToInDepthView(eventToView: Event) {
        val intent = Intent(this, EventInDepthActivity::class.java)
        intent.putExtra("event", eventToView)
        startActivity(intent)
        setContentView(R.layout.activity_event_in_depth)
    }
}