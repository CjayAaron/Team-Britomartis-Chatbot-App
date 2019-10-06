package com.britomartis.android.britobudget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.view.View
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences

class Profile : AppCompatActivity() {

    private val sharedPrefFile = "sharedPreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val inputId = findViewById<EditText>(R.id.u_name)
        val inputName = findViewById<EditText>(R.id.u_email)

        val btnSave = findViewById<Button>(R.id.save_b)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)


        btnSave.setOnClickListener(View.OnClickListener {
            val id:String = inputId.text.toString()
            val name:String = inputName.text.toString()
            val editor:SharedPreferences.Editor =  sharedPreferences.edit()
            editor.putString("id_key",id)
            editor.putString("name_key",name)
            editor.apply()
            editor.commit()

            val intent = Intent(this,StartActivity::class.java)
            startActivity(intent)
        })
}}
