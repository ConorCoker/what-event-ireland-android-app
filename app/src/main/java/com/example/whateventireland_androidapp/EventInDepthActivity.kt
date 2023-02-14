package com.example.whateventireland_androidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.whateventireland_androidapp.databinding.ActivityEventInDepthBinding

class EventInDepthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventInDepthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}