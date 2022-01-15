package com.example.path_finding_app.fragments.levels

class BestFirstSearch(level: Int) {

    lateinit var levelLayout: LevelLayout

    inner class LevelLayout (startNode: IntArray, finishNode: IntArray, walls: Array<IntArray>) {
        var startNode = startNode
        var finishNode = finishNode
        var walls = walls
    }

    init {
        when(level) {
            1 -> levelLayout = LevelLayout(
                intArrayOf(0, 0),
                intArrayOf(9, 11),
                arrayOf(
                    intArrayOf(9, 0),
                    intArrayOf(8, 1),
                    intArrayOf(7, 2),
                    intArrayOf(6, 3),
                    intArrayOf(5, 4),
                    intArrayOf(4, 5),
                    intArrayOf(3, 6),
                    intArrayOf(2, 7),
                    intArrayOf(1, 8)
                )
            )
        }
    }
}
