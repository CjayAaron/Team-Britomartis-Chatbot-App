package com.britomartis.android.britobudget

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.core.view.GravityCompat
import android.widget.TextView
import android.net.Uri
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBar
import com.britomartis.android.britobudget.R.id.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout

    val TAG = "MyMessage"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        }
        mDrawerLayout = findViewById(R.id.drawerlay)

        val navigationView: NavigationView = findViewById(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->

            menuItem.isChecked = true

            mDrawerLayout.closeDrawers()

            when (menuItem.itemId) {
                db -> {
                }
                edit -> {
                    val intent = Intent(this, Profile::class.java)
                    startActivity(intent)
                }
                exi -> {
                    System.exit(-1)

                }

            }
            true
        }

        // Check if this is the first launch
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val loggedin = sharedPref?.getBoolean(getString(R.string.user_logged_in), false)
        if (loggedin != null && loggedin == true) {
            Log.d(TAG, "Not the first time")
            startActivity(getMainActivityIntent())
            finish()
        }

        //declare the animation
        val ttp = AnimationUtils.loadAnimation(this, R.anim.ttp)
        val ttb = AnimationUtils.loadAnimation(this, R.anim.ttb)

        val headertitle = findViewById<TextView>(R.id.tite)
        val sideimage = findViewById<ImageView>(R.id.slide)

        //set the animation
        headertitle.startAnimation(ttp)
        sideimage.startAnimation(ttb)


        feedbackcard.setOnClickListener {
            val i = Intent(Intent.ACTION_SEND)
            i.type = "message/rfc822"
            i.putExtra(Intent.EXTRA_EMAIL, arrayOf("teambritomartis@gmail.com"))
            i.putExtra(Intent.EXTRA_SUBJECT, "FeedBack from BritoBot User")
            try {
                startActivity(Intent.createChooser(i, "Send mail..."))
            } catch (ex: android.content.ActivityNotFoundException) {
                Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show()
            }
        }

        chatbotcard.setOnClickListener {
            // Save first launch
            if (sharedPref == null) return@setOnClickListener
            with(sharedPref.edit()) {
                putBoolean(getString(R.string.user_logged_in), true)
                apply()
            }
            startActivity(getMainActivityIntent())
            finish()
        }

        tite.setOnClickListener {

            val intent = Intent(this, Profile::class.java)
            startActivity(intent)}

    }


    private fun getMainActivityIntent(): Intent {
        return Intent(this, MainActivity::class.java)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home ->{
                mDrawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }}

}
