package com.example.path_finding_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.path_finding_app.MainActivity
import com.example.path_finding_app.R
import com.example.path_finding_app.fragments.algorithms.BestFirst
import com.example.path_finding_app.fragments.algorithms.Dijkstra
import com.example.path_finding_app.fragments.levels.BestFirstLevels
import com.example.path_finding_app.fragments.levels.DijkstraLevels
import com.example.path_finding_app.fragments.shared.*
import com.example.path_finding_app.fragments.visualiser.Visualiser
import kotlin.collections.HashMap

class Game : Fragment() {

    protected lateinit var root: View
    protected var levelBoard: HashMap<String, Node> = hashMapOf()
    protected lateinit var levelLayout: LevelLayout
    protected lateinit var visualiser: Visualiser

    fun setSpecificOnClickListeners(selectedAlgorithm: String) {
        val nextLevelButton = root.findViewById<View>(R.id.nextLevelButton)
        nextLevelButton.setOnClickListener {
            if ((activity as MainActivity).level === 5) {
                (activity as MainActivity).setIsGameFinished()
                (activity as MainActivity).changeTab(2)
            }
            else {
                (activity as MainActivity).incrementLevel()
                setupLevel()
            }
        }

        val checkResultButton = root.findViewById<View>(R.id.checkResultButton)
        checkResultButton.setOnClickListener {
            if (selectedAlgorithm === "dijkstra") {
                val algorithm = Dijkstra(levelBoard, levelLayout)
                val path = algorithm.runAlgorithm()
                visualiser.animatePath(path)
                incrementScore((activity as MainActivity), root, 2000)
            }
            else if (selectedAlgorithm === "best-first") {
                val algorithm = BestFirst(levelBoard, levelLayout)
                val path = algorithm.runAlgorithm()
                visualiser.animatePath(path)
                incrementScore((activity as MainActivity), root, 2000)
            }
        }
    }

    fun setupLevel() {
        val selectedAlgorithm = (activity as MainActivity).selectedAlgorithm
        val level = (activity as MainActivity).level
        levelBoard = hashMapOf()
        val nextLevelButton = root.findViewById<View>(R.id.nextLevelButton)
        nextLevelButton.visibility = View.GONE

        if (selectedAlgorithm === "dijkstra") {
            levelLayout = DijkstraLevels(level).levelLayout
        }
        else if (selectedAlgorithm === "best-first") {
            levelLayout = BestFirstLevels(level).levelLayout
        }

        levelBoard = hashMapOf()
        visualiser = Visualiser(root, levelBoard, (activity as MainActivity))

        buildLevelBoard(levelBoard, levelLayout)
        printLevelBoard(levelBoard, root)

        setCommonOnClickListeners(activity as MainActivity, levelBoard, root)
        setSpecificOnClickListeners(selectedAlgorithm)

        setStaticText((activity as MainActivity), root)
    }

    override fun onResume() {
        (activity as MainActivity).setNewLevel(1)
        (activity as MainActivity).setNewScore(0)
        setupLevel()
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