package com.example.path_finding_app.fragments.visualiser

import android.graphics.Color
import android.os.Handler
import android.view.View
import android.widget.TextView
import com.example.path_finding_app.MainActivity
import com.example.path_finding_app.R
import com.example.path_finding_app.fragments.algorithms.Dijkstra
import com.example.path_finding_app.fragments.shared.Node
import com.example.path_finding_app.fragments.shared.findButton
import com.example.path_finding_app.fragments.shared.incrementScore

class Visualiser(root: View, levelBoard: HashMap<String, Node>, activity: MainActivity) {

    var root = root
    var levelBoard = levelBoard
    var activity = activity

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

    fun changeColorsOfNotSelected(path: Dijkstra.Path) {
        for (i in path.shortestPath.size - 1 downTo 0) {
            val timeInterval = ((path.shortestPath.size - i)*50).toLong()
            Handler().postDelayed({
                val node = path.shortestPath[i].node
                val nodeToPrint = findButton(root, node.x, node.y)
                val boardNode = levelBoard["x${node.x}y${node.y}"]
                if (boardNode?.isSelected === false && boardNode?.isStart === false && boardNode?.isFinish === false) {
                    incrementScore(activity, root, -100)
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
                    incrementScore(activity, root, -100)
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
}