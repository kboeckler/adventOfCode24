package com.github.kboeckler.aoc24

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

enum class Direction {
    UP, RIGHT, DOWN, LEFT;

    fun next(pos: Vector2D): Vector2D {
        return Vector2D(next(pos.x to pos.y))
    }

    fun next(pos: Pair<Int, Int>): Pair<Int, Int> {
        return when (this) {
            UP -> pos.first to pos.second - 1
            RIGHT -> pos.first + 1 to pos.second
            DOWN -> pos.first to pos.second + 1
            LEFT -> pos.first - 1 to pos.second
        }
    }

    fun rotateRight(): Direction {
        return when (this) {
            UP -> RIGHT
            RIGHT -> DOWN
            DOWN -> LEFT
            LEFT -> UP
        }
    }
}

data class Vector2D(val x: Int, val y: Int) {
    constructor(pair: Pair<Int, Int>) : this(pair.first, pair.second)

    fun inBoundsOfGrid(width: Int, height: Int) = this.x >= 0 && this.y >= 0 && this.x < width && this.y < height
}

fun Vector2D.toIndex(width: Int): Int {
    return (x to y).toIndex(width)
}

operator fun Vector2D.plus(other: Vector2D) = Vector2D(this.x + other.x, this.y + other.y)

operator fun Vector2D.minus(other: Vector2D) = Vector2D(this.x - other.x, this.y - other.y)

operator fun Int.times(vector: Vector2D) = Vector2D(this * vector.x, this * vector.y)
