package com.example.whateventireland_androidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.whateventireland_androidapp.databinding.ActivityOrganiserBinding


class OrganiserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrganiserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrganiserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textViewTest.text = intent.getStringExtra("email")
    }
}