package de.kevinboeckler.aoc23

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigInteger

class Day5Test {

    @Test
    fun examineInput() {
        val input = Day5().readInput()
        val ints = "(\\d+)".toRegex().findAll(input).map { it.value.toBigInteger() }.toList()
        println(ints.count())
        println(ints.distinct().count())
        println(ints.max())
        "(\\d+)\\s(\\d+)".toRegex().findAll(input.substringBefore("\n"))
            .onEach { println(it.value)  }
            .map { it.groupValues[1].toBigInteger() to it.groupValues[2].toBigInteger() }
            .sortedBy { it.first }
            .forEach { println("$it and ${it.first + it.second}") }
        Day5().part1(input)
    }
    //1639775
    //4279520523
    /*
    3356941271 54368890 = 3411310161
    3866217991 323477533
     */

    private fun intsFromTo(from: BigInteger, range: BigInteger): List<BigInteger> {
        val ints = mutableListOf<BigInteger>()
        var i = from
        while (i < from + range) {
            ints.add(i)
            i++
        }
        return ints
    }

    @Test
    fun part1() {
        assertEquals(
            35.toBigInteger(), Day5().part1(
                "seeds: 79 14 55 13\n" +
                        "\n" +
                        "seed-to-soil map:\n" +
                        "50 98 2\n" +
                        "52 50 48\n" +
                        "\n" +
                        "soil-to-fertilizer map:\n" +
                        "0 15 37\n" +
                        "37 52 2\n" +
                        "39 0 15\n" +
                        "\n" +
                        "fertilizer-to-water map:\n" +
                        "49 53 8\n" +
                        "0 11 42\n" +
                        "42 0 7\n" +
                        "57 7 4\n" +
                        "\n" +
                        "water-to-light map:\n" +
                        "88 18 7\n" +
                        "18 25 70\n" +
                        "\n" +
                        "light-to-temperature map:\n" +
                        "45 77 23\n" +
                        "81 45 19\n" +
                        "68 64 13\n" +
                        "\n" +
                        "temperature-to-humidity map:\n" +
                        "0 69 1\n" +
                        "1 0 69\n" +
                        "\n" +
                        "humidity-to-location map:\n" +
                        "60 56 37\n" +
                        "56 93 4\n"
            )
        )
    }

    @Test
    fun part2() {
        assertEquals(
            46.toBigInteger(), Day5().part2(
                "seeds: 79 14 55 13\n" +
                        "\n" +
                        "seed-to-soil map:\n" +
                        "50 98 2\n" +
                        "52 50 48\n" +
                        "\n" +
                        "soil-to-fertilizer map:\n" +
                        "0 15 37\n" +
                        "37 52 2\n" +
                        "39 0 15\n" +
                        "\n" +
                        "fertilizer-to-water map:\n" +
                        "49 53 8\n" +
                        "0 11 42\n" +
                        "42 0 7\n" +
                        "57 7 4\n" +
                        "\n" +
                        "water-to-light map:\n" +
                        "88 18 7\n" +
                        "18 25 70\n" +
                        "\n" +
                        "light-to-temperature map:\n" +
                        "45 77 23\n" +
                        "81 45 19\n" +
                        "68 64 13\n" +
                        "\n" +
                        "temperature-to-humidity map:\n" +
                        "0 69 1\n" +
                        "1 0 69\n" +
                        "\n" +
                        "humidity-to-location map:\n" +
                        "60 56 37\n" +
                        "56 93 4\n"
            )
        )
    }
}