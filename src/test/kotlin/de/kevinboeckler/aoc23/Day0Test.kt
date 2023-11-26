package de.kevinboeckler.aoc23

import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

internal class Day0Test {

    @Test
    fun testDay0Part1() {
        assertEquals(67016, Day0().part1(Day0().readInput()))
    }

    @Test
    fun testDay0Part2() {
        assertEquals(200116, Day0().part2(Day0().readInput()))
    }

}
