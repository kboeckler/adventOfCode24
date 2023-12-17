package de.kevinboeckler.aoc23

import java.math.BigInteger

fun transpose(rows: List<String>): List<String> {
    return (0..<rows[0].length).map { y ->
        (0..<rows.size).mapNotNull { x ->
            rows[x][y]
        }.joinToString("")
    }.toList()
}

fun Int.toCoords(width: Int): Pair<Int, Int> {
    val x = this % width
    val y = this / width
    return x to y
}

fun Pair<Int, Int>.toIndex(width: Int): Int {
    return second * width + first
}

fun lcm(numbers: List<Int>): BigInteger {
    return numbers.map { it.toBigInteger() }.reduce { a, b -> a * b / a.gcd(b) }
}