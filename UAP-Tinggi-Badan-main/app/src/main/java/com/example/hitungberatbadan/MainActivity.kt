package com.example.hitungberatbadan

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val genderGroup = findViewById<RadioGroup>(R.id.genderGroup)
        val heightInput = findViewById<EditText>(R.id.heightInput)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val resultText = findViewById<TextView>(R.id.resultText)
        val resultGender = findViewById<TextView>(R.id.resultGender)
        val resultName = findViewById<TextView>(R.id.resultName)
        val nameInput = findViewById<TextView>(R.id.nameInput)

        calculateButton.setOnClickListener{
            val selectedGenderId = genderGroup.checkedRadioButtonId
            val selectedGender = findViewById<RadioButton>(selectedGenderId)
            val heightData = heightInput.text.toString()
            val nameData = nameInput.text.toString()

            if (heightData.isEmpty()) {
                Toast.makeText(this, "Tinggi badan harus di isi bang", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (nameData.isEmpty()) {
                Toast.makeText(this, "Nama Harus di isi bang", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val height = heightData.toDouble()
            val idealWeight = if (selectedGender.text == "pria") {
                resultGender.text = "jenis kelamin pria"
                (height-100)-(0.1*(height - 100))
            } else {
                resultGender.text = "jenis kelamin perempuan"
                (height-100) -(0.15*(height - 100))
            }
          resultName.text= "nama : $nameData"
            resultText.text= "Berat badan ideal anda adalah ${"%.2f".format(idealWeight)} kg"
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}