package day03

fun main() {
    println(part1())
    println(part2())
}

private fun part1(): Int {
    return input.sumOf { line ->
        val firstHalf = line.take(line.length / 2)
        val secondHaft = line.takeLast(line.length / 2)
        getFirstCharInCommon(listOf(firstHalf, secondHaft))
            .value()

    }
}

private fun part2(): Int {
    return input.chunked(3)
        .sumOf { list ->
            getFirstCharInCommon(list)
                .value()
        }
}

private fun getFirstCharInCommon(list: List<String>): Char? {
    val remainingList = list.drop(1)
    return list[0].firstOrNull { char ->
        remainingList.all { text ->
            text.contains(char)
        }
    }
}

private fun Char?.value() = lowerMap[this] ?: upperMap[this] ?: 0

private val lowerMap: Map<Char, Int> = mapOf(
    'a' to 1,
    'b' to 2,
    'c' to 3,
    'd' to 4,
    'e' to 5,
    'f' to 6,
    'g' to 7,
    'h' to 8,
    'i' to 9,
    'j' to 10,
    'k' to 11,
    'l' to 12,
    'm' to 13,
    'n' to 14,
    'o' to 15,
    'p' to 16,
    'q' to 17,
    'r' to 18,
    's' to 19,
    't' to 20,
    'u' to 21,
    'v' to 22,
    'w' to 23,
    'x' to 24,
    'y' to 25,
    'z' to 26,
)


private val upperMap: Map<Char, Int> = lowerMap
    .mapKeys {
        it.key.uppercase().first()
    }
    .mapValues {
        it.value + 26
    }