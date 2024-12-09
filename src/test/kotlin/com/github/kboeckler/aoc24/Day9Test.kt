package com.github.kboeckler.aoc24

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day9Test {

    @Test
    fun part1() {
        assertEquals(
            1928.toBigInteger(),
            Day9().part1(
                "2333133121414131402"
            )
        )
    }

    @Test
    fun part2() {
        assertEquals(
            2858.toBigInteger(),
            Day9().part2(
                "2333133121414131402"
            )
        )
    }
}