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
    private val utils: Utils = Utils()
    private var eventToUpdate: Event? = null

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
                Toast.makeText(this, "Event ${newEvent.title} has been created", Toast.LENGTH_LONG)
                    .show()
                utils.clearTextFields(
                    binding.textFieldInputEditTextEventTitle,
                    binding.textInputEditTextEventDescription,
                    binding.textInputEditTextEventVenue,
                    binding.textInputEditTextEventPrice,
                    binding.textInputEditTextEventDate
                )
                return@setOnClickListener

            } else {
                Toast.makeText(this, "Please enter all required fields!", Toast.LENGTH_LONG).show()
            }

        }
        binding.buttonDeleteAccount.setOnClickListener {

            if (ArrayStorage.getInstance().deleteUserByEmail(user.email)) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                setContentView(R.layout.activity_login)
            } else {
                Toast.makeText(
                    this,
                    "Something went wrong whilst deleting your account!",
                    Toast.LENGTH_LONG
                ).show()
            }

        }
        binding.buttonDeleteEvent.setOnClickListener {

            if (binding.textInputEditTextMainInput.text.toString().isNotBlank()) {
                if (ArrayStorage.getInstance()
                        .deleteEventById(binding.textInputEditTextMainInput.text.toString().toInt())
                ) {
                    Toast.makeText(this, "Event has been deleted!", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(
                        this,
                        "Error event has not been deleted! Please check if event ID is correct!",
                        Toast.LENGTH_LONG
                    ).show()
                }

            } else {
                Toast.makeText(
                    this,
                    "Please enter an event ID to delete an event!",
                    Toast.LENGTH_LONG
                ).show()
            }

        }
        binding.buttonDisplayEventDetails.setOnClickListener {
            if (binding.textInputEditTextMainInput.text.toString().isNotBlank()) {
                try {
                    val event = ArrayStorage.getInstance().searchEventsById(
                        binding.textInputEditTextMainInput.text.toString().toInt()
                    )
                    if (event != null) {
                        binding.textViewMainOutput.text = event.toString()
                    } else {
                        Toast.makeText(
                            this,
                            "${binding.textInputEditTextMainInput.text.toString()} is not a valid ID",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                } catch (e: java.lang.NumberFormatException) {
                    Toast.makeText(
                        this,
                        "${
                            binding.textInputEditTextMainInput.text.toString().toInt()
                        } is not a valid ID!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                Toast.makeText(this, "Please enter an Event ID!", Toast.LENGTH_LONG).show()
            }

        }
        binding.buttonSummaryOfAllEvents.setOnClickListener {
            var stp = ""
            for (event in ArrayStorage.getInstance().getEvents()) {
                stp += event.toString()
            }
            binding.textViewMainOutput.text = stp

        }
        binding.buttonUpdateEventDetails.setOnClickListener {
            if (eventToUpdate == null) {
                if (binding.textInputEditTextMainInput.text.toString().isNotBlank()) {
                    try {
                        eventToUpdate = ArrayStorage.getInstance().searchEventsById(
                            binding.textInputEditTextMainInput.text.toString().toInt()
                        )
                        if (eventToUpdate != null) {
                            Toast.makeText(
                                this,
                                "Change details above, then re-click 'Update event details' to save",
                                Toast.LENGTH_LONG
                            ).show()
                            utils.disableButtons(
                                binding.buttonRegister,
                                binding.buttonDisplayEventDetails,
                                binding.buttonDeleteEvent,
                                binding.buttonDeleteAccount,
                                binding.buttonSummaryOfAllEvents
                            )
                        } else {
                            Toast.makeText(
                                this,
                                "${binding.textInputEditTextMainInput.text.toString()} is not a valid ID",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    } catch (e: java.lang.NumberFormatException) {
                        Toast.makeText(
                            this,
                            "${
                                binding.textInputEditTextMainInput.text.toString().toInt()
                            } is not a valid ID!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    Toast.makeText(this, "Please enter an Event ID!", Toast.LENGTH_LONG).show()
                }

            } else {
                if (checkIsAllRequiredFieldsFilled()) {
                    eventToUpdate!!.title = binding.textFieldInputEditTextEventTitle.text.toString()
                    eventToUpdate!!.description =
                        binding.textInputEditTextEventDescription.text.toString()
                    eventToUpdate!!.venue =
                        binding.textInputEditTextEventVenue.text.toString()
                    try {
                        eventToUpdate!!.price =
                            binding.textInputEditTextEventPrice.text.toString().toDouble()
                    } catch (e: java.lang.NumberFormatException) {
                        Toast.makeText(this, "Event price must be a number!", Toast.LENGTH_LONG)
                            .show()
                    }
                    eventToUpdate!!.date = binding.textInputEditTextEventDate.text.toString()
                    ArrayStorage.getInstance().deleteEventById(eventToUpdate!!.getId())
                    ArrayStorage.getInstance().addEvent(eventToUpdate!!)
                    Toast.makeText(
                        this,
                        "Event ${eventToUpdate!!.title} has been updated!",
                        Toast.LENGTH_LONG
                    ).show()
                    utils.enableButtons(
                        binding.buttonRegister,
                        binding.buttonDisplayEventDetails,
                        binding.buttonDeleteEvent,
                        binding.buttonDeleteAccount,
                        binding.buttonSummaryOfAllEvents
                    )
                    eventToUpdate = null

                } else {
                    Toast.makeText(
                        this,
                        "Please enter all fields to update your event!",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
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