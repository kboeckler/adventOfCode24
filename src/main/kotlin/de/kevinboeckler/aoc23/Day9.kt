package de.kevinboeckler.aoc23

class Day9 : Day() {
    override fun part1(input: String): Any {
        return input.lines().asSequence().filterNot { it.isBlank() }.map { "(-?\\d+)".toRegex().findAll(it) }
            .map { it.map { r -> r.value.toInt() }.toList() }
            .map { rightOf(0, it.size - 1, it) }
            .sum()
    }

    override fun part2(input: String): Any {
        return input.lines().asSequence().filterNot { it.isBlank() }.map { "(-?\\d+)".toRegex().findAll(it) }
            .map { it.map { r -> r.value.toInt() }.toList() }
            .map { leftOf(0, 0, it) }
            .sum()
    }

    private fun rightOf(row: Int, col: Int, history: List<Int>): Int {
        if (col == history.size - 1) {
            return bottomOf(row, col + 1, history) + leftOf(row, col + 1, history)
        }
        if (row == 0) {
            return history[col + 1]
        }
        return topOf(row, col + 1, history) - topLeftOf(row, col + 1, history)
    }

    private fun bottomOf(row: Int, col: Int, history: List<Int>): Int {
        if (row + 1 >= history.size) {
            return 0
        }
        return leftOf(row + 1, col, history) + bottomOf(row + 1, col, history)
    }

    private fun bottomRightOf(row: Int, col: Int, history: List<Int>): Int {
        if (row + 1 >= history.size) {
            return 0
        }
        return rightOf(row + 1, col + 1, history) - bottomRightOf(row + 1, col + 1, history)
    }

    private fun leftOf(row: Int, col: Int, history: List<Int>): Int {
        if (col == 0) {
            return rightOf(row, col - 1, history) - bottomRightOf(row, col - 1, history)
        }
        if (row == 0) {
            return history[col - 1]
        }
        return topOf(row, col - 1, history) - topLeftOf(row, col - 1, history)
    }

    private fun topOf(row: Int, col: Int, history: List<Int>): Int {
        if (row - 1 == 0) {
            return history[col]
        }
        return topOf(row - 1, col, history) - topLeftOf(row - 1, col, history)
    }

    private fun topLeftOf(row: Int, col: Int, history: List<Int>): Int {
        if (row - 1 == 0) {
            return history[col - 1]
        }
        return topOf(row - 1, col - 1, history) - topLeftOf(row - 1, col - 1, history)
    }
}
