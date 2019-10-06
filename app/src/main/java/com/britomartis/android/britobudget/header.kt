package com.britomartis.android.britobudget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.view.View
import android.content.Context
import android.content.SharedPreferences
import android.widget.EditText

class header : AppCompatActivity() {

    private val sharedPrefFile = "sharedPreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.header)

        val cName = findViewById<TextView>(R.id.user_name)
        val cMail = findViewById<TextView>(R.id.user_mail)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)

        val sharedIdValue = sharedPreferences.getString("id_key","id")
        val sharedNameValue = sharedPreferences.getString("name_key","defaultname")
            cName.setText("Name: ${sharedNameValue}").toString()
            cMail.setText("Email: ${sharedIdValue}").toString()

    }
}