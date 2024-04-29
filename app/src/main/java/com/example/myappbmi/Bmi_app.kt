package com.example.myappbmi

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Bmi_app : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_calculate)

        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        btnCalculate.setOnClickListener {
            calculateBMI()
        }

        val btnInfo = findViewById<Button>(R.id.btnInfo)
        btnInfo.setOnClickListener {
            showInfo(this)
        }
    }

    private fun calculateBMI() {
        val weightEditText = findViewById<EditText>(R.id.numberWeight)
        val heightEditText = findViewById<EditText>(R.id.numberHeight)
        val resultTextView = findViewById<TextView>(R.id.result)
        val statusTextView = findViewById<TextView>(R.id.status)
        val spinnerAgeGroup = findViewById<Spinner>(R.id.spinnerAgeGroup)

        val weightString = weightEditText.text.toString()
        val heightString = heightEditText.text.toString()

        if (weightString.isNotEmpty() && heightString.isNotEmpty()) {
            val weight = weightString.toFloat()
            val height = heightString.toFloat() / 100 // convert cm to m
            val bmi = weight / (height * height)
            resultTextView.text = "BMI: $bmi"

            val ageGroup = spinnerAgeGroup.selectedItem.toString()
            val status = if (ageGroup == "Dewasa") {
                if (bmi < 18.5) {
                    "status = Status: Berat Badan Kurang"
                } else if (bmi <= 22.9) {
                    "status = Status: Berat Badan Normal"
                } else if (bmi <= 29.9) {
                    "status = Status: Berat Badan Berlebih (kecenderungan obesitas)"
                } else {
                    "status = Status: Obesitas"
                }
            } else {
                if (bmi < 9.5) {
                    "status = Status: Berat Badan Kurang"
                } else if (bmi <= 11.9) {
                    "status = Status: Berat Badan Normal"
                } else if (bmi <= 15.9) {
                    "status = Status: Berat Badan Berlebih (kecenderungan obesitas)"
                } else {
                    "status = Status: Obesitas"
                }
            }
            statusTextView.text = status
        } else {
            Toast.makeText(this, "Masukkan berat dan tinggi terlebih dahulu", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showInfo(context: Context) {
        val message = "Informasi tambahan ditampilkan!"
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}