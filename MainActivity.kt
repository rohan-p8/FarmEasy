package com.example.famersmarket

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.ComponentActivity
import android.widget.Toast

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find Views
        val usernameInput = findViewById<EditText>(R.id.username_input)
        val passwordInput = findViewById<EditText>(R.id.password_input)
        val loginBtn = findViewById<Button>(R.id.login_btn)
        val signupButton = findViewById<Button>(R.id.signup_btn)
        val facebookBtn: ImageView = findViewById(R.id.facebook_btn)
        val instagramBtn: ImageView = findViewById(R.id.instagram_btn)

        // Initialize Database Helper
        val dbHelper = DatabaseHelper(this)

        // Login button click
        loginBtn.setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                showToast("Please enter both username and password")
                return@setOnClickListener
            }

            if (dbHelper.validateUser(username, password)) {
                showToast("Login Successful!")
                // Redirect to the home screen or dashboard
                val intent = Intent(this, HomeActivity::class.java) // Replace with your actual home activity
                startActivity(intent)
                finish()
            } else {
                showToast("Invalid username or password")
            }
        }

        // Sign-up button click
        signupButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        // Open Facebook login page
        facebookBtn.setOnClickListener {
            openUrl("https://www.facebook.com/login/")
        }

        // Open Instagram login page
        instagramBtn.setOnClickListener {
            openUrl("https://www.instagram.com/accounts/login/")
        }
    }

    // Function to open URL in browser
    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    // Function to show toast messages
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
