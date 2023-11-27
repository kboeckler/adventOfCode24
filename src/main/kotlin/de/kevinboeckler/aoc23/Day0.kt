package de.kevinboeckler.aoc23

class Day0 : Day() {

    override fun part1(input: String) = sortedcaloriesOfElves(input).take(1).sum()


    override fun part2(input: String) = sortedcaloriesOfElves(input).take(3).sum()

    private fun sortedcaloriesOfElves(input: String) = input.split("\n\n")
        .map { it -> it.split("\n").sumOf { Integer.valueOf(it) } }.sortedDescending()

}
