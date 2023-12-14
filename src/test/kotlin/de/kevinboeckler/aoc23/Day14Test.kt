package de.kevinboeckler.aoc23

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day14Test {

    @Test
    fun part1() {
        assertEquals(
            136, Day14().part1(
                "O....#....\n" +
                        "O.OO#....#\n" +
                        ".....##...\n" +
                        "OO.#O....O\n" +
                        ".O.....O#.\n" +
                        "O.#..O.#.#\n" +
                        "..O..#O..O\n" +
                        ".......O..\n" +
                        "#....###..\n" +
                        "#OO..#...."
            )
        )
    }

    @Test
    fun part2() {
        assertEquals(
            64, Day14().part2(
                "O....#....\n" +
                        "O.OO#....#\n" +
                        ".....##...\n" +
                        "OO.#O....O\n" +
                        ".O.....O#.\n" +
                        "O.#..O.#.#\n" +
                        "..O..#O..O\n" +
                        ".......O..\n" +
                        "#....###..\n" +
                        "#OO..#...."
            )
        )
    }
}