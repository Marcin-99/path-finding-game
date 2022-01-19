package com.example.path_finding_app.fragments.algorithms

import com.example.path_finding_app.fragments.shared.LevelLayout
import com.example.path_finding_app.fragments.shared.Node

class BestFirst(levelBoard: HashMap<String, Node>, levelLayout: LevelLayout) {

    var levelBoard = levelBoard
    var levelLayout = levelLayout

    fun runAlgorithm(): Dijkstra.Path {
        // With this code base we can easly switch algorithms just with one if statement
        val dijkstra = Dijkstra(levelBoard, levelLayout)
        return dijkstra.runAlgorithm(true)
    }
}