package aoc2022.day05

import utils.*
import java.util.Stack

fun main() {
    println(part1())
    println(part2())
}

private fun part1(): String {
    val (stacks, moves) = read()
    moves.forEach { (times, from, to) ->
        stacks[from].popTo(stacks[to], times)
    }
    return stacks.joinToString(separator = ""){
        it.pop().toString()
    }
}

private fun part2(): String {
    val (stacks, moves) = read()
    moves.forEach { (times, from, to) ->
        val itemsToMove = stacks[from].popToStack(times)
        itemsToMove.popAllTo(stacks[to])
    }
    return stacks.joinToString(separator = ""){
        it.pop().toString()
    }
}

private fun read(): Pair<List<Stack<Char>>, List<Move>> {
    val stackNumber: Int
    val (stackList, moveList) = input.partition {
        ".*\\[[A-Z]{1}\\].*".toRegex().matches(it)
    }.let {
        stackNumber = it.second[0].trim().last().toString().toInt()
        it.copy(second = it.second.drop(2))
    }
    val stack = List(stackNumber) { Stack<Char>() }
    stackList
        .reversed()
        .map {
            it.chunkedWithCount(4) { charSequence, count ->
                if (charSequence.isNotBlank()) {
                    stack[count].push(charSequence[1])
                }
            }
        }
    val operators = moveList.map {
        val (move, from, to) = it
            .remove("move ")
            .remove("from ")
            .remove("to ")
            .split(" ")
        Move(move.toInt(), from.toInt() - 1, to.toInt() - 1)
    }
    return stack to operators
}