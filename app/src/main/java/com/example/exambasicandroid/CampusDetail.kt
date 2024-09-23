package com.example.exambasicandroid

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class CampusDetail : AppCompatActivity() {

    companion object {
        const val EXTRA_CAMPUS = "extra_campus"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_campus_detail)

        val campusPhoto: ImageView = findViewById(R.id.campus_photo)
        val campusLocation: TextView = findViewById(R.id.campus_location)
        val campusLogo: ImageView = findViewById(R.id.campus_logo)
        val campusName: TextView = findViewById(R.id.campus_name)
        val campusDescriptor: TextView = findViewById(R.id.campus_detail)

        val campus = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Campuses>(EXTRA_CAMPUS, Campuses::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_CAMPUS)
        }

        if (campus != null) {
            val photo = campus.photo
            val location = campus.Location
            val logo = campus.campusLogo
            val name = campus.name
            val description = campus.description

            campusPhoto.setImageResource(photo)
            campusLogo.setImageResource(logo)
            campusName.text = name
            campusLocation.text = location
            campusDescriptor.text = description
        }

    }

    fun isDarkModeEnabled(): Boolean {
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        val menuItem = menu?.findItem(R.id.action_share)
        if (isDarkModeEnabled()) {
            menuItem?.icon = getDrawable(R.drawable.ic_share_dark_mode)
        } else {
            menuItem?.icon = getDrawable(R.drawable.ic_share_light_mode)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.action_share -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}