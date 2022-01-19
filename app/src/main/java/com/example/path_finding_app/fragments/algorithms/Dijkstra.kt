package com.example.path_finding_app.fragments.algorithms

import com.example.path_finding_app.fragments.shared.LevelLayout
import com.example.path_finding_app.fragments.shared.Node

class Dijkstra(levelBoard: HashMap<String, Node>, levelLayout: LevelLayout) {

    var levelBoard = levelBoard
    var levelLayout = levelLayout

    inner class Index(x: Int, y: Int) {
        val x = x
        val y = y
    }

    inner class Neighbor(node: Node, parent: Node?, heuristic: Int) {
        var node = node
        var parent = parent
        var heuristic = heuristic
    }

    inner class Path(
        openPath: MutableList<Neighbor>,
        closedPath: MutableList<Neighbor>,
        shortestPath: MutableList<Neighbor>
    ) {
        var openPath = openPath
        var closedPath = closedPath
        var shortestPath = shortestPath
    }

    fun runAlgorithm(isBestFirst: Boolean = false): Path {
        var pathFound = false

        var currentNode = Neighbor(
            levelBoard["x${levelLayout.startNode[0]}y${levelLayout.startNode[1]}"] as Node,
            null,
            Int.MAX_VALUE
        )
        currentNode.node.isVisited = true

        var closedPath = arrayListOf<Neighbor>(currentNode)
        var openPath = getNeighbors(currentNode.node).sortedWith(compareBy({it.heuristic}))
        for (node in openPath) {
            node.node.isVisited = true
        }

        while (pathFound === false) {
            currentNode = openPath[0]

            if (currentNode.heuristic === 0) {
                pathFound = true
            }

            closedPath += currentNode
            openPath = openPath.drop(1)
            openPath += getNeighbors(currentNode.node)

            if (isBestFirst) {
                openPath = openPath.sortedWith(compareBy({it.heuristic}))
            }

            for (node in openPath) {
                node.node.isVisited = true
            }
        }

        val shortestPath = getShortestPath(closedPath)
        return Path(openPath as MutableList, closedPath as MutableList<Neighbor>, shortestPath)
    }

    private fun getShortestPath(closedPath: ArrayList<Neighbor>): MutableList<Neighbor> {
        var closedPathCopy = closedPath.toMutableList().asReversed()
        var currentNode = closedPathCopy[0]
        var shortestPath = mutableListOf<Neighbor>(currentNode)

        for (node in closedPathCopy) {
            if (currentNode.parent?.x === node.node.x && currentNode.parent?.y === node.node.y) {
                shortestPath.add(node)
                currentNode = node
            }
        }

        return shortestPath.asReversed()
    }

    private fun getNeighbors(node: Node): MutableList<Neighbor> {
        val indexes = arrayListOf<Index>()
        if (node.x > 0) {
            indexes += Index(-1, 0)
        }
        if (node.x < 9) {
            indexes += Index(1, 0)
        }
        if (node.y > 0) {
            indexes += Index(0, -1)
        }
        if (node.y < 11) {
            indexes += Index(0, 1)
        }

        val neighbors = mutableListOf<Neighbor>()
        for (index in indexes) {
            val neighborNode = levelBoard["x${node.x + index.x}y${node.y + index.y}"] as Node
            val parent = node
            val heuristic = getHeuristicValue(neighborNode)
            if (neighborNode.isWall === false && neighborNode.isVisited === false) {
                neighbors.add(Neighbor(neighborNode, parent, heuristic))
            }
        }

        return neighbors
    }

    private fun getHeuristicValue(node: Node): Int {
        val finish = levelLayout.finishNode
        val xDif = finish[0] - node.x
        val yDif = finish[1] - node.y
        return Math.abs(xDif) + Math.abs(yDif)
    }
}















