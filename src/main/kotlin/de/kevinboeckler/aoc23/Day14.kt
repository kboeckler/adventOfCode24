package de.kevinboeckler.aoc23

class Day14 : Day() {
    override fun part1(input: String): Any {
        return transpose(input.lines()).sumOf {
            it.mapIndexed { i, c -> if (c == '#') "#${it.length - i}" else "$c" }.joinToString("")
                .split("#").sumOf { group ->
                    val maxValue = "(\\d+)".toRegex().find(group)?.value?.toInt() ?: it.length + 1
                    val amountOs = group.length - group.replace("O", "").length
                    (0..<amountOs).sumOf { idxO -> maxValue - idxO - 1 }
                }
        }
    }

    override fun part2(input: String): Any {
        return "?"
    }
}