package com.github.kboeckler.aoc24

class Day1 : Day() {
    override fun part1(input: String): Any {
        return input.lines()
            .map {
                it.replace("[a-zA-Z]*".toRegex(), "")
            }
            .map { "" + it[0] + it[it.length - 1] }
            .sumOf { Integer.valueOf(it) }
    }

    override fun part2(input: String): Any {
        return input.lines()
            .map {
                it
                    .replace("one", "o1e")
                    .replace("two", "t2e")
                    .replace("three", "t3e")
                    .replace("four", "f4r")
                    .replace("five", "f5e")
                    .replace("six", "s6x")
                    .replace("seven", "s7n")
                    .replace("eight", "e8t")
                    .replace("nine", "n9e")
            }
            .map {
                it.replace("[a-zA-Z]*".toRegex(), "")
            }
            .map { "" + it[0] + it[it.length - 1] }
            .sumOf { Integer.valueOf(it) }
    }
}
