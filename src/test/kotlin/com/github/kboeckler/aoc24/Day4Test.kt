package com.github.kboeckler.aoc24

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day4Test {

    @Test
    fun part1() {
        assertEquals(
            18,
            Day4().part1(
                "MMMSXXMASM\n" +
                        "MSAMXMSMSA\n" +
                        "AMXSXMAAMM\n" +
                        "MSAMASMSMX\n" +
                        "XMASAMXAMM\n" +
                        "XXAMMXXAMA\n" +
                        "SMSMSASXSS\n" +
                        "SAXAMASAAA\n" +
                        "MAMMMXMMMM\n" +
                        "MXMXAXMASX"
            )
        )
    }

    @Test
    fun part2() {
        assertEquals(
            31,
            Day4().part2(
                "MMMSXXMASM\n" +
                        "MSAMXMSMSA\n" +
                        "AMXSXMAAMM\n" +
                        "MSAMASMSMX\n" +
                        "XMASAMXAMM\n" +
                        "XXAMMXXAMA\n" +
                        "SMSMSASXSS\n" +
                        "SAXAMASAAA\n" +
                        "MAMMMXMMMM\n" +
                        "MXMXAXMASX"
            )
        )
    }
}