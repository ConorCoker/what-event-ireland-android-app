package com.example.whateventireland_androidapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.whateventireland_androidapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupOnClickListeners()
    }

    private fun setupOnClickListeners(){
        binding.buttonRegister.setOnClickListener {
            val registerIntent = Intent(this,RegisterActivity::class.java)
            startActivity(registerIntent)
            setContentView(R.layout.activity_register)
        }
        binding.buttonLogin.setOnClickListener {
            val loginIntent = Intent(this,LoginActivity::class.java)
            startActivity(loginIntent)
            setContentView(R.layout.activity_login)
        }

    }
}