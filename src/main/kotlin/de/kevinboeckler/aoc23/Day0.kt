package de.kevinboeckler.aoc23

class Day0 : Day() {
    override fun part1(input: String): Any {
        return input.split("\n\n")
            .map { it -> it.split("\n").sumOf { Integer.valueOf(it) } }.maxOf { it }
    }

    override fun part2(input: String): Any {
        return input.split("\n\n")
            .map { it -> it.split("\n").sumOf { Integer.valueOf(it) } }.sortedDescending().take(3).sum()
    }

}
