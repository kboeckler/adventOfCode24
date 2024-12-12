package com.github.kboeckler.aoc24

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day12Test {

    @Test
    fun part1_small() {
        assertEquals(
            140,
            Day12().part1(
                "AAAA\n" +
                        "BBCD\n" +
                        "BBCC\n" +
                        "EEEC"
            )
        )
    }

    @Test
    fun part1_larger() {
        assertEquals(
            1930,
            Day12().part1(
                "RRRRIICCFF\n" +
                        "RRRRIICCCF\n" +
                        "VVRRRCCFFF\n" +
                        "VVRCCCJFFF\n" +
                        "VVVVCJJCFE\n" +
                        "VVIVCCJJEE\n" +
                        "VVIIICJJEE\n" +
                        "MIIIIIJJEE\n" +
                        "MIIISIJEEE\n" +
                        "MMMISSJEEE"
            )
        )
    }

    @Test
    fun part2_small() {
        assertEquals(
            368,
            Day12().part2(
                "AAAA\n" +
                        "BBCD\n" +
                        "BBCC\n" +
                        "EEEC"
            )
        )
    }

    @Test
    fun part2_larger() {
        assertEquals(
            1206,
            Day12().part2(
                "RRRRIICCFF\n" +
                        "RRRRIICCCF\n" +
                        "VVRRRCCFFF\n" +
                        "VVRCCCJFFF\n" +
                        "VVVVCJJCFE\n" +
                        "VVIVCCJJEE\n" +
                        "VVIIICJJEE\n" +
                        "MIIIIIJJEE\n" +
                        "MIIISIJEEE\n" +
                        "MMMISSJEEE"
            )
        )
    }
}