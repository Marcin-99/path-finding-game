package com.example.path_finding_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.util.Log
import com.example.path_finding_app.MainActivity
import com.example.path_finding_app.R

class Game : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun setOnClickListeners (root: View) {
        for (y in 0..11) {
            for (x in 0..9) {
                val id = root.getResources().getIdentifier("node_${x}_${y}", "id", "com.example.path_finding_app")
                val boardButton = root.findViewById<View>(id)
                boardButton.setOnClickListener {
                    (activity as MainActivity).alert("button clicked", "x: ${x} | y: ${y}")
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_game, container, false)

        setOnClickListeners(root)

        return root
    }
}