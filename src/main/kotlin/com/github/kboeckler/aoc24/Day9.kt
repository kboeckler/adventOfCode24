package com.github.kboeckler.aoc24

class Day9 : Day {
    override fun part1(input: String): Any {
        val expanded = expand(input).repeatToStrings()
        val reversed = expanded.reversed().filter { it != "." }
        val sb = mutableListOf<String>()
        var takeIndex = 0
        var index = 0
        var endIndexExclusive = expanded.size
        while (index < endIndexExclusive) {
            if (expanded[index] != ".") {
                sb.add(expanded[index])
            } else {
                sb.add(reversed[takeIndex])
                takeIndex++
                do {
                    endIndexExclusive--
                } while (expanded[endIndexExclusive] == ".")
            }
            index++
        }
        val rearranged = sb
        return rearranged.withIndex()
            .sumOf { it.index.toBigInteger() * "${it.value}".toBigInteger() }
    }

    override fun part2(input: String): Any {
        val expanded = expand(input)
        val expandedUltra = expanded.withIndex().filter { it.value.second != "." }
        val sb = mutableListOf<String>()
        var index = 0
        val movedIndicesOfReversed = mutableSetOf<Int>()
        var endIndexExclusive = expanded.size
        var expandedMutable = expanded.toMutableList()
        while (index < endIndexExclusive) {
            if (expandedMutable[index].second != ".") {
                if (!movedIndicesOfReversed.contains(index)) {
                    sb.addAll(expandedMutable[index].repeatToStrings())
                } else {
                    sb.addAll((expandedMutable[index].first to ".").repeatToStrings())
                }
                index++
            } else {
                val moveable = expandedUltra.reversed()
                    .filter { !movedIndicesOfReversed.contains(it.index) }
                    .filter { it.index > index }
                    .filter { it.value.first <= expandedMutable[index].first }.firstOrNull()
                if (moveable != null) {
                    sb.addAll(moveable.value.repeatToStrings())
                    movedIndicesOfReversed.add(moveable.index)
                    val remainingSpace = expandedMutable[index].first - moveable.value.first
                    expandedMutable.removeAt(index)
                    expandedMutable.add(index, remainingSpace to ".")
                } else {
                    sb.addAll((expandedMutable[index].first to ".").repeatToStrings())
                    index++
                }
            }
        }
        val rearranged = sb
        val now = rearranged.filter { it != "." }
        return now.withIndex()
            .sumOf { it.index.toBigInteger() * "${it.value}".toBigInteger() }
    }

    private fun expand(line: String): List<Pair<Int, String>> {
        val ids = (0 until line.length / 2 + 1).map { "$it" }.toList()
        val dots = line.map { "." }.toList()
        val symbols = ids.zip(dots).flatMap { listOf(it.first, it.second) }
        val amounts = line.map { "$it".toInt() }
        val expanded = amounts.zip(symbols)
        return expanded
    }

    private fun List<Pair<Int, String>>.repeatToStrings(): List<String> {
        return this.flatMap { it.repeatToStrings() }
    }

    private fun Pair<Int, String>.repeatToStrings(): List<String> {
        return (0 until this.first).map { this.second }
    }
}