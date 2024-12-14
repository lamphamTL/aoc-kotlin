package aoc2024.day05

fun part1(): Int {
    val (ruleList: Map<Int, List<Int>>, updateList: List<List<Int>>) = readInput()
    return updateList.sumOf {
        if (it.respectRule(ruleList)) it[it.size / 2]
        else 0
    }
}

private fun List<Int>.respectRule(rules: Map<Int, List<Int>>): Boolean {
    for (i in 0 until (size - 1)) {
        for (j in (i + 1) until size) {
            if (!(get(i) to get(j)).respectRule(rules))
                return false
        }
    }
    return true
}

private fun Pair<Int, Int>.respectRule(rules: Map<Int, List<Int>>): Boolean =
    (rules[first]?.contains(second) == true)