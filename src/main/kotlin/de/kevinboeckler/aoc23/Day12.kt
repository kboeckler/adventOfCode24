package de.kevinboeckler.aoc23

class Day12 : Day() {
    override fun part1(input: String): Any {
        return input.lines().asSequence().filterNot { it.isBlank() }.map {
            "([.?#]+)".toRegex().find(it)!!.value to "(\\d+)".toRegex().findAll(it)
                .map { match -> match.value.toInt() }.toList()
        }.map { Conditions(it.second, it.first) }.sumOf { arrangementsOf(it) }
    }

    override fun part2(input: String): Any {
        if (true) return "?" // TODO
        return input.lines().asSequence().filterNot { it.isBlank() }.map {
            "([.?#]+)".toRegex().find(it)!!.value to "(\\d+)".toRegex().findAll(it)
                .map { match -> match.value.toInt() }.toList()
        }.map { Conditions(unfold(it.second), unfold(it.first)) }.sumOf { arrangementsOf(it) }
    }

    data class Conditions(val groups: List<Int>, val record: String)

    private fun arrangementsOf(condition: Conditions): Int {
        val firstUnknown = condition.record.indexOfFirst { it == '?' }
        if (firstUnknown == -1) {
            return if (isFeasible(condition)) 1 else 0
        }
        val firstGroups = "(#+[^#])".toRegex().findAll(condition.record.substring(0, firstUnknown))
            .map { it.value.filter { c -> c == '#' }.length }.toList()
        if (firstGroups.filterIndexed { i, num -> i >= condition.groups.size || condition.groups[i] != num }
                .isNotEmpty()) {
            return 0
        }
        val replaced1 = Conditions(condition.groups, condition.record.replaceFirst('?', '.'))
        val replaced2 = Conditions(condition.groups, condition.record.replaceFirst('?', '#'))
        return arrangementsOf(replaced1) + arrangementsOf(replaced2)
    }

    private fun isFeasible(condition: Conditions): Boolean {
        val actualGroupLengths = "(#+)".toRegex().findAll(condition.record).map { it.value }.map { it.length }.toList()
        return actualGroupLengths == condition.groups
    }

    private fun unfold(value: String): String {
        return (0..4).joinToString("?") { _ -> value }
    }

    private fun unfold(values: List<Int>): List<Int> {
        return (0..4).flatMap { values }
    }
}
