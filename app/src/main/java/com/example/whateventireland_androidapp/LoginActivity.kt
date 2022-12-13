package com.example.whateventireland_androidapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.whateventireland_androidapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupOnClickListeners()

    }

    private fun setupOnClickListeners() {
        binding.buttonBackLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            setContentView(R.layout.activity_main)
            startActivity(intent)
        }
        binding.buttonLogin.setOnClickListener {
            if (isFieldsFilled()) {
                if (ArrayStorage.getInstance().isValidUserAndIsPasswordCorrect(
                        binding.textInputEditTextEmail.text.toString(),
                        binding.textInputEditTextPassword.text.toString()
                    )
                ) {
                    val intent = Intent(this, OrganiserActivity::class.java)
                    intent.putExtra("user",binding.textInputEditTextEmail.text.toString())
                    startActivity(intent)
                    setContentView(R.layout.activity_organiser)
                    return@setOnClickListener //start activity does not immediately launch activity so we need return to
                    //stop below code running
                }
                Toast.makeText(this, "Username or password is incorrect!", Toast.LENGTH_LONG).show()
                binding.textInputEditTextEmail.setText("")
                binding.textInputEditTextPassword.setText("")
            }
            Toast.makeText(this, "Please enter all fields!", Toast.LENGTH_LONG).show()

        }
    }

    private fun isFieldsFilled(): Boolean {
        if (binding.textInputEditTextEmail.text.toString().isNotBlank() &&
            binding.textInputEditTextPassword.text.toString().isNotBlank()
        ) {
            return true
        }
        return false
    }


}