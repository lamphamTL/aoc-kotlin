package aoc2021.day25

class Node(
    var move: Move,
) {
    var rightNode: Node? = null
    var bottomNode: Node? = null

    override fun toString(): String {
        return "$move right = ${rightNode?.move} bottom=${bottomNode?.move}"
    }
}

fun Move.toNode() = Node(move = this)