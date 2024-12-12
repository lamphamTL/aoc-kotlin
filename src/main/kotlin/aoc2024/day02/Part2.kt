package aoc2024.day02

import kotlin.math.abs

fun part2(): Int = readInput2().count { it.bruteForceIsSafe() }

private fun List<Int>.bruteForceIsSafe(): Boolean {
    if (isCompletelySafe()) {
        return true
    }
    return 0.rangeTo(size).any {
        this.filterIndexed { index, _ -> index != it }.isCompletelySafe()
    }
}

private fun List<Int>.isCompletelySafe(): Boolean {
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