package day1

fun main() {

    // region puzzle1
    val sumList = list.map {
        it.sumOf { it.toInt() }
    }
    println(sumList.max())
    // endregion
    val result = sumList
        .sortedDescending()
        .subList(0, 3)
        .sum()
    println(result)
}
