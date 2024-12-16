package com.ngocnhungo.currency_calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ArrayAdapter


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "CutPasteId")
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
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.waehrung,
            R.layout.spinner
        )
        val spinner2: Spinner = findViewById(R.id.spinner2 )
        val adapter2 = ArrayAdapter.createFromResource(
            this,
            R.array.waehrung,
            R.layout.spinner
        )
        adapter.setDropDownViewResource(R.layout.spinner)
        spinner1.adapter = adapter
        spinner2.adapter = adapter
    }
}