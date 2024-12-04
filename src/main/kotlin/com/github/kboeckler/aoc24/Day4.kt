package com.github.kboeckler.aoc24

class Day4 : Day {
    override fun part1(input: String): Any {
        val area = input.lines()
        return listOf(
            listOf("XMAS"),
            listOf("SAMX"),
            listOf("X", "M", "A", "S"),
            listOf("S", "A", "M", "X"),
            listOf("X...", ".M..", "..A.", "...S"),
            listOf("S...", ".A..", "..M.", "...X"),
            listOf("...S", "..A.", ".M..", "X..."),
            listOf("...X", "..M.", ".A..", "S...")
        ).sumOf { occurrences(it, area) }
    }

    override fun part2(input: String): Any {
        val area = input.lines()
        return listOf(
            listOf("M.S", ".A.", "M.S"),
            listOf("M.S", ".A.", "S.M"),
            listOf("S.M", ".A.", "S.M"),
            listOf("S.M", ".A.", "M.S"),
            listOf("M.M", ".A.", "S.S"),
            listOf("S.S", ".A.", "M.M")
        ).sumOf { occurrences(it, area) }
    }

    private fun occurrences(pattern: List<String>, area: List<String>): Int {
        val yOffsets = area.size - pattern.size
        val xOffsets = area[0].length - pattern[0].length
        return (0..yOffsets).sumOf { y ->
            (0..xOffsets).count { x ->
                val matches = pattern.filterIndexed { pyIdx, pyVal ->
                    pyVal.filterIndexed { pxIdx, pxVal ->
                        area[y + pyIdx][x + pxIdx] == pxVal || pxVal == '.'
                    }.count() == pyVal.length
                }.count() == pattern.size
                matches
            }
        }
    }
}