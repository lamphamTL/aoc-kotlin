package utils

import java.util.*

fun <T> Stack<T>.popOrNull(): T? = if (empty()) null else pop()

fun <T, R> List<T>.windowedClose(size: Int, step: Int = 1, transform: (List<T>) -> R): List<R> =
    windowed(size = size, step = step, transform = transform) + listOf(transform.invoke(listOf(last(), first())))