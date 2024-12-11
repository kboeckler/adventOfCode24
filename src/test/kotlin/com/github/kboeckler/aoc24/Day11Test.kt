package com.github.kboeckler.aoc24

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day11Test {

    @Test
    fun part1() {
        assertEquals(
            55312,
            Day11().part1(
                "125 17"
            )
        )
    }

    @Test
    fun part2() {
        assertEquals(
            2858,
            Day11().part2(
                "125 17"
            )
        )
    }
}