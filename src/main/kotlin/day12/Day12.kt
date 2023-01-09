package day12

import utils.ImmutablePoint
import utils.flatMapSet

private val matrix: Map<ImmutablePoint, Char> = input.flatMapIndexed { y: Int, string: String ->
    string.mapIndexed { x: Int, char ->
        ImmutablePoint(x, y) to char
    }
}.toMap()

private val maxY = input.size
private val maxX = input[0].length

fun main() {
    println(part1())
    println(part2())
}

fun part1(): Int  {
    val startPosition = matrix.keys.first { matrix[it] == 'S' }
    return findShortestPath(setOf(startPosition), setOf(startPosition))
}

fun part2(): Int {
    val startPoints = matrix.keys.filter { matrix[it] == 'a' }.toSet()
    return findShortestPath(startPoints, startPoints)
}

private fun findShortestPath(points: Set<ImmutablePoint>, previousPoints : Set<ImmutablePoint>, count: Int = 0): Int {
    if (points.isEmpty()) return 0
    val nextPoints = points.flatMapSet {
        if (matrix[it] == 'E') {
            return count
        }
        it.nextPossibleSteps(previousPoints)
    }
    return findShortestPath(nextPoints, previousPoints, count + 1)
}

private fun ImmutablePoint.nextPossibleSteps(previousPoint : Set<ImmutablePoint>) : List<ImmutablePoint> {
    val nextPossibleSteps = mutableListOf<ImmutablePoint>()
    fun validate(point: ImmutablePoint) : Boolean {
        return matrix.getValue(point).value <= matrix.getValue(this).value + 1
                && this != point && !previousPoint.contains(point)
    }
    if (x + 1 < maxX) ImmutablePoint(x + 1, y).takeIf { validate(it) }?.let { nextPossibleSteps.add(it) }
    if (y + 1 < maxY) ImmutablePoint(x, y + 1).takeIf { validate(it) }?.let { nextPossibleSteps.add(it) }
    if (x - 1 >= 0) ImmutablePoint(x - 1, y).takeIf { validate(it) }?.let { nextPossibleSteps.add(it) }
    if (y - 1 >= 0) ImmutablePoint(x, y - 1).takeIf { validate(it) }?.let { nextPossibleSteps.add(it) }
    return nextPossibleSteps
}

private val lowerMap: Map<Char, Int> = mapOf(
    'a' to 1, 'b' to 2, 'c' to 3, 'd' to 4, 'e' to 5, 'f' to 6, 'g' to 7, 'h' to 8, 'i' to 9, 'j' to 10,
    'k' to 11, 'l' to 12, 'm' to 13, 'n' to 14, 'o' to 15, 'p' to 16, 'q' to 17, 'r' to 18, 's' to 19, 't' to 20,
    'u' to 21, 'v' to 22, 'w' to 23, 'x' to 24, 'y' to 25, 'z' to 26, 'S' to 1, 'E' to 26
)

private val Char.value get() = lowerMap.getValue(this)