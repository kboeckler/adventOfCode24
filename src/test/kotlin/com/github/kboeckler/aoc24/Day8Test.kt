package com.github.kboeckler.aoc24

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day8Test {

    @Test
    fun part1() {
        assertEquals(
            14,
            Day8().part1(
                "............\n" +
                        "........0...\n" +
                        ".....0......\n" +
                        ".......0....\n" +
                        "....0.......\n" +
                        "......A.....\n" +
                        "............\n" +
                        "............\n" +
                        "........A...\n" +
                        ".........A..\n" +
                        "............\n" +
                        "............"
            )
        )
    }

    @Test
    fun part2() {
        assertEquals(
            34,
            Day8().part2(
                "............\n" +
                        "........0...\n" +
                        ".....0......\n" +
                        ".......0....\n" +
                        "....0.......\n" +
                        "......A.....\n" +
                        "............\n" +
                        "............\n" +
                        "........A...\n" +
                        ".........A..\n" +
                        "............\n" +
                        "............"
            )
        )
    }
}