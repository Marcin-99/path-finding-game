package com.example.path_finding_app.fragments.shared

class Node(x: Int, y: Int, isStart: Boolean, isFinish: Boolean, isWall: Boolean) {
    var x = x
    var y = y

    var isStart = isStart
    var isFinish = isFinish
    var isWall = isWall

    var isSelected = false
    var isVisited = false
}