package aoc2024.day03

fun part1(): Int {
    val input = readInput()
    return "mul\\(\\d+,\\d+\\)".toRegex().findAll(input)
        .sumOf {
            multiple(it.value)
        }
}

private fun multiple(str: String): Int =
    "mul\\((\\d+),(\\d+)\\)".toRegex().matchEntire(str)?.groupValues?.let {
        it[1].toInt() * it[2].toInt()
    } ?: 0