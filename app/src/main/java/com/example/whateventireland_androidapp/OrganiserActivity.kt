package com.example.whateventireland_androidapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.whateventireland_androidapp.databinding.ActivityOrganiserBinding


class OrganiserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrganiserBinding
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrganiserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        user = ArrayStorage.getInstance().getUserByEmail(intent.getStringExtra("user")!!)!!
        setupOnClickListeners()
        setupSpinners()

    }

    private fun setupOnClickListeners() {

        binding.buttonLogout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            setContentView(R.layout.activity_main)
        }
        binding.buttonRegister.setOnClickListener {
            if (checkIsAllRequiredFieldsFilled()) {
                val newEvent = Event(
                    binding.textFieldInputEditTextEventTitle.text.toString(),
                    binding.textInputEditTextEventDescription.text.toString(),
                    binding.textInputEditTextEventVenue.text.toString(),
                    binding.textInputEditTextEventPrice.text.toString().toDouble(),
                    binding.textInputEditTextEventDate.text.toString()
                )
                ArrayStorage.getInstance().addEvent(newEvent)
                Toast.makeText(this,"Event ${newEvent.title} has been created",Toast.LENGTH_LONG).show()
                return@setOnClickListener

            } else {
                Toast.makeText(this, "Please enter all required fields!", Toast.LENGTH_LONG).show()
            }

        }
        binding.buttonDeleteAccount.setOnClickListener {

            if (ArrayStorage.getInstance().deleteUserByEmail(user.email)){
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
                setContentView(R.layout.activity_login)
            }
            else{
                Toast.makeText(this,"Something went wrong whilst deleting your account!",Toast.LENGTH_LONG).show()
            }

        }
        binding.buttonDeleteEvent.setOnClickListener {

            if (binding.textInputEditTextMainInput.text.toString().isNotBlank()){

            }
            else{
                Toast.makeText(this,"Please enter an event ID to delete an event!",Toast.LENGTH_LONG).show()
            }

        }
        binding.buttonDisplayEventDetails.setOnClickListener {

        }
        binding.buttonSummaryOfAllEvents.setOnClickListener {
            var stp = ""
            for (event in ArrayStorage.getInstance().getEvents()){
                stp += event.toString()
            }
            binding.textViewMainOutput.text = stp

        }
        binding.buttonUpdateEventDetails.setOnClickListener {

        }
    }

    private fun setupSpinners() {
        val spinnerValuesCategory = arrayOf(
            "Cultural", "Community", "Educational", "Religious",
            "Corporate", "Political", "Sports", "Comedy", "Music"
        )
        val spinnerValuesLocation = arrayOf(
            "Dublin",
            "Cork",
            "Galway",
            "Belfast",
            "Derry",
            "Limerick",
            "Waterford",
            "Sligo",
            "Online"
        )

        val spinnerAdapterCategory = ArrayAdapter(
            this,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            spinnerValuesCategory
        )
        binding.spinnerCategory.adapter = spinnerAdapterCategory

        val spinnerAdapterLocation = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            spinnerValuesLocation
        )
        binding.spinnerLocation.adapter = spinnerAdapterLocation
    }

    private fun checkIsAllRequiredFieldsFilled(): Boolean {
        if (binding.textFieldInputEditTextEventTitle.text.toString().isNotBlank() &&
            binding.textInputEditTextEventDescription.text.toString().isNotBlank() &&
            binding.textInputEditTextEventVenue.text.toString().isNotBlank() &&
            binding.textInputEditTextEventDate.text.toString().isNotBlank() &&
            binding.textInputEditTextEventPrice.text.toString().isNotBlank()
        ) {
            return true
        }
        return false
    }
}