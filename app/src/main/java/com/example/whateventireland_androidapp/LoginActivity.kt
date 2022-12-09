package com.example.whateventireland_androidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.whateventireland_androidapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    companion object {
        var users: ArrayList<User> = ArrayList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        users.add(intent.getSerializableExtra("User") as User)
        binding.textViewTest.text = users.get(0).firstName //testing is my array holding a valid user
    }


}