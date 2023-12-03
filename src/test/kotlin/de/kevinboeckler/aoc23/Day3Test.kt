package de.kevinboeckler.aoc23

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day3Test {
    @Test
    fun part1() {
        assertEquals(
            4361, Day3().part1(
                "467..114..\n" +
                        "...*......\n" +
                        "..35..633.\n" +
                        "......#...\n" +
                        "617*......\n" +
                        ".....+.58.\n" +
                        "..592.....\n" +
                        "......755.\n" +
                        "...\$.*....\n" +
                        ".664.598.."
            )
        )
    }

    @Test
    fun part1_smaller() {
        assertEquals(
            467, Day3().part1(
                "467..114..\n" +
                        "...*......"
            )
        )
    }

    @Test
    fun parseLastNumber() {
        assertEquals(42, Day3().part1("*42"))
    }

    @Test
    fun parseMiddleNumber() {
        assertEquals(42, Day3().part1(".42*"))
    }
    @Test
    fun part2() {
        assertEquals(
            467835, Day3().part2(
                "467..114..\n" +
                        "...*......\n" +
                        "..35..633.\n" +
                        "......#...\n" +
                        "617*......\n" +
                        ".....+.58.\n" +
                        "..592.....\n" +
                        "......755.\n" +
                        "...\$.*....\n" +
                        ".664.598.."
            )
        )
    }
}
