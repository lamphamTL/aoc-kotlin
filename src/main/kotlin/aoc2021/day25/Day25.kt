package aoc2021.day25

import utils.windowedClose

enum class Move(val value: String) {
    Right(">"),
    Down("v"),
    Blank(".");

    override fun toString() = value
}

fun Char.toMove() = when (this) {
    '>' -> Move.Right
    'v' -> Move.Down
    '.' -> Move.Blank
    else -> error("What is this?")
}


fun main() {
    val matrix = makeInput()
    var count = 0
    var stop = false
    while (!stop) {
        val listToMoveRight = mutableListOf<Node>()
        val listToMoveBottom = mutableListOf<Node>()
        matrix.forEach {
            it.forEach { node ->
                if (node.rightNode?.move == Move.Blank && node.move == Move.Right) {
                    listToMoveRight.add(node)
                }
            }
        }
        listToMoveRight.forEach { it.moveRight() }
        matrix.forEach {
            it.forEach { node ->
                if (node.bottomNode?.move == Move.Blank && node.move == Move.Down) {
                    listToMoveBottom.add(node)
                }
            }
        }
        listToMoveBottom.forEach { it.moveBottom() }
        if (listToMoveRight.isNotEmpty() || listToMoveBottom.isNotEmpty()) {
            count++
        } else {
            stop = true
        }
    }
    println(count)
}

private fun Node.moveRight() {
    rightNode?.move = move
    move = Move.Blank
}

private fun Node.moveBottom() {
    bottomNode?.move = move
    move = Move.Blank
}

fun makeInput(): List<List<Node>> {

    fun List<List<Node>>.horizontalMatrix(): List<List<Node>> = map {
        it.windowedClose(2, 1) {
            it[0].rightNode = it[1]
            it[0]
        }
    }

    fun List<List<Node>>.verticalMatrix() {
        zipList(this) {
            it.windowedClose(2, 1) {
                it[0].bottomNode = it[1]
                it[0]
            }
        }
    }

    return input.map {
        it.map {
            it.toMove().toNode()
        }
    }.horizontalMatrix().apply {
        verticalMatrix()
    }
}

inline fun <T, V> zipList(matrix: List<List<T>>, transform: (List<T>) -> V) {
    matrix.first().indices.forEachIndexed { index, i ->
        matrix.map {
            it[index]
        }.let(transform)
    }
}