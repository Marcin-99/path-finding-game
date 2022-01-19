package com.example.path_finding_app.fragments

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.path_finding_app.MainActivity
import com.example.path_finding_app.R
import com.example.path_finding_app.fragments.algorithms.BestFirst
import com.example.path_finding_app.fragments.algorithms.Dijkstra
import com.example.path_finding_app.fragments.levels.BestFirstLevels
import com.example.path_finding_app.fragments.levels.DijkstraLevels
import com.example.path_finding_app.fragments.shared.*
import kotlin.collections.HashMap

class Game : Fragment() {

    protected lateinit var root: View
    protected var levelBoard: HashMap<String, Node> = hashMapOf()
    protected lateinit var levelLayout: LevelLayout

    fun animatePath(path: Dijkstra.Path) {
        for (i in 0..path.closedPath.size - 1) {
            if (i === 0 || i === path.closedPath.size - 1) {
                continue
            }
            val timeInterval = (i*50).toLong()
            if (i === path.closedPath.size - 2) {
                Handler().postDelayed({
                    changeColorsOfNodesWithShortestPath(path)
                }, timeInterval)
            }

            Handler().postDelayed({
                val node = path.closedPath[i].node
                val nodeToPrint = findButton(root, node.x, node.y)
                nodeToPrint.setBackgroundColor(Color.parseColor("#ffffcc"))
            }, timeInterval)
        }
    }

    fun isInShortestPath(node: Node, path: Dijkstra.Path): Boolean {
        for (shortPathNode in path.shortestPath) {
            if (shortPathNode.node.x === node.x && shortPathNode.node.y === node.y) {
                return true
            }
        }
        return false
    }

    fun revertClosedPathColor(path: Dijkstra.Path) {
        for (i in path.closedPath.size - 1 downTo 0) {
            val timeInterval = ((path.closedPath.size - i)*25).toLong()
            Handler().postDelayed({
                val node = path.closedPath[i].node
                val nodeToPrint = findButton(root, node.x, node.y)
                if (isInShortestPath(node, path) === false) {
                    nodeToPrint.setBackgroundColor(Color.parseColor("#FFFFFF"))
                }
            }, timeInterval)
        }

        val delay = (path.closedPath.size*25).toLong()
        Handler().postDelayed({
            revertSelectedNodesColor(path)
        }, delay)
    }

    fun changeColorsOfNodesWithShortestPath(path: Dijkstra.Path) {
        for (i in 0..path.shortestPath.size - 1) {
            val timeInterval = (i*50).toLong()
            Handler().postDelayed({
                val node = path.shortestPath[i].node
                val nodeToPrint = findButton(root, node.x, node.y)
                val boardNode = levelBoard["x${node.x}y${node.y}"]
                if (boardNode?.isStart === false && boardNode?.isFinish === false) {
                    nodeToPrint.setBackgroundColor(Color.parseColor("#3399ff"))
                }
            }, timeInterval)
        }

        Handler().postDelayed({
            revertClosedPathColor(path)
        }, 1000)
    }

    fun revertSelectedNodesColor(path: Dijkstra.Path) {
        val selectedPath = arrayListOf<Node>()
        for ((_, node) in levelBoard) {
            if (node.isSelected) {
                selectedPath += node
            }
        }

        var index = 0
        for (selectedNode in selectedPath) {
            val timeInterval = (index*50).toLong()
            Handler().postDelayed({
                val nodeToPrint = findButton(root, selectedNode.x, selectedNode.y)
                if (isInShortestPath(selectedNode, path) === false) {
                    incrementScore(-100)
                    nodeToPrint.setBackgroundColor(Color.parseColor("#ff6666"))
                }
            }, timeInterval)
            index ++
        }

        val delay = (selectedPath.size*25).toLong()
        Handler().postDelayed({
            changeColorsOfNotSelected(path)
        }, delay)
    }

    fun changeColorsOfNotSelected(path: Dijkstra.Path) {
        for (i in path.shortestPath.size - 1 downTo 0) {
            val timeInterval = ((path.shortestPath.size - i)*50).toLong()
            Handler().postDelayed({
                val node = path.shortestPath[i].node
                val nodeToPrint = findButton(root, node.x, node.y)
                val boardNode = levelBoard["x${node.x}y${node.y}"]
                if (boardNode?.isSelected === false && boardNode?.isStart === false && boardNode?.isFinish === false) {
                    incrementScore(-100)
                    nodeToPrint.setBackgroundColor(Color.parseColor("#cccccc"))
                }
            }, timeInterval)
        }

        val nextLevelButton = root.findViewById<View>(R.id.nextLevelButton)
        if ((activity as MainActivity).level === 5) {
            (nextLevelButton as TextView).text = "Summary"
        }
        if ((activity as MainActivity).selectedMode === "competetive") {
            nextLevelButton.visibility = View.VISIBLE
        }
    }

    fun incrementScore(increment: Int) {
        (activity as MainActivity).incrementScore(increment)
        val scoreDisplayText: TextView = root.findViewById<View>(R.id.scoreDisplayText) as TextView
        scoreDisplayText.text = (activity as MainActivity).score.toString()
    }

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
                animatePath(path)
                incrementScore(2000)
            }
            else if (selectedAlgorithm === "best-first") {
                val algorithm = BestFirst(levelBoard, levelLayout)
                val path = algorithm.runAlgorithm()
                animatePath(path)
                incrementScore(2000)
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