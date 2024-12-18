package com.ngocnhungo.currency_calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
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


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val spinner1: Spinner = findViewById(R.id.spinner1)
        val spinner2: Spinner = findViewById(R.id.spinner2)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.waehrung,
            R.layout.spinner
        )
        adapter.setDropDownViewResource(R.layout.spinner)
        spinner1.adapter = adapter
        spinner2.adapter = adapter


        val editTextFrom: EditText = findViewById(R.id.editTextNumberDecimal4)
        val editTextTo: EditText = findViewById(R.id.editTextNumberDecimal5)


        val exchangeRates = mapOf(
            "EURtoVND" to 27663.0,
            "EURtoUSD" to 1.09,
            "USDtoEUR" to 0.92,
            "VNDtoEUR" to 0.00003,
            "USDtoVND" to 25422.0,
            "VNDtoUSD" to 0.00004
        )

        
        editTextFrom.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val fromCurrency = spinner1.selectedItem.toString()
                val toCurrency = spinner2.selectedItem.toString()

                if (s.isNullOrEmpty() || fromCurrency == toCurrency) {
                    editTextTo.setText("")
                    return
                }

                val amount = s.toString().toDoubleOrNull()
                if (amount != null) {
                    val key = "${fromCurrency}to${toCurrency}"
                    val rate = exchangeRates[key]

                    if (rate != null) {
                        val convertedAmount = amount * rate
                        editTextTo.setText("%.0f".format(convertedAmount))
                    } else {
                        editTextTo.setText("Kein Kurs")
                    }
                } else {
                    editTextTo.setText("")
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}
