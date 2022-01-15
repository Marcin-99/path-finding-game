package com.example.path_finding_app.fragments

import android.content.res.AssetManager
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.util.Log
import androidx.core.content.ContextCompat
import com.beust.klaxon.Klaxon
import com.example.path_finding_app.MainActivity
import com.example.path_finding_app.R
import com.example.path_finding_app.fragments.levels.BestFirstSearch
import com.example.path_finding_app.fragments.utils.Node
import java.io.File
import java.io.FileReader

class Game : Fragment() {

    protected lateinit var root: View
    protected var levelBoard: HashMap<String, Node> = hashMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun findButton (x: Int, y: Int): View {
        val id = root.getResources().getIdentifier("node_${x}_${y}", "id", "com.example.path_finding_app")
        return root.findViewById<View>(id)
    }

    private fun setOnClickListeners () {
        for (y in 0..11) {
            for (x in 0..9) {
                val boardButton = findButton(x, y)
                boardButton.setOnClickListener {
                    (activity as MainActivity).alert("button clicked", "x: ${x} | y: ${y}")
                }
            }
        }
    }

    private fun isWall(walls: Array<IntArray>, x: Int, y: Int): Boolean {
        for (wall in walls) {
            if (wall[0] === x && wall[1] === y) {
                return true
            }
        }
        return false
    }

    private fun buildLevelBoard(levelLayout: BestFirstSearch.LevelLayout) {
        for (y in 0..11) {
            for (x in 0..9) {
                if (isWall(levelLayout.walls, x, y)) {
                    levelBoard.put("x${x}y${y}", Node(false, false, true))
                }
                else if (levelLayout.startNode[0] === x && levelLayout.startNode[1] === y) {
                    levelBoard.put("x${x}y${y}", Node(true, false, false))
                }
                else if (levelLayout.finishNode[0] === x && levelLayout.finishNode[1] === y) {
                    levelBoard.put("x${x}y${y}", Node(false, true, false))
                }
                else {
                    levelBoard.put("x${x}y${y}", Node(false, false, false))
                }
            }
        }
    }

    private fun printLevelBoard() {
        for (y in 0..11) {
            for (x in 0..9) {
                val boardButton = findButton(x, y)
                val boardNode = levelBoard["x${x}y${y}"]
                if (boardNode?.isStart === true) {
                    boardButton.setBackgroundColor(Color.parseColor("#339933"))
                }
                else if (boardNode?.isFinish === true) {
                    boardButton.setBackgroundColor(Color.parseColor("#ff6666"))
                }
                else if (boardNode?.isWall === true) {
                    boardButton.setBackgroundColor(Color.parseColor("#072227"))
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

        buildLevelBoard(levelLayout)
        printLevelBoard()
        setOnClickListeners()

        return root
    }
}