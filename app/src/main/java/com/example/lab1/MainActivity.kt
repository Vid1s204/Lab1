package com.example.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.lab1.databinding.ActivityMainBinding

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Adding a log statement to check if onCreate() is executed
        Log.d("MainActivity", "onCreate() executed")

        binding.button.setOnClickListener {
            // Adding a log statement to check if the button click listener is triggered
            Log.d("MainActivity", "Register button clicked")

            val studentId = binding.SID.text.toString()
            val studentName = binding.SName.text.toString()
            val studentAge = binding.SAge.text.toString()
            val studentAddress = binding.SAddress.text.toString()
            val studentEmail = binding.SEmail.text.toString()

            database = FirebaseDatabase.getInstance().getReference("User")

            val user1 = User(studentName, studentAge, studentAddress, studentEmail)

            database.child(studentId).setValue(user1)
                .addOnSuccessListener {
                    binding.SID.text.clear()
                    binding.SName.text.clear()
                    binding.SAge.text.clear()
                    binding.SAddress.text.clear()
                    binding.SEmail.text.clear()
                    Toast.makeText(this, "Data Save Successfully", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, "Operation Failed: ${exception.message}", Toast.LENGTH_SHORT).show()
                    // Adding a log statement to log the error message
                    Log.e("MainActivity", "Operation Failed", exception)
                }
        }
    }
}