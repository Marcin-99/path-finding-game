package com.example.path_finding_app.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.path_finding_app.MainActivity
import com.example.path_finding_app.R
import com.example.path_finding_app.fragments.levels.BestFirstSearch
import com.example.path_finding_app.fragments.shared.*

class Game : Fragment() {

    protected lateinit var root: View
    protected var levelBoard: HashMap<String, Node> = hashMapOf()

    override fun onResume() {
        setStaticText((activity as MainActivity), root)

        val levelLayout = BestFirstSearch(1).levelLayout
        buildLevelBoard(levelBoard, levelLayout)
        printLevelBoard(levelBoard, root)

        setOnClickListeners(levelBoard, root)
        super.onResume()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_game, container, false)
        return root
    }
}