package utils

data class ImmutablePoint(
    val x: Int,
    val y: Int,
) {
    override fun toString(): String = "($x $y)"
}

data class Point(
    var x: Int,
    var y: Int,
)

fun Point.copy(p: Point) {
    x = p.x
    y = p.y
}

fun Point.copy(): Point = Point(x, y)