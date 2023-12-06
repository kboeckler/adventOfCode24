package de.kevinboeckler.aoc23

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day5Test {

    @Test
    fun part1() {
        assertEquals(
            35.toBigInteger(), Day5().part1(
                "seeds: 79 14 55 13\n" +
                        "\n" +
                        "seed-to-soil map:\n" +
                        "50 98 2\n" +
                        "52 50 48\n" +
                        "\n" +
                        "soil-to-fertilizer map:\n" +
                        "0 15 37\n" +
                        "37 52 2\n" +
                        "39 0 15\n" +
                        "\n" +
                        "fertilizer-to-water map:\n" +
                        "49 53 8\n" +
                        "0 11 42\n" +
                        "42 0 7\n" +
                        "57 7 4\n" +
                        "\n" +
                        "water-to-light map:\n" +
                        "88 18 7\n" +
                        "18 25 70\n" +
                        "\n" +
                        "light-to-temperature map:\n" +
                        "45 77 23\n" +
                        "81 45 19\n" +
                        "68 64 13\n" +
                        "\n" +
                        "temperature-to-humidity map:\n" +
                        "0 69 1\n" +
                        "1 0 69\n" +
                        "\n" +
                        "humidity-to-location map:\n" +
                        "60 56 37\n" +
                        "56 93 4\n"
            )
        )
    }

    @Test
    fun part2() {
        assertEquals(
            46.toBigInteger(), Day5().part2(
                "seeds: 79 14 55 13\n" +
                        "\n" +
                        "seed-to-soil map:\n" +
                        "50 98 2\n" +
                        "52 50 48\n" +
                        "\n" +
                        "soil-to-fertilizer map:\n" +
                        "0 15 37\n" +
                        "37 52 2\n" +
                        "39 0 15\n" +
                        "\n" +
                        "fertilizer-to-water map:\n" +
                        "49 53 8\n" +
                        "0 11 42\n" +
                        "42 0 7\n" +
                        "57 7 4\n" +
                        "\n" +
                        "water-to-light map:\n" +
                        "88 18 7\n" +
                        "18 25 70\n" +
                        "\n" +
                        "light-to-temperature map:\n" +
                        "45 77 23\n" +
                        "81 45 19\n" +
                        "68 64 13\n" +
                        "\n" +
                        "temperature-to-humidity map:\n" +
                        "0 69 1\n" +
                        "1 0 69\n" +
                        "\n" +
                        "humidity-to-location map:\n" +
                        "60 56 37\n" +
                        "56 93 4\n"
            )
        )
    }

    @Test
    fun part2_real() {
        assertEquals(11611182.toBigInteger(), Day5().part2(Day5().readInput()))
    }

    @Test
    fun bigRange_intersect_nothingBecauseOtherBehind() {
        assertEquals(null, Day5.BigRange(1, 10).intersect(Day5.BigRange(11, 1)))
    }

    @Test
    fun bigRange_intersect_nothingBecauseOtherBefore() {
        assertEquals(null, Day5.BigRange(1, 10).intersect(Day5.BigRange(0, 1)))
    }

    @Test
    fun bigRange_intersect_otherEquals() {
        assertEquals(Day5.BigRange(1, 10), Day5.BigRange(1, 10).intersect(Day5.BigRange(1, 10)))
    }

    @Test
    fun bigRange_intersect_otherFromEquals() {
        assertEquals(Day5.BigRange(1, 5), Day5.BigRange(1, 10).intersect(Day5.BigRange(1, 5)))
    }

    @Test
    fun bigRange_intersect_otherEndEquals() {
        assertEquals(Day5.BigRange(6, 5), Day5.BigRange(1, 10).intersect(Day5.BigRange(6, 5)))
    }

    @Test
    fun bigRange_intersect_otherInside() {
        assertEquals(Day5.BigRange(6, 2), Day5.BigRange(1, 10).intersect(Day5.BigRange(6, 2)))
    }

    @Test
    fun bigRange_intersect_otherAtBegin() {
        assertEquals(Day5.BigRange(1, 1), Day5.BigRange(1, 10).intersect(Day5.BigRange(0, 2)))
    }

    @Test
    fun bigRange_intersect_otherAtEnd() {
        assertEquals(Day5.BigRange(10, 1), Day5.BigRange(1, 10).intersect(Day5.BigRange(10, 2)))
    }

    @Test
    fun bigRange_intersect_otherCovers() {
        assertEquals(Day5.BigRange(1, 10), Day5.BigRange(1, 10).intersect(Day5.BigRange(0, 12)))
    }
}