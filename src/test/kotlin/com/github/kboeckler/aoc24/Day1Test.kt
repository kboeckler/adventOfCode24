package com.github.kboeckler.aoc24

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day1Test {

    @Test
    fun part2() {
        assertEquals(
            281, com.github.kboeckler.aoc24.Day1().part2(
                "two1nine\n" +
                        "eightwothree\n" +
                        "abcone2threexyz\n" +
                        "xtwone3four\n" +
                        "4nineeightseven2\n" +
                        "zoneight234\n" +
                        "7pqrstsixteen"
            )
        )
    }
}