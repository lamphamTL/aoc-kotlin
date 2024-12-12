package aoc2024.day02

import kotlin.time.measureTime

fun main() {
    println(part1())
    println(part2())
    println(part2PerformanceFriendly())

    measureTime { part2() }.let(::println) // 33.420666ms
    measureTime { part2PerformanceFriendly() }.let(::println) // 7.928250ms
}
