package day11

import utils.findMultiple
import utils.findSingle
import java.util.*

fun main() {
    println(part1())
    println(part2())
}

private fun part1() = play({ level -> level / 3L }, 20)
private fun part2() = play({ level -> level % (13 * 19 * 11 * 17 * 3 * 7 * 5 * 2) }, round = 10000)

private fun play(reduceWorryLevel: (Long) -> Long = { level -> level }, round : Int): Long {
    val monkeys = input.map {
        val startingItems = "Starting items: (.*\\d+)$".findSingle(it[1]).let { items ->
            Stack<Long>().apply { items.split(", ").forEach { push(it.toLong()) } }
        }
        val op = "Operation: new = old ([+*]) (\\d+|old)$".findMultiple(it[2]).let { (op, param) ->
            { currentValue: Long ->
                val paramValue = if (param == "old") currentValue else param.toLong()
                if (op == "*") currentValue * paramValue else currentValue + paramValue
            }
        }
        val divisibleNumber = "Test: divisible by (\\d+)".findSingle(it[3]).toLong()
        val trueMonkey = "If true: throw to monkey (\\d+)".findSingle(it[4]).toInt()
        val falseMonkey = "If false: throw to monkey (\\d+)".findSingle(it[5]).toInt()
        val condition = { currentLevel: Long ->
            if (currentLevel % divisibleNumber == 0L) currentLevel to trueMonkey
            else currentLevel to falseMonkey
        }
        Monkey(startingItems, op, condition)
    }
    val mapResult = monkeys.asSequence().mapIndexed { index: Int,_ -> index to 0L }.toMap().toMutableMap()
    repeat(round) {
        monkeys.forEachIndexed { monkeyIndex: Int, monkey: Monkey ->
            while(monkey.startingItems.isNotEmpty()) {
                val (level, nextMonkeyIndex) = monkey.startingItems.pop()
                    .let { monkey.op(it) }
                    .let { reduceWorryLevel.invoke(it) }
                    .let { monkey.condition(it) }
                monkeys[nextMonkeyIndex].startingItems.push(level)
                mapResult[monkeyIndex] = mapResult.getValue(monkeyIndex) + 1
            }
        }
    }
    return mapResult.values.sortedDescending().take(2).reduce { acc, i -> acc * i }
}

private class Monkey(
    val startingItems: Stack<Long>,
    val op: (Long) -> Long,
    val condition: (Long) -> Pair<Long, Int>,
) {
    override fun toString(): String {
        return startingItems.toString()
    }
}