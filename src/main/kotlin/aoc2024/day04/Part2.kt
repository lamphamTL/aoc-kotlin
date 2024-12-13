package aoc2024.day04

fun part2(): Int {
    val input: List<List<Char>> = readInput()
    return input.countXMas()
}

private fun List<List<Char>>.countXMas(): Int {
    var count = 0
    val countRow: Int = first().size
    for (column in 0..(size - 3)) {
        for (row in 0..(countRow - 3)) {
            when {
                get(column + 1)[row + 1] == 'A' &&
                        "${get(column)[row]}${get(column + 2)[row + 2]}" in listOf("MS", "SM") &&
                        "${get(column)[row + 2]}${get(column + 2)[row]}" in listOf("MS", "SM")
                    -> count++
            }
        }
    }
    return count
}