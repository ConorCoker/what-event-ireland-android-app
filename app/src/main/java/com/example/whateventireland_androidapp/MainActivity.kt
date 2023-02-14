package com.example.whateventireland_androidapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.whateventireland_androidapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupSpinners()
        setupOnClickListeners()
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
            filter(
                binding.spinnerLocation.selectedItem.toString(),
                binding.spinnerCategory.selectedItem.toString()
            )
            setContentView(binding.root)
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

    private fun filter(location: String, category: String) {
        val eventsAdapter: EventsAdapter =
            if (!location.contentEquals("Anywhere") && category.contentEquals("Anything")) {
                EventsAdapter(ArrayStorage.getInstance().filterByLocation(location))
            } else if (!category.contentEquals("Anything") && location.contentEquals("Anywhere")) {
                EventsAdapter(ArrayStorage.getInstance().filterByCategory(category))
            } else if (!location.contentEquals("Anywhere") && !category.contentEquals("Anything")) {
                EventsAdapter(
                    ArrayStorage.getInstance().filterByCategoryAndLocation(location, category)
                )
            } else {
                EventsAdapter(ArrayStorage.getInstance().getEvents())
            }
        binding.recyclerView.adapter = eventsAdapter
    }
}