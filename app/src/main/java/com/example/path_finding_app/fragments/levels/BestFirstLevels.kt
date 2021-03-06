package com.example.path_finding_app.fragments.levels

import com.example.path_finding_app.fragments.shared.LevelLayout

class BestFirstLevels(level: Int) {

    lateinit var levelLayout: LevelLayout

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
            2 -> levelLayout = LevelLayout(
                intArrayOf(7, 0),
                intArrayOf(2, 11),
                arrayOf(
                    intArrayOf(9, 0),
                    intArrayOf(8, 1),
                    intArrayOf(7, 2),
                    intArrayOf(6, 3),
                    intArrayOf(5, 4),
                    intArrayOf(4, 5),
                    intArrayOf(3, 6),
                    intArrayOf(2, 7),
                    intArrayOf(1, 8),
                    intArrayOf(0, 0),
                    intArrayOf(1, 1),
                    intArrayOf(2, 2),
                    intArrayOf(3, 3),
                    intArrayOf(1, 11),
                    intArrayOf(2, 10),
                    intArrayOf(3, 9),
                    intArrayOf(4, 8),
                    intArrayOf(5, 7),
                    intArrayOf(6, 6),
                )
            )
            3 -> levelLayout = LevelLayout(
                intArrayOf(3, 5),
                intArrayOf(5, 5),
                arrayOf(
                    intArrayOf(4, 1),
                    intArrayOf(4, 2),
                    intArrayOf(4, 3),
                    intArrayOf(4, 4),
                    intArrayOf(4, 5),
                    intArrayOf(4, 6),
                    intArrayOf(4, 7),
                    intArrayOf(4, 8),
                    intArrayOf(4, 9),
                    intArrayOf(4, 10),
                    intArrayOf(5, 3),
                    intArrayOf(6, 3),
                    intArrayOf(7, 3),
                    intArrayOf(8, 3),
                    intArrayOf(6, 8),
                    intArrayOf(7, 8),
                    intArrayOf(8, 8),
                    intArrayOf(9, 8),
                    intArrayOf(5, 6),
                    intArrayOf(6, 6),
                    intArrayOf(7, 6),
                    intArrayOf(8, 6),
                    intArrayOf(3, 4),
                    intArrayOf(2, 4),
                    intArrayOf(1, 4),
                    intArrayOf(3, 6),
                    intArrayOf(2, 6),
                    intArrayOf(1, 6),
                    intArrayOf(2, 8),
                    intArrayOf(2, 9),
                    intArrayOf(2, 10),
                    intArrayOf(2, 11),
                    intArrayOf(2, 0),
                    intArrayOf(2, 1),
                    intArrayOf(2, 2),
                    intArrayOf(8, 1),
                    intArrayOf(8, 2),
                )
            )
            4 -> levelLayout = LevelLayout(
                intArrayOf(9, 11),
                intArrayOf(0, 0),
                arrayOf(
                    intArrayOf(8, 1),
                    intArrayOf(6, 3),
                    intArrayOf(4, 5),
                    intArrayOf(2, 7),
                    intArrayOf(0, 9),
                    intArrayOf(4, 1),
                    intArrayOf(2, 3),
                    intArrayOf(0, 5),
                    intArrayOf(6, 0),
                    intArrayOf(4, 2),
                    intArrayOf(2, 4),
                    intArrayOf(0, 6),
                    intArrayOf(6, 1),
                    intArrayOf(4, 3),
                    intArrayOf(2, 5),
                    intArrayOf(0, 7),
                    intArrayOf(6, 5),
                    intArrayOf(7, 5),
                    intArrayOf(8, 5),
                    intArrayOf(4, 7),
                    intArrayOf(5, 7),
                    intArrayOf(6, 7),
                    intArrayOf(8, 8),
                    intArrayOf(8, 9),
                    intArrayOf(8, 10),
                )
            )
            5 -> levelLayout = LevelLayout(
                intArrayOf(9, 5),
                intArrayOf(0, 5),
                arrayOf(
                    intArrayOf(4, 3),
                    intArrayOf(4, 6),
                    intArrayOf(1, 0),
                    intArrayOf(1, 2),
                    intArrayOf(1, 3),
                    intArrayOf(1, 4),
                    intArrayOf(1, 5),
                    intArrayOf(1, 6),
                    intArrayOf(1, 8),
                    intArrayOf(1, 9),
                    intArrayOf(1, 10),
                    intArrayOf(1, 11),
                    intArrayOf(3, 0),
                    intArrayOf(3, 1),
                    intArrayOf(3, 3),
                    intArrayOf(3, 4),
                    intArrayOf(3, 6),
                    intArrayOf(3, 7),
                    intArrayOf(3, 8),
                    intArrayOf(3, 9),
                    intArrayOf(3, 10),
                    intArrayOf(3, 11),
                    intArrayOf(6, 0),
                    intArrayOf(6, 1),
                    intArrayOf(6, 2),
                    intArrayOf(6, 4),
                    intArrayOf(6, 5),
                    intArrayOf(6, 6),
                    intArrayOf(6, 7),
                    intArrayOf(6, 8),
                    intArrayOf(6, 9),
                    intArrayOf(6, 11),
                    intArrayOf(8, 0),
                    intArrayOf(8, 2),
                    intArrayOf(8, 3),
                    intArrayOf(8, 4),
                    intArrayOf(8, 5),
                    intArrayOf(8, 6),
                    intArrayOf(8, 7),
                    intArrayOf(8, 8),
                    intArrayOf(8, 9),
                    intArrayOf(8, 11),
                )
            )
        }
    }
}
