package de.kevinboeckler.aoc23

class Day2 : Day() {
    override fun part1(input: String): Any {
        return input.lines()
            .map { toGame(it) }
            .filter {
                it.cubes.all {
                    listOf(
                        12 of "red",
                        13 of "green",
                        14 of "blue"
                    ).filter { b -> b.color == it.color }.all { b -> it.amount <= b.amount }
                }
            }
            .sumOf { it.id }
    }

    override fun part2(input: String): Any {
        return input.lines()
            .map { toGame(it) }
            .map { game ->
                game.cubes.groupBy { it.color }.mapValues { it.value.maxBy { perColor -> perColor.amount }.amount }
                    .map { Cubes(it.value, it.key) }
            }
            .sumOf {
                it.map { c -> c.amount }.reduce { a, b -> a * b }
            }
    }

    private fun toGame(input: String): Game {
        val gameRest = "Game\\s(\\d+):(.+)".toRegex().matchEntire(input)!!.groupValues
        return Game(gameRest[1].toInt(), toCubes(gameRest[2]))
    }

    /**
     * input is something like  3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
     */
    private fun toCubes(description: String): List<Cubes> {
        return description
            .replace(" ", "")
            .split(";", ",")
            .map {
                val amountColor = "(\\d+)(\\w+)".toRegex().matchEntire(it)!!.groupValues
                Cubes(amountColor[1].toInt(), amountColor[2])
            }
    }

    data class Cubes(val amount: Int, val color: String) {}

    private infix fun Int.of(that: String): Cubes = Cubes(this, that)

    data class Game(val id: Int, val cubes: List<Cubes>) {}
}
