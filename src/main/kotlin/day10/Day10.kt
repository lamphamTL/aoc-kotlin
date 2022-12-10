package day10

import utils.println

fun main() {
    println(part1())
    part2()
}

private fun part1(): Int = with(getSignalMap()) {
    get(20 - 1) * 20 + get(60 - 1) * 60 + get(100 - 1) * 100 + get(140 - 1) * 140 + get(180 - 1) * 180 + get(220 - 1) * 220
}

private fun part2(){
    val signalMap = getSignalMap()
    val crt = MutableList(6) { MutableList(40) { "." } }
    (0 until 40 * 6).forEach { index ->
        val (column, row) = index / 40 to index % 40
        val spiritPosition = signalMap[index] -1
        if (row >= spiritPosition && row <= spiritPosition + 2 ) {
            crt[column][row] = "#"
        }
    }
    crt.forEach { it.joinToString(separator = "").println() }
}

private fun getSignalMap(): List<Int> {
    var sum = 1
    return listOf(1) + input.flatMap {
        "addx (-*\\d+)".toRegex().find(it)?.groupValues?.let { (_, value) -> listOf(0, value.toInt()) } ?: listOf(0)
    }.map { value ->
        sum += value
        sum
    }
}