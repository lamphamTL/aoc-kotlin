package day1

fun main() {

    val sumList = list.map {
        it.sumOf { it.toInt() }
    }

    println(sumList.max())

    val result = sumList
        .sortedDescending()
        .subList(0, 2)
        .sum()
    println(result)
}
