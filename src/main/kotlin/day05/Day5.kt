package day05

import utils.*
import java.util.Stack

fun main() {
    println(part1())
    println(part2())
}

private fun part1(): String {
    val (stacks, moves) = read()
    moves.forEach { (times, from, to) ->
        (1..times).forEach {
            val item = stacks[from].popOrNull() ?: return@forEach
            stacks[to].push(item)
        }
    }
    return stacks.joinToString(separator = ""){
        it.pop().toString()
    }
}

private fun part2(): String {
    val (stacks, moves) = read()
    moves.forEach { (times, from, to) ->
        val itemsToMove = stacks[from].popToStack(times)
        while (!itemsToMove.empty()) {
            itemsToMove.popTo(stacks[to])
        }
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
        val moveInput = it
            .remove("move ")
            .remove("from ")
            .remove("to ")
            .split(" ")
        Move(moveInput[0].toInt(), moveInput[1].toInt() - 1, moveInput[2].toInt() - 1)
    }
    return stack to operators
}