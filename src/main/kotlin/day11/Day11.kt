package day11

import utils.println
import java.math.BigDecimal
import java.math.BigInteger
import java.util.*

fun main() {
    println(part1())
    println(part2())
}

private fun part1() = play({ level -> level / 3.toBigInteger() }, 20)
private fun part2() = play(round = 10000) // Get BigInteger overflow exception

private fun play(reduceWorryLevel: (BigInteger) -> BigInteger = { level -> level }, round : Int): Long {
    val monkeys = input.map {
        val startingItems = "Starting items: (.*\\d+)$".toRegex().find(it[1])!!.groupValues.let { (_, items) ->
            Stack<BigInteger>().apply { items.split(", ").forEach { push(it.toBigInteger()) } }
        }
        val op = "Operation: new = old ([+*]) (\\d+|old)$".toRegex().find(it[2])!!.groupValues.let { (_, op, param) ->
            { currentValue: BigInteger ->
                val paramValue = if (param == "old") currentValue else param.toBigInteger()
                if (op == "*") currentValue * paramValue else currentValue + paramValue
            }
        }
        val divisibleNumber = "Test: divisible by (\\d+)".toRegex().find(it[3])!!.groupValues[1].toBigInteger()
        val trueMonkey = "If true: throw to monkey (\\d+)".toRegex().find(it[4])!!.groupValues[1].toInt()
        val falseMonkey = "If false: throw to monkey (\\d+)".toRegex().find(it[5])!!.groupValues[1].toInt()
        val condition = { currentLevel: BigInteger ->
            if (currentLevel % divisibleNumber == 0.toBigInteger()) currentLevel to trueMonkey
            else currentLevel to falseMonkey
        }
        Monkey(startingItems, op, condition)
    }
    val mapResult = monkeys.asSequence().mapIndexed { index: Int, monkey : Monkey ->
        index to 0L
    }.toMap().toMutableMap()
    repeat(round) {
        monkeys.forEachIndexed { monkeyIndex: Int, monkey: Monkey ->
            while(monkey.startingItems.isNotEmpty()) {
                val (level, nextMonkeyIndex) = monkey.startingItems.pop()
                    .let { monkey.op(it)}
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
    val startingItems: Stack<BigInteger>,
    val op: (BigInteger) -> BigInteger,
    val condition: (BigInteger) -> Pair<BigInteger, Int>,
) {
    override fun toString(): String {
        return startingItems.toString()
    }
}