package de.kevinboeckler.aoc23

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day8Test {

    @Test
    fun part1() {
        assertEquals(
            2.toBigInteger(), Day8().part1(
                "RL\n" +
                        "\n" +
                        "AAA = (BBB, CCC)\n" +
                        "BBB = (DDD, EEE)\n" +
                        "CCC = (ZZZ, GGG)\n" +
                        "DDD = (DDD, DDD)\n" +
                        "EEE = (EEE, EEE)\n" +
                        "GGG = (GGG, GGG)\n" +
                        "ZZZ = (ZZZ, ZZZ)"
            )
        )
    }

    @Test
    fun part2() {
        assertEquals(
            6.toBigInteger(), Day8().part2(
                "LR\n" +
                        "\n" +
                        "11A = (11B, XXX)\n" +
                        "11B = (XXX, 11Z)\n" +
                        "11Z = (11B, XXX)\n" +
                        "22A = (22B, XXX)\n" +
                        "22B = (22C, 22C)\n" +
                        "22C = (22Z, 22Z)\n" +
                        "22Z = (22B, 22B)\n" +
                        "XXX = (XXX, XXX)"
            )
        )
    }
}