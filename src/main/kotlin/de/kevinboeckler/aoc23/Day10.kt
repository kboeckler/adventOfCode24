package de.kevinboeckler.aoc23

import java.util.*

class Day10 : Day() {
    override fun part1(input: String): Any {
        val start =
            input.lines().flatMapIndexed { y, row -> row.mapIndexedNotNull { x, c -> if (c == 'S') x to y else null } }
                .first()
        val pipes = input.lines().flatMapIndexed { y, row ->
            row.mapIndexedNotNull { x, c ->
                when (c) {
                    '|' -> Pipe(x to y, x to y - 1, x to y + 1)
                    '-' -> Pipe(x to y, x - 1 to y, x + 1 to y)
                    'L' -> Pipe(x to y, x to y - 1, x + 1 to y)
                    'J' -> Pipe(x to y, x to y - 1, x - 1 to y)
                    '7' -> Pipe(x to y, x to y + 1, x - 1 to y)
                    'F' -> Pipe(x to y, x to y + 1, x + 1 to y)
                    else -> null
                }
            }.toList()
        }
        val pipesByPos = createPipesMap(pipes, start)
        var maxDist = -1
        val openList: MutableList<Pair<Int, Int>> = LinkedList<Pair<Int, Int>>()
        openList.add(start)
        val visited = mutableSetOf(start)
        while (openList.isNotEmpty()) {
            for (i in 0..<openList.size) {
                val current = openList.removeFirst()
                pipesByPos[current]!!.neighbors.forEach {
                    if (!visited.contains(it)) {
                        openList.add(it)
                        visited.add(it)
                    }
                }
            }
            maxDist++
        }
        return maxDist
    }

    private fun createPipesMap(
        pipes: List<Pipe>,
        start: Pair<Int, Int>
    ): Map<Pair<Int, Int>, Pipe> {
        val pipesByPos = pipes.groupBy { it.pos }.mapValues { it.value.first() }.toMutableMap()
        val neighborsOfStart = listOf(
            start.first - 1 to start.second,
            start.first + 1 to start.second,
            start.first to start.second - 1,
            start.first to start.second + 1
        ).mapNotNull { pipesByPos[it] }.filter { it.neighbors.contains(start) }.map { it.pos }.toList()
        val startPipe = Pipe(start, neighborsOfStart)
        pipesByPos[start] = startPipe
        return pipesByPos
    }

    override fun part2(input: String): Any {
        return "?"
    }

    data class Pipe(val pos: Pair<Int, Int>, val neighbors: List<Pair<Int, Int>>) {
        constructor(pos: Pair<Int, Int>, firstNeighbor: Pair<Int, Int>, secondNeighbor: Pair<Int, Int>) : this(
            pos,
            listOf(firstNeighbor, secondNeighbor)
        )
    }
}
