package aoc2024.day05

fun part2(): Int {
    val (ruleList: Map<Int, List<Int>>, updateList: List<List<Int>>) = readInput()
    return updateList.sumOf { list ->
        list.adjustFollowingRules(ruleList).let {
            if (it.isEmpty()) {
                0
            } else
                it[it.size / 2]
        }
    }
}

private fun List<Int>.adjustFollowingRules(rules: Map<Int, List<Int>>): List<Int> =
    (this to true).adjustFollowingRules(rules).let {
        if(!it.second)
            it.first
        else
            emptyList()
    }

private fun Pair<List<Int>, Boolean>.adjustFollowingRules(rules: Map<Int, List<Int>>): Pair<List<Int>, Boolean> {
    for (i in 0 until (first.size - 1)) {
        for (j in (i + 1) until first.size) {
            if (!(first[i] to first[j]).respectRule(rules)) {
                if (i == 0) {
                    return (first.moveItem(j, 0) to false).adjustFollowingRules(rules)
                }
                return ((first.subList(0, i) + (first.moveItem(j, i).drop(i)) to false).adjustFollowingRules(rules))
            }
        }
    }
    return this
}

private fun Pair<Int, Int>.respectRule(rules: Map<Int, List<Int>>): Boolean =
    (rules[first]?.contains(second) == true)

private fun List<Int>.moveItem(index: Int, moveBackToIndex: Int): List<Int> = toMutableList().apply {
    val itemToMove = get(index)
    removeAt(index)
    add(moveBackToIndex, itemToMove)
}