package aoc2024.day01

fun part2(): Long {
    val input: Pair<List<Int>, List<Int>> = readInput1()
    val itemAppearance = input.second.itemAppearance()
    return input.first.fold(0) { sum, item ->
        sum + item * itemAppearance.getOrDefault(item, 0)
    }
}

private fun List<Int>.itemAppearance(): Map<Int, Int> {
    val map = mutableMapOf<Int, Int>()
    forEach {
        val currentCount = map[it]
        if (currentCount == null) {
            map[it] = 1
        } else {
            map[it] = currentCount + 1
        }
    }
    return map
}