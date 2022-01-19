package com.example.path_finding_app.fragments.visualiser

import com.example.path_finding_app.fragments.algorithms.Dijkstra
import com.example.path_finding_app.fragments.shared.Node

fun isInShortestPath(node: Node, path: Dijkstra.Path): Boolean {
    for (shortPathNode in path.shortestPath) {
        if (shortPathNode.node.x === node.x && shortPathNode.node.y === node.y) {
            return true
        }
    }
    return false
}