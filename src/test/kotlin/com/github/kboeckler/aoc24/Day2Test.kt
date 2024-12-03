package com.github.kboeckler.aoc24

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day2Test {

    @Test
    fun part1() {
        assertEquals(
            2,
            Day2().part1(
                "7 6 4 2 1\n" +
                        "1 2 7 8 9\n" +
                        "9 7 6 2 1\n" +
                        "1 3 2 4 5\n" +
                        "8 6 4 4 1\n" +
                        "1 3 6 7 9"
            )
        )
    }

    @Test
    fun part2() {
        assertEquals(
            4,
            Day2().part2(
                "7 6 4 2 1\n" +
                        "1 2 7 8 9\n" +
                        "9 7 6 2 1\n" +
                        "1 3 2 4 5\n" +
                        "8 6 4 4 1\n" +
                        "1 3 6 7 9"
            )
        )
    }

    @Test
    fun part2_lineByLine() {
        assertEquals(
            1,
            Day2().part2(
                "7 6 4 2 1"
            )
        )
        assertEquals(
            0,
            Day2().part2(
                "1 2 7 8 9"
            )
        )
        assertEquals(
            0,
            Day2().part2(
                "9 7 6 2 1"
            )
        )
        assertEquals(
            1,
            Day2().part2(
                "1 3 2 4 5"
            )
        )
    }

    @Test
    fun indexOfWrongLevel_firstMinus1() {
        assertEquals(listOf<Int>(-1, -1, -1, -1, -1, -1, -1),
            ("33 30 29 27 24\n" +
                    "97 95 94 91 90 88 86 83\n" +
                    "81 79 76 75 73 72\n" +
                    "8 9 12 15 17 19 21\n" +
                    "26 29 31 32 34\n" +
                    "88 86 85 83 80 78\n" +
                    "76 78 80 81 82 83 85 86").lines().map { it.split(" ") }
                .map { it.map { innerIt -> innerIt.toInt() } }
                .map { Day2().indexOfWrongLevel(it) }
                .toList()
        )
    }

    @Test
    fun indexOfWrongLevel_deeper() {
        assertEquals(listOf<Int>(-1, 7, 7, 3),
            ("5 7 8 11 13\n" +
                    "99 95 92 88 85 82 80 80\n" +
                    "99 95 92 88 85 82 80 81\n" +
                    "1 2 5 3 6").lines().map { it.split(" ") }
                .map { it.map { innerIt -> innerIt.toInt() } }
                .map { Day2().indexOfWrongLevel(it) }
                .toList()
        )
    }
}