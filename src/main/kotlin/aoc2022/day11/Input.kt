package aoc2022.day11

import java.io.File


private const val PATH = "src/main/kotlin/aoc2022.day11/Input.txt"

val input: List<List<String>> get() = File(PATH).readText().split("\n\n").map { it.lines() }