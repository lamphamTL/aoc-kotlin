package aoc2022.day09

import java.io.File

private const val PATH = "src/main/kotlin/aoc2022.day09/Input.txt"

val input get() = File(PATH).readLines()

val inputInMatrix get() = File(PATH).readLines().map { it.map { it.toString() } }

