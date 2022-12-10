package com.example.whateventireland_androidapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.whateventireland_androidapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupOnClickListeners()
    }

    private fun setupOnClickListeners() {
        binding.buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            setContentView(R.layout.activity_main)

        }
        binding.buttonRegister.setOnClickListener {

            getInput()
        }
    }

    private fun getInput() {

        if (allFieldsFilled()) {

            if (passwordsMatch()) {
                val newUser = User(
                    binding.textInputEditTextFirstName.text.toString(),
                    binding.textInputEditTextLastName.text.toString(),
                    binding.textInputEditTextEmail.text.toString(),
                    binding.textInputEditTextPassword.text.toString()
                )
                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("User",newUser)
                startActivity(intent)
                setContentView(R.layout.activity_login)
                return //start activity does not immediately launch activity so we need return to
                //stop below code running
            }
            binding.textInputEditTextPassword.setText("")
            binding.textInputEditTextConfirmPassword.setText("")
            Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_LONG).show()
        }
        Toast.makeText(this, "Please enter all fields!", Toast.LENGTH_LONG).show()
    }

    private fun allFieldsFilled(): Boolean {

        if (binding.textInputEditTextFirstName.text.toString().isNotBlank() &&
            binding.textInputEditTextLastName.text.toString().isNotBlank() &&
            binding.textInputEditTextEmail.text.toString().isNotBlank() &&
            binding.textInputEditTextPassword.text.toString().isNotBlank() &&
            binding.textInputEditTextConfirmPassword.text.toString().isNotBlank()
        ) {

            return true
        }
        return false
    }

    private fun passwordsMatch(): Boolean {

        if (binding.textInputEditTextPassword.text.toString()
                .contentEquals(binding.textInputEditTextConfirmPassword.text.toString())
        ) {
            return true
        }
        return false
    }
}