package com.example.exambasicandroid

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    lateinit var rvCampuses: RecyclerView
    private val list = ArrayList<Campuses>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvCampuses = findViewById(R.id.rv_campuses)
        rvCampuses.setHasFixedSize(true)

        list.addAll(getListCampuses())
        showRecyclerList()
    }

    private fun getListCampuses(): ArrayList<Campuses> {
        val dataName: Array<String> = resources.getStringArray(R.array.data_campus_name)
        val dataShortDesc: Array<String> = resources.getStringArray(R.array.data_short_desc)
        val dataLogo = resources.obtainTypedArray(R.array.data_logo_campus)
        val dataPublicOrPrivate: Array<String> = resources.getStringArray(R.array.data_locatoin)
        val dataDescription: Array<String> = resources.getStringArray(R.array.data_description)
        val dataPhotoCampuses = resources.obtainTypedArray(R.array.data_photo_campus)


        val listCampuses = ArrayList<Campuses>()

        for (position in dataName.indices) {
            val campuses = Campuses(
                dataLogo.getResourceId(position, -1),
                dataName[position],
                dataShortDesc[position],
                dataPublicOrPrivate[position],
                dataDescription[position],
                dataPhotoCampuses.getResourceId(position, -1)
            )
            listCampuses.add(campuses)
        }

        return listCampuses
    }

    private fun showRecyclerList() {
        rvCampuses.layoutManager = LinearLayoutManager(this)
        val listCampusAdapter = ListCampusAdapter(list)
        rvCampuses.adapter = listCampusAdapter

        listCampusAdapter.setOnClickCallback(object : ListCampusAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Campuses) {
                val moveIntent = Intent(this@MainActivity, CampusDetail::class.java)
                moveIntent.putExtra(CampusDetail.EXTRA_CAMPUS, data)
                startActivity(moveIntent)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val moveIntent = Intent(this@MainActivity, AboutPage::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(STATE_RESULT, list)
        super.onSaveInstanceState(outState)
    }
}