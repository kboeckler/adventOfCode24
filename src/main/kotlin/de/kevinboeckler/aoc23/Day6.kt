package de.kevinboeckler.aoc23

import java.math.BigInteger
import java.math.MathContext
import java.math.RoundingMode

class Day6 : Day() {
    override fun part1(input: String): Any {
        val lines = input.lines()
        val times = "(\\d+)".toRegex().findAll(lines[0]).map { it.value.toInt() }.toList()
        val recordDistances = "(\\d+)".toRegex().findAll(lines[1]).map { it.value.toBigInteger() }.toList()
        val games = times.mapIndexed { i, t -> RaceGame(t, recordDistances[i]) }
        return games.map { waysToBeatTheRecord(it) }.reduce { a, b -> a * b }
    }

    override fun part2(input: String): Any {
        val lines = input.lines()
        val time = "(\\d+)".toRegex().findAll(lines[0]).map { it.value }.reduce { a, b -> a + b }.toInt()
        val recordDistance =
            "(\\d+)".toRegex().findAll(lines[1]).map { it.value }.reduce { a, b -> a + b }.toBigInteger()
        val game = RaceGame(time, recordDistance)
        return waysToBeatTheRecord(game)
    }

    private fun waysToBeatTheRecord(game: RaceGame): Int {
        val lowerBound =
            (0.5 * game.time).toBigDecimal() - ((0.25.toBigDecimal() * game.time.toBigDecimal() * game.time.toBigDecimal()) - game.recordDistance.toBigDecimal()).sqrt(
                MathContext(34, RoundingMode.DOWN)
            )
        val upperBound =
            (0.5 * game.time).toBigDecimal() + ((0.25.toBigDecimal() * game.time.toBigDecimal() * game.time.toBigDecimal()) - game.recordDistance.toBigDecimal()).sqrt(
                MathContext(34, RoundingMode.UP)
            )
        val smallestWay = lowerBound.toBigInteger() + 1.toBigInteger()
        val greatestWay =
            if (upperBound.divideAndRemainder(1.toBigDecimal())[1].compareTo(0.toBigDecimal()) == 0) upperBound.toBigInteger() - 1.toBigInteger() else upperBound.toBigInteger()
        return (greatestWay - smallestWay).toInt() + 1
    }

    data class RaceGame(val time: Int, val recordDistance: BigInteger)
}
