package de.kevinboeckler.aoc23

import java.math.BigInteger

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
        return IntRange(0, game.time)
            .map { buttonMs -> (game.time.toBigInteger() - buttonMs.toBigInteger()) * buttonMs.toBigInteger() }
            .count { d ->
                d > game.recordDistance
            }
    }

    data class RaceGame(val time: Int, val recordDistance: BigInteger)
}
