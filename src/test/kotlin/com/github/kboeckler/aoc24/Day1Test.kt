package com.github.kboeckler.aoc24

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day1Test {

    @Test
    fun part1() {
        assertEquals(
            11,
            Day1().part1(
                "3   4\n" +
                        "4   3\n" +
                        "2   5\n" +
                        "1   3\n" +
                        "3   9\n" +
                        "3   3"
            )
        )
    }

    @Test
    fun part2() {
        assertEquals(
            31,
            Day1().part2(
                "3   4\n" +
                        "4   3\n" +
                        "2   5\n" +
                        "1   3\n" +
                        "3   9\n" +
                        "3   3"
            )
        )
    }
}