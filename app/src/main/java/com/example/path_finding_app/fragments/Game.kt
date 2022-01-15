package com.example.path_finding_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.path_finding_app.MainActivity
import com.example.path_finding_app.R
import com.example.path_finding_app.fragments.levels.BestFirstSearch
import com.example.path_finding_app.fragments.shared.Node
import com.example.path_finding_app.fragments.shared.buildLevelBoard
import com.example.path_finding_app.fragments.shared.findButton
import com.example.path_finding_app.fragments.shared.printLevelBoard

class Game : Fragment() {

    protected lateinit var root: View
    protected var levelBoard: HashMap<String, Node> = hashMapOf()

    private fun setOnClickListeners (root: View) {
        for (y in 0..11) {
            for (x in 0..9) {
                val boardButton = findButton(root, x, y)
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
        root = inflater.inflate(R.layout.fragment_game, container, false)
        val levelLayout = BestFirstSearch(1).levelLayout

        buildLevelBoard(levelBoard, levelLayout)
        printLevelBoard(levelBoard, root)

        setOnClickListeners(root)
        return root
    }
}