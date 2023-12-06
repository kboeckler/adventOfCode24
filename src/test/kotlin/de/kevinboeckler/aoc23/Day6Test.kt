package de.kevinboeckler.aoc23

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day6Test {

    @Test
    fun part1() {
        assertEquals(
            288, Day6().part1(
                "Time:      7  15   30\n" +
                        "Distance:  9  40  200"
            )
        )
    }

    @Test
    fun part2() {
        assertEquals(
            71503, Day6().part2(
                "Time:      7  15   30\n" +
                        "Distance:  9  40  200"
            )
        )
    }
}