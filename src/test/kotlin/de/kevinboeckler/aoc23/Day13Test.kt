package de.kevinboeckler.aoc23

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day13Test {

    @Test
    fun part1() {
        assertEquals(
            405, Day13().part1(
                "#.##..##.\n" +
                        "..#.##.#.\n" +
                        "##......#\n" +
                        "##......#\n" +
                        "..#.##.#.\n" +
                        "..##..##.\n" +
                        "#.#.##.#.\n" +
                        "\n" +
                        "#...##..#\n" +
                        "#....#..#\n" +
                        "..##..###\n" +
                        "#####.##.\n" +
                        "#####.##.\n" +
                        "..##..###\n" +
                        "#....#..#"
            )
        )
    }
}