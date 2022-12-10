package day10

import utils.println

fun main() {
    println(part1())
    println(part2())
}

private fun part1(): Int = with(getSignalMap()) {
    return getValue(20 - 1) * 20 +
            getValue(60 - 1) * 60 +
            getValue(100 - 1) * 100 +
            getValue(140 - 1) * 140 +
            getValue(180 - 1) * 180 +
            getValue(220 - 1) * 220
}

private fun part2(): Int {
    val signalMap = getSignalMap()
    val crt = MutableList(6) { MutableList(40) { "." } }
    (0 until 40 * 6).forEach { index ->
        val (column, row) = index / 40 to index % 40
        val spiritPosition = signalMap.getValue(index) -1
        if (row >= spiritPosition && row <= spiritPosition + 2 ) {
            crt[column][row] = "#"
        }
    }
    crt.forEach {
        it.joinToString(separator = "").println()
    }
    return 0
}

private fun getSignalMap(): Map<Int, Int> {
    var sum = 1
    return input.flatMap {
        "addx (-*\\d+)".toRegex().find(it)?.groupValues?.let { (_, value) -> listOf(0, value.toInt()) } ?: listOf(0)
    }.mapIndexed { index, value ->
        sum += value
        (index + 1) to sum
    }.toMap() + mapOf(0 to 1)
}