package com.github.kboeckler.aoc24

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day7Test {

    @Test
    fun part1() {
        assertEquals(
            3749.toBigInteger(),
            Day7().part1(
                "190: 10 19\n" +
                        "3267: 81 40 27\n" +
                        "83: 17 5\n" +
                        "156: 15 6\n" +
                        "7290: 6 8 6 15\n" +
                        "161011: 16 10 13\n" +
                        "192: 17 8 14\n" +
                        "21037: 9 7 18 13\n" +
                        "292: 11 6 16 20"
            )
        )
    }

    @Test
    fun part2() {
        assertEquals(
            11387.toBigInteger(),
            Day7().part2(
                "190: 10 19\n" +
                        "3267: 81 40 27\n" +
                        "83: 17 5\n" +
                        "156: 15 6\n" +
                        "7290: 6 8 6 15\n" +
                        "161011: 16 10 13\n" +
                        "192: 17 8 14\n" +
                        "21037: 9 7 18 13\n" +
                        "292: 11 6 16 20"
            )
        )
    }
}