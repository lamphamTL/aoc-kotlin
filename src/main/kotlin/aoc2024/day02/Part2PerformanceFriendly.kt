package aoc2024.day02

import kotlin.math.abs

fun part2PerformanceFriendly(): Int = readInput2().count {
    it.appendStatus() != Status.Unsafe
}

private fun List<Int>.appendStatus(): Status {
    if (size == 4) {
        return status()
    }
    return dropLast(1).appendStatus().append(last())
}

private sealed interface Status {
    data class Safe(val trend: Int, val lastItem : Int) : Status

    /**
     * @param lastItems: there can be multiple positions where the list is safe removing one of them
     *                      -> there can be multiple options for lastItem
     */
    data class HalfSafe(val trend: Int, val lastItems : List<Int>) : Status
    data object Unsafe : Status
}

private fun Status.append(nextItem : Int) : Status = when(this) {
    is Status.HalfSafe -> {
        when (trend) {
            1 -> {
                if (lastItems.any {
                        (nextItem - it) in 1..3
                    }) {
                    Status.HalfSafe(trend, listOf(nextItem))
                } else Status.Unsafe
            }

            -1 -> {
                if (lastItems.any {
                        val diff = nextItem - it
                        diff < 0 && abs(diff) in 1..3
                    }) {
                    Status.HalfSafe(trend, listOf(nextItem))
                } else Status.Unsafe
            }

            else -> throw IllegalArgumentException("What's else?")
        }
    }
    is Status.Safe -> {
        when (trend) {
            1 -> {
                val diff = nextItem - lastItem
                if (diff in 1..3) {
                    Status.Safe(trend, nextItem)
                } else {
                    Status.HalfSafe(trend, listOf(lastItem))
                }
            }

            -1 -> {
                val diff = nextItem - lastItem
                if (diff < 0 && abs(diff) in 1..3) {
                    Status.Safe(trend, nextItem)
                } else {
                    Status.HalfSafe(trend, listOf(lastItem))
                }
            }

            else -> throw IllegalArgumentException("What's else?")
        }
    }
    Status.Unsafe -> Status.Unsafe
}

private fun List<Int>.status(): Status {
    if (isCompletelySafe()) return Status.Safe(trend(get(0), get(1)), get(3))
    val removablePositions = listOf(0,1,2,3).filter {
        this.filterIndexed { index, _ -> index != it }.isCompletelySafe()
    }
    if (removablePositions.isEmpty()) {
        return Status.Unsafe
    }
    val lastItems = removablePositions.mapTo(mutableSetOf()) {
        when (it) {
            0, 1, 2 -> 3
            3 -> 2
            else -> throw IllegalArgumentException("What's this?")
        }
    }.map { get(it) }
    val trend = when(removablePositions) {
        listOf(0), listOf(1), listOf(0, 1) -> trend(get(2), get(3))
        listOf(2), listOf(3), listOf(2, 3) -> trend(get(0), get(1))
        listOf(1, 2) -> trend(get(0), get(3))
        else -> throw IllegalArgumentException("What's this?")
    }
    return Status.HalfSafe(trend = trend, lastItems = lastItems)
}

private fun List<Int>.isCompletelySafe(): Boolean {
    val trend = this[1] - this[0]
    for (i in 0 until (size - 1)) {
        val currentItem = this[i]
        val nextItem = this[i + 1]
        val diff = nextItem - currentItem
        if (diff * trend < 0) {
            return false
        }
        if (abs(diff) > 3 || abs(diff) < 1) {
            return false
        }
    }
    return true
}

/**
 * @return 1 for ascending trend or -1 for descending trend
 */
private fun trend(startItem: Int, endItem: Int): Int =
    (endItem - startItem) / abs(endItem - startItem)