package de.kevinboeckler.aoc23

class Day15 : Day() {
    override fun part1(input: String): Any {
        return input.split(",").sumOf { hashOf(it) }
    }

    override fun part2(input: String): Any {
        val storage = (0..255).map { mutableListOf<Pair<String, Int>>() }
        input.split(",").forEach {
            val label = it.substring(0, it.indexOfAny(charArrayOf('-', '=')))
            val hash = hashOf(label)
            if (it.contains("-")) {
                storage[hash].removeAll { item -> item.first == label }
            } else {
                if (storage[hash].any { item -> item.first == label }) {
                    storage[hash][storage[hash].indexOfFirst { item -> item.first == label }] =
                        label to it.replace("$label=", "").toInt()
                } else {
                    storage[hash].add(label to it.replace("$label=", "").toInt())
                }
            }
        }
        return storage.mapIndexed { i, pairs -> pairs.mapIndexed { j, pair -> (i + 1) * (j + 1) * pair.second }.sum() }
            .sum()
    }

    private fun hashOf(word: String): Int {
        return (0.toChar() + word).map { it.code }.reduce { a, b -> ((a + b) * 17) % 256 }
    }
}