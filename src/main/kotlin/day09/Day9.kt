package day09

import utils.*
import kotlin.math.abs

private val operations = input.asSequence().map { it.split(" ") }.map { (a, b) -> a to b.toInt() }.toList()

fun main() {
    part1().println()
    part2().println()
}

private fun part1(): Int = List(2) {}.fold(null as Knot?) { knot, _ -> Knot(knot, Point(0, 0)) }!!.move().size

private fun part2(): Int = List(10) {}.fold(null as Knot?) { knot, _ -> Knot(knot, Point(0, 0)) }!!.move().size

private fun Knot.move(): Set<Point> = operations.flatMapSet { pair ->
    point.move(pair) {
        val lastTail = tail!!.follow(this)
        Point(lastTail.point.x, lastTail.point.y)
    }
}

private fun Point.move(instruction: Pair<String, Int>, onMoved: () -> Point) = (1..instruction.second).map {
    when (instruction.first) {
        "R" -> x++
        "L" -> x--
        "U" -> y++
        "D" -> y--
        else -> error("What is this?")
    }
    onMoved.invoke()
}

private fun Knot.follow(head: Knot): Knot {
    point.follow(head.point)
    return tail?.follow(this) ?: this
}

private fun Point.follow(head: Point) {
    when {
        abs(x - head.x) <= 1 && abs(y - head.y) <= 1 -> return
        y == head.y -> x += (head.x - x) / abs(head.x - x)
        x == head.x -> y += (head.y - y) / abs(head.y - y)
        else -> {
            x += (head.x - x) / abs(head.x - x)
            y += (head.y - y) / abs(head.y - y)
        }
    }
}

private data class Knot(
    val tail: Knot? = null,
    val point: Point,
)