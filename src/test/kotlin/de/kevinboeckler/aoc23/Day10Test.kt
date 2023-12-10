package de.kevinboeckler.aoc23

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day10Test {

    @Test
    fun part1_simple() {
        assertEquals(
            4, Day10().part1(
                ".....\n" +
                        ".S-7.\n" +
                        ".|.|.\n" +
                        ".L-J.\n" +
                        ".....\n"
            )
        )
    }

    @Test
    fun part1_complex() {
        assertEquals(
            8, Day10().part1(
                "..F7.\n" +
                        ".FJ|.\n" +
                        "SJ.L7\n" +
                        "|F--J\n" +
                        "LJ...\n"
            )
        )
    }
}
