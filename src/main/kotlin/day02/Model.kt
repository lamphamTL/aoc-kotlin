package day02

// 1 -> 2 -> 3 -> 1
enum class Reason(val value: Int) {
    ROCK(1),
    Paper(2),
    Scissors(3),
}

enum class Strategy(val value: Int) {
    LOST(0),
    DRAW(3),
    WIN(6),
}

fun String.toReason(): Reason = when (this) {
    "A" -> Reason.ROCK
    "B" -> Reason.Paper
    "C" -> Reason.Scissors
    "X" -> Reason.ROCK
    "Y" -> Reason.Paper
    "Z" -> Reason.Scissors
    else -> throw IllegalAccessException("")
}


fun String.toResultExpected(): Strategy = when (this) {
    "X" -> Strategy.LOST
    "Y" -> Strategy.DRAW
    "Z" -> Strategy.WIN
    else -> throw IllegalAccessException("")
}