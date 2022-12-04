package day04

fun main() {
    println(part1())
    println(part2())
}

private fun part1(): Int {
    return makeInput().count {
        it[0] overlap it[1] || it[1] overlap it[0]
    }
}

private fun part2(): Int {
    return makeInput().count {
        it[0] overlapPartially it[1]
    }
}

infix fun Pair<Int, Int>.overlap(other: Pair<Int, Int>): Boolean {
    return first <= other.first && second >= other.second
}

infix fun Pair<Int, Int>.overlapPartially(other: Pair<Int, Int>): Boolean {
    return first <= other.second && second >= other.first
}

private fun makeInput(): List<List<Pair<Int, Int>>> = input.map {
    it.split(",")
        .map {
            it.split("-").run {
                get(0).toInt() to get(1).toInt()
            }

        }
}
