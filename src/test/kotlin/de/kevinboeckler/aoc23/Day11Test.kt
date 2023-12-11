package de.kevinboeckler.aoc23

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day11Test {

    @Test
    fun part1() {
        assertEquals(
            374, Day11().part1(
                "...#......\n" +
                        ".......#..\n" +
                        "#.........\n" +
                        "..........\n" +
                        "......#...\n" +
                        ".#........\n" +
                        ".........#\n" +
                        "..........\n" +
                        ".......#..\n" +
                        "#...#.....\n"
            )
        )
    }
}