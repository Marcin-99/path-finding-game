package com.example.path_finding_app.fragments.shared

class Node(isStart: Boolean, isFinish: Boolean, isWall: Boolean) {
    var isStart = isStart
    var isFinish = isFinish
    var isWall = isWall

    var isSelected = false
}