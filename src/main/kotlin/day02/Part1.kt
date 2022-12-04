package day02

fun part1(): Int {
    return list
        .map {
            it.first.toReason() to it.second.toReason()
        }
        .sumOf {
            it.second.value + play(it.second, it.first)
        }
}

fun play(me: Reason, opponent: Reason): Int {
    return when {
        me.value == opponent.value -> 3
        me.value % 3 + 1 == opponent.value -> 0
        opponent.value % 3 + 1 == me.value -> 6
        else -> throw IllegalAccessException("")
    }
}
