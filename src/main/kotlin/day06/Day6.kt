package day06

fun main() {
    println(part1())
    println(part2())
}

private fun part1(): Int {
    var count = 0
    while (true) {
        val firsts = input.substring(count, count + 4)
        if (firsts.toSet().size == 4) {
            return count + 4
        }
        count++
    }
}

private fun part2(): Int {
    var count = 0
    while (true) {
        val firsts = input.substring(count, count + 14)
        if (firsts.toSet().size == 14) {
            return count + 14
        }
        count++
    }
}