package com.github.kboeckler.aoc24

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day6Test {

    @Test
    fun part1() {
        assertEquals(
            41,
            Day6().part1(
                "....#.....\n" +
                        ".........#\n" +
                        "..........\n" +
                        "..#.......\n" +
                        ".......#..\n" +
                        "..........\n" +
                        ".#..^.....\n" +
                        "........#.\n" +
                        "#.........\n" +
                        "......#..."
            )
        )
    }

    @Test
    fun part2() {
        assertEquals(
            6,
            Day6().part2(
                "....#.....\n" +
                        ".........#\n" +
                        "..........\n" +
                        "..#.......\n" +
                        ".......#..\n" +
                        "..........\n" +
                        ".#..^.....\n" +
                        "........#.\n" +
                        "#.........\n" +
                        "......#..."
            )
        )
    }
}