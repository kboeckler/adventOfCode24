package com.github.kboeckler.aoc24

import kotlin.math.abs
import kotlin.math.pow

class Day3 : Day() {
    override fun part1(input: String): Any {
        val width = if (input.contains("\n")) input.indexOfFirst { it == '\n' } + 1 else input.length
        val numbers = parseNumbers(input)
        val symbols = parseSymbols(input)
        return numbers
            .filter { n ->
                symbols.any { s -> dist2(s.coord, n, width) <= 2.0 }
            }
            .sumOf { it.value }
    }

    override fun part2(input: String): Any {
        val width = if (input.contains("\n")) input.indexOfFirst { it == '\n' } + 1 else input.length
        val numbers = parseNumbers(input)
        val symbols = parseSymbols(input)
        return symbols
            .filter { it.value == '*' }
            .map { symbol -> numbers.filter { dist2(symbol.coord, it, width) <= 2.0 } }
            .filter { it.size == 2 }
            .sumOf { it[0].value * it[1].value }
    }

    private fun dist2(coord: Int, number: Number, width: Int): Double {
        return number.coords.minOfOrNull {
            dist2(it, coord, width)
        } ?: throw IllegalStateException("Number or Symbol has empty coords")
    }

    private fun dist2(one: Int, another: Int, width: Int): Double {
        val (oneX, oneY) = one.toCoords(width)
        val (anotherX, anotherY) = another.toCoords(width)
        return abs(oneX - anotherX).toDouble().pow(2.0) + abs(oneY - anotherY).toDouble().pow(2.0)
    }

    data class Number(val value: Int, val coords: IntRange)

    private fun parseNumbers(input: String): List<Number> {
        val numbers = mutableListOf<Number>()
        var currentNumberString = ""
        var currentNumberEnd = 0
        val addNumberIfPresent = fun() {
            if (currentNumberString.isNotEmpty()) {
                numbers.add(
                    Number(
                        currentNumberString.toInt(),
                        currentNumberEnd - currentNumberString.length + 1..currentNumberEnd
                    )
                )
                currentNumberString = ""
            }
        }

        input.forEachIndexed { i, c ->
            if (c.isDigit()) {
                currentNumberString += c
                currentNumberEnd = i
            } else {
                addNumberIfPresent()
            }
        }
        addNumberIfPresent()

        return numbers
    }

    data class Symbol(val value: Char, val coord: Int)

    private fun parseSymbols(input: String): List<Symbol> {
        val symbols = mutableListOf<Symbol>()
        input.forEachIndexed { i, c ->
            if (!c.isDigit() && c != '\n' && c != '.') {
                symbols.add(Symbol(c, i))
            }
        }
        return symbols
    }
}
