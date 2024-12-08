package aoc2024.day02

import kotlin.math.abs

fun part1(): Int = readInput2().count { it.isSafe() }

private fun List<Int>.isSafe(): Boolean {
    val trend = this[1] - this[0]
    for (i in 0 until (size - 1)) {
        val currentItem = this[i]
        val nextItem = this[i + 1]
        val diff = nextItem - currentItem
        if (diff * trend < 0) {
            return false
        }
        if (abs(diff) > 3 || abs(diff) < 1) {
            return false
        }
    }
    return true
}