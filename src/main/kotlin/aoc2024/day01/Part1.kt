package aoc2024.day01

import kotlin.math.abs

fun part1(): Long {
    val input: Pair<List<Int>, List<Int>> = readInput1()
    val sortedList1 = input.first.sorted()
    val sortedList2 = input.second.sorted()
    return sortedList1.foldIndexed(0) { index, sum, item ->
        sum + abs(sortedList2[index] - item)
    }
}