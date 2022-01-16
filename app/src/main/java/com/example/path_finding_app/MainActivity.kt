package com.example.path_finding_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.path_finding_app.fragments.ChooseAlgorithm
import com.example.path_finding_app.fragments.Game
import com.example.path_finding_app.fragments.Summary
import com.example.path_finding_app.fragments.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var selectedAlgorithm: String = ""
    var selectedMode: String = ""
    var level = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpTabs()
    }

    fun alert(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .show()
    }

    fun setNewLevel(newLevel: Int) {
        level = newLevel
    }

    fun changeTab(position: Int) {
        viewPager.currentItem = position
    }

    fun setChoices(algorithm: String, mode: String) {
        selectedAlgorithm = algorithm
        selectedMode = mode
    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(ChooseAlgorithm(), "Algorithm")
        adapter.addFragment(Game(), "Game")
        adapter.addFragment(Summary(), "Summary")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_format_list_bulleted_24)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_videogame_asset_24)
        tabs.getTabAt(2)!!.setIcon(R.drawable.ic_baseline_star_outline_24)
    }
}