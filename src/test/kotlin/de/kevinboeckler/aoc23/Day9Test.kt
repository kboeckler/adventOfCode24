package de.kevinboeckler.aoc23

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day9Test {

    @Test
    fun part1() {
        assertEquals(
            114, Day9().part1(
                "0 3 6 9 12 15\n" +
                        "1 3 6 10 15 21\n" +
                        "10 13 16 21 30 45\n"
            )
        )
    }

    @Test
    fun part2() {
        assertEquals(
            2, Day9().part2(
                "0 3 6 9 12 15\n" +
                        "1 3 6 10 15 21\n" +
                        "10 13 16 21 30 45\n"
            )
        )
    }
}
