package aoc2024.day04

fun part1(): Int {
    val input: List<List<Char>> = readInput()
    return input.countVerticalXmas() +
            input.countHorizontalXmas() +
            input.countLRDiagonalXmas() +
            input.countRLDiagonalXmas()
}

private fun List<List<Char>>.countVerticalXmas(): Int {
    var count = 0
    val countRow: Int = first().size
    for (column in 0..(size - 4)) {
        for (row in 0 until countRow) {
            when {
                get(column)[row] == 'X' &&
                        get(column + 1)[row] == 'M' &&
                        get(column + 2)[row] == 'A' &&
                        get(column + 3)[row] == 'S' -> count++

                get(column + 3)[row] == 'X' &&
                        get(column + 2)[row] == 'M' &&
                        get(column + 1)[row] == 'A' &&
                        get(column)[row] == 'S' -> count++
            }

        }
    }
    return count
}

private fun List<List<Char>>.countHorizontalXmas(): Int {
    var count = 0
    val countRow: Int = first().size
    for (column in indices) {
        for (row in 0..(countRow - 4)) {
            when {
                get(column)[row] == 'X' &&
                        get(column)[row + 1] == 'M' &&
                        get(column)[row + 2] == 'A' &&
                        get(column)[row + 3] == 'S' -> count++

                get(column)[row + 3] == 'X' &&
                        get(column)[row + 2] == 'M' &&
                        get(column)[row + 1] == 'A' &&
                        get(column)[row] == 'S' -> count++
            }

        }
    }
    return count
}

private fun List<List<Char>>.countLRDiagonalXmas(): Int {
    var count = 0
    val countRow: Int = first().size
    for (column in 0..(size - 4)) {
        for (row in 0..(countRow - 4)) {
            when {
                get(column)[row] == 'X' &&
                        get(column + 1)[row + 1] == 'M' &&
                        get(column + 2)[row + 2] == 'A' &&
                        get(column + 3)[row + 3] == 'S' -> count++
                get(column + 3)[row + 3] == 'X' &&
                        get(column + 2)[row + 2] == 'M' &&
                        get(column + 1)[row + 1] == 'A' &&
                        get(column)[row] == 'S' -> count++
            }

        }
    }
    return count
}

private fun List<List<Char>>.countRLDiagonalXmas(): Int {
    var count = 0
    val countRow: Int = first().size
    for (column in 0..(size - 4)) {
        for (row in 3 until countRow) {
            when {
                get(column)[row] == 'X' &&
                        get(column + 1)[row - 1] == 'M' &&
                        get(column + 2)[row - 2] == 'A' &&
                        get(column + 3)[row - 3] == 'S' -> count++
                get(column + 3)[row - 3] == 'X' &&
                        get(column + 2)[row - 2] == 'M' &&
                        get(column + 1)[row - 1] == 'A' &&
                        get(column)[row] == 'S' -> count++
            }

        }
    }
    return count
}
