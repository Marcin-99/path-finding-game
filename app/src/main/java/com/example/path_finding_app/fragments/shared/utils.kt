package com.example.path_finding_app.fragments.shared

import android.graphics.Color
import android.view.View
import com.example.path_finding_app.fragments.levels.BestFirstSearch

fun buildLevelBoard(levelBoard: HashMap<String, Node>, levelLayout: BestFirstSearch.LevelLayout) {
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
                boardButton.setBackgroundColor(Color.parseColor("#ff6666"))
            }
            else if (boardNode?.isWall === true) {
                boardButton.setBackgroundColor(Color.parseColor("#072227"))
            }
        }
    }
}
