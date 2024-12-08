package aoc2022.day07

import java.util.*

fun main() {
    println(part1())
    println(part2())
}

private fun part1(): Int {
    val root = getRootDir()
    return root.getAllDir().filter {
        it.getSize() < 100000
    }.sumOf { it.getSize() }
}

private fun part2(): Int {
    val root = getRootDir()
    val rootSize = root.getSize()
    return root.getAllDir().filter {
        (70000000 - rootSize + it.getSize()) > 30000000
    }.minOf { it.getSize() }
}

private fun getRootDir(): Dir {
    val root = Dir("/")
    val inputStack = Stack<String>()
    input.drop(2).reversed().forEach {
        inputStack.push(it)
    }
    root.recursivelyScan(inputStack)
    return root
}

private fun Dir.getAllDir(result: MutableList<Dir> = mutableListOf()): List<Dir> {
    val childDirs = getAllChildDirs()
    if (childDirs.isEmpty()) {
        return result
    }
    result.addAll(childDirs)
    childDirs.forEach {
        it.getAllDir(result)
    }
    return result
}

private fun Dir.recursivelyScan(input: Stack<String>): Dir {
    if (input.isEmpty()) {
        return this
    }
    val nextLine = input.pop()
    "(\\d+) (.+)".toRegex().find(nextLine)?.groupValues?.let { (_, size, name) ->
        File(size.toInt())
    }?.let {
        childFiles.add(it)
    }
    "dir (.+)".toRegex().find(nextLine)?.groupValues?.let { (_, name) ->
        Dir(name)
    }?.let {
        childDirs.put(it.name, it)
    }
    "\\$ cd (.+)".toRegex().find(nextLine)?.groupValues?.let { (_, dirName) ->
        if (dirName == "..") {
            return this
        }
        childDirs[dirName]?.recursivelyScan(input)

    }
    return recursivelyScan(input)
}
