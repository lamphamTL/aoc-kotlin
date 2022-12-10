package utils

fun String.remove(remove: String) = replace(remove, "")

fun Any?.println() {
    println(toString())
}

fun <T> List<List<T>>.println() {
    forEach {
        it.println()
    }
}

fun <T> List<List<T>>.getValue(x: Int, y: Int): T = get(x)[y]