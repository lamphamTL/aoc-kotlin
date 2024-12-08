package aoc2022.day02

fun part2(): Int {
    return list.map {
        it.first.toReason() to it.second.toResultExpected()
    }
        .sumOf {
            it.second.value + play(it.second, it.first)
        }
}

fun play(result: Strategy, opponent: Reason): Int {
    // 1 -> 2 -> 3 -> 1
    return when (result) {
        Strategy.LOST -> (opponent.value % 3 + 1) % 3 + 1 // move back 1 step === move forward 2 steps
        Strategy.WIN -> opponent.value % 3 + 1 // move forward 1 step
        Strategy.DRAW -> opponent.value
    }
}