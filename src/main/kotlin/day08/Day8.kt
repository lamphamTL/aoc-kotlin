package day08

fun main() {
    println(part1())
    println(part2())
}

private fun part1(): Int {
    val matrix: List<List<Int>> = input.map {
        it.map {
            it.toString().toInt()
        }
    }
    val (maxColumn, maxRow) = matrix.size to matrix[0].size
    var count = 0
    matrix.forEachIndexed column@{ indexColumn, row ->
        when (indexColumn) {
            0,
            maxColumn - 1,
            -> {
                count += maxRow
                return@column
            }
        }
        row.forEachIndexed row@{ indexRow, i ->
            when (indexRow) {
                0,
                maxRow - 1,
                -> {
                    count += 1
                    return@row
                }
            }
            val bottomVisible = ((indexColumn + 1) until maxColumn).all { bottomIndexColum ->
                matrix[bottomIndexColum][indexRow]< i
            }
            if (bottomVisible) {
                count++
                return@row
            }
            val topVisible = (0 until indexColumn).all { topIndexColum ->
                matrix[topIndexColum][indexRow] < i
            }
            if (topVisible) {
                count++
                return@row
            }
            val rightVisible = ((indexRow + 1) until maxRow).all { rightIndexRow ->
                matrix[indexColumn][rightIndexRow] < i
            }
            if (rightVisible) {
                count++
                return@row
            }
            val leftVisible = (0 until indexRow).all { leftIndexRow ->
                matrix[indexColumn][leftIndexRow] < i
            }
            if (leftVisible) {
                count++
                return@row
            }
        }
    }


    return count
}

private fun part2(): Int {
    val matrix: List<List<Int>> = input.map {
        it.map {
            it.toString().toInt()
        }
    }
    val (maxColumn, maxRow) = matrix.size to matrix[0].size
    var max = 0
    matrix.forEachIndexed column@{ indexColumn, row ->
        row.forEachIndexed row@{ indexRow, i ->
            val bottomCount = ((indexColumn + 1) until maxColumn)
                .indexOfFirst { bottomIndexColum ->
                    matrix[bottomIndexColum][indexRow] >= i
                }
                .takeIf { it != -1 }
                ?.plus(1) ?: (maxColumn - indexColumn - 1)
            val topCount = (-(indexColumn - 1)..0).indexOfFirst { topIndexColum ->
                matrix[-topIndexColum][indexRow] >= i
            }
                .takeIf { it != -1 }
                ?.plus(1) ?: indexColumn
            val rightCount = ((indexRow + 1) until maxRow).indexOfFirst { rightIndexRow ->
                matrix[indexColumn][rightIndexRow] >= i
            }
                .takeIf { it != -1 }
                ?.plus(1) ?: (maxRow - indexRow - 1)
            val leftCount = (-(indexRow - 1)..0).indexOfFirst { leftIndexRow ->
                matrix[indexColumn][-leftIndexRow] >= i
            }.takeIf { it != -1 }
                ?.plus(1) ?: indexRow
            val value = bottomCount * topCount * rightCount * leftCount
            if (value > max) {
                max = value
            }
        }
    }
    return max
}