package com.example.path_finding_app.fragments.shared

import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.example.path_finding_app.MainActivity
import com.example.path_finding_app.R

fun buildLevelBoard(levelBoard: HashMap<String, Node>, levelLayout: LevelLayout) {
    for (y in 0..11) {
        for (x in 0..9) {
            if (isWall(levelLayout.walls, x, y)) {
                levelBoard.put("x${x}y${y}", Node(x, y, false, false, true))
            }
            else if (levelLayout.startNode[0] === x && levelLayout.startNode[1] === y) {
                levelBoard.put("x${x}y${y}", Node(x, y, true, false, false))
            }
            else if (levelLayout.finishNode[0] === x && levelLayout.finishNode[1] === y) {
                levelBoard.put("x${x}y${y}", Node(x, y, false, true, false))
            }
            else {
                levelBoard.put("x${x}y${y}", Node(x, y, false, false, false))
            }
        }
    }
}

fun isWall(walls: Array<IntArray>, x: Int, y: Int): Boolean {
    for (wall in walls) {
        if (wall[0] === x && wall[1] === y) {
            return true
        }
    }
    return false
}

fun findButton (root: View, x: Int, y: Int): View {
    val id = root.getResources().getIdentifier("node_${x}_${y}", "id", "com.example.path_finding_app")
    return root.findViewById<View>(id)
}

fun printLevelBoard(levelBoard: HashMap<String, Node>, root: View) {
    for (y in 0..11) {
        for (x in 0..9) {
            val boardButton = findButton(root, x, y)
            val boardNode = levelBoard["x${x}y${y}"]
            if (boardNode?.isStart === true) {
                boardButton.setBackgroundColor(Color.parseColor("#339933"))
            }
            else if (boardNode?.isFinish === true) {
                boardButton.setBackgroundColor(Color.parseColor("#e60000"))
            }
            else if (boardNode?.isSelected === true) {
                boardButton.setBackgroundColor(Color.parseColor("#ccebff"))
            }
            else if (boardNode?.isWall === true) {
                boardButton.setBackgroundColor(Color.parseColor("#072227"))
            }
            else {
                boardButton.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
        }
    }
}

fun setCommonOnClickListeners (activity: MainActivity, levelBoard: HashMap<String, Node>, root: View) {
    for (y in 0..11) {
        for (x in 0..9) {
            val boardButton = findButton(root, x, y)
            boardButton.setOnClickListener {
                val node = levelBoard["x${x}y${y}"]
                if (activity.selectedMode === "sandbox" && node !== null) {
                    node.isWall = !node.isWall
                }
                else if (node?.isStart === false && node?.isFinish === false && node?.isWall === false) {
                    node.isSelected = !node.isSelected
                }
                printLevelBoard(levelBoard, root)
            }
        }
    }
}

fun setStaticText(activity: MainActivity, root: View) {
    val algorithmDisplayText: TextView = root.findViewById<View>(R.id.algorithmDisplayText) as TextView
    algorithmDisplayText.text = activity.selectedAlgorithm

    val levelDisplayText: TextView = root.findViewById<View>(R.id.levelDisplayText) as TextView
    levelDisplayText.text = activity.level.toString()

    val modeDisplayText: TextView = root.findViewById<View>(R.id.modeDisplayText) as TextView
    modeDisplayText.text = activity.selectedMode

    val scoreDisplayText: TextView = root.findViewById<View>(R.id.scoreDisplayText) as TextView
    scoreDisplayText.text = activity.score.toString()
}

fun incrementScore(activity: MainActivity, root: View, increment: Int) {
    activity.incrementScore(increment)
    val scoreDisplayText: TextView = root.findViewById<View>(R.id.scoreDisplayText) as TextView
    scoreDisplayText.text = (activity as MainActivity).score.toString()
}
