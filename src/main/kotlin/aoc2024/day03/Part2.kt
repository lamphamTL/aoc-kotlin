package aoc2024.day03

fun part2(): Int {
    val input = readInput()
    // use non-greedy *? instead of greedy * option
    val doInstruction = input.replace(Regex("don't\\(\\).*?(do\\(\\)|$)", RegexOption.DOT_MATCHES_ALL), "do()")
    return "mul\\(\\d+,\\d+\\)".toRegex().findAll(doInstruction)
        .sumOf {
            multiple(it.value)
        }
}

private fun multiple(str: String): Int =
    "mul\\((\\d+),(\\d+)\\)".toRegex().matchEntire(str)?.groupValues?.let {
        it[1].toInt() * it[2].toInt()
    } ?: 0