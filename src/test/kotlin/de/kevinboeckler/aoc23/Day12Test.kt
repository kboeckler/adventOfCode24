package de.kevinboeckler.aoc23

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day12Test {

    @Test
    fun part1() {
        assertEquals(
            21, Day12().part1(
                "???.### 1,1,3\n" +
                        ".??..??...?##. 1,1,3\n" +
                        "?#?#?#?#?#?#?#? 1,3,1,6\n" +
                        "????.#...#... 4,1,1\n" +
                        "????.######..#####. 1,6,5\n" +
                        "?###???????? 3,2,1\n"
            )
        )
    }

    @Test
    fun part2() {
        assertEquals(
            525152, Day12().part2(
                "???.### 1,1,3\n" +
                        ".??..??...?##. 1,1,3\n" +
                        "?#?#?#?#?#?#?#? 1,3,1,6\n" +
                        "????.#...#... 4,1,1\n" +
                        "????.######..#####. 1,6,5\n" +
                        "?###???????? 3,2,1\n"
            )
        )
    }
}
