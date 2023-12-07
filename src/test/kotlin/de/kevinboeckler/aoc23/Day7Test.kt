package de.kevinboeckler.aoc23

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day7Test {

    @Test
    fun part1() {
        assertEquals(
            6440, Day7().part1(
                "32T3K 765\n" +
                        "T55J5 684\n" +
                        "KK677 28\n" +
                        "KTJJT 220\n" +
                        "QQQJA 483\n"
            )
        )
    }

    @Test
    fun part2() {
        assertEquals(
            5905, Day7().part2(
                "32T3K 765\n" +
                        "T55J5 684\n" +
                        "KK677 28\n" +
                        "KTJJT 220\n" +
                        "QQQJA 483\n"
            )
        )
    }
}