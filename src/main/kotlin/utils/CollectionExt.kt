package utils

import java.util.*

fun <T> Stack<T>.popTo(stack: Stack<T>, times: Int = 1) {
    var count = times
    while (!empty() && count != 0) {
        stack.push(pop())
        count--
    }
}
fun <T> Stack<T>.popAllTo(stack: Stack<T>) {
    while (!empty()) {
        stack.push(pop())
    }
}

fun <T> Stack<T>.popOrNull(): T? = if (empty()) null else pop()

fun <T> Stack<T>.popToStack(number: Int): Stack<T> {
    val stack = Stack<T>()
    var count = number
    while (!empty() && count != 0) {
        stack.push(pop())
        count--
    }
    return stack
}

fun <T> Stack<T>.popToList(number: Int): List<T> {
    val list = mutableListOf<T>()
    var count = number
    while (!empty() && count != 0) {
        list.add(pop())
        count--
    }
    return list
}

fun <T, R> List<T>.windowedClose(size: Int, step: Int = 1, transform: (List<T>) -> R): List<R> =
    windowed(size = size, step = step, transform = transform) + listOf(transform.invoke(listOf(last(), first())))

fun <R> CharSequence.chunkedWithCount(size: Int, transform: (CharSequence, Int) -> R): List<R> {
    var count = 0
    return chunked(size) {
        transform.invoke(it, count).apply {
            count++
        }
    }
}

fun <T> List<T>.getValue(index: Int): T = get(index)!!

inline fun <T, R> Iterable<T>.mapSet(predicate: (T) -> R): Set<R> {
    val result = mutableSetOf<R>()
    forEach {
        result.add(predicate.invoke(it))
    }
    return result
}
inline fun <T, R> Iterable<T>.flatMapSet(predicate: (T) -> Iterable<R>): Set<R> {
    val result = mutableSetOf<R>()
    forEach {
        result.addAll(predicate.invoke(it))
    }
    return result
}