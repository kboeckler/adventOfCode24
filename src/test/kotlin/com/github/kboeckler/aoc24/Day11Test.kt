package com.github.kboeckler.aoc24

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigInteger

class Day11Test {

    @Test
    fun part1() {
        assertEquals(
            55312.toBigInteger(),
            Day11().part1(
                "125 17"
            )
        )
    }

    @Test
    fun part2() {
        assertEquals(
            BigInteger.valueOf(65601038650482),
            Day11().part2(
                "125 17"
            )
        )
    }
}