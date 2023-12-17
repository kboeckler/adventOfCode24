package de.kevinboeckler.aoc23

import java.lang.Integer.min
import java.util.*

class Day17 : Day() {
    override fun part1(input: String): Any {
        val grid = input.replace("\n", "")
        val width = input.indexOfFirst { it == '\n' }
        val height = grid.length / width
        val edges = mutableListOf<HeatEdge>()
        (0..<height).forEach { y ->
            (0..<width).forEach { x ->
                val myPos = x to y
                val myPosIndex = myPos.toIndex(width)
                // Right
                var tarHeatSum = 0
                (1..min(3, width - 1 - x)).forEach { dx ->
                    val tarPos = x + dx to y
                    val tarPosIndex = tarPos.toIndex(width)
                    val tarHeat = grid[tarPosIndex].toString().toInt()
                    tarHeatSum += tarHeat
                    edges.add(
                        HeatEdge(
                            myPosIndex,
                            tarPosIndex,
                            Direction.Right,
                            tarHeatSum,
                            (1..dx).map { myPosIndex + it })
                    )
                }
                // Left
                tarHeatSum = 0
                (1..min(3, x)).forEach { dx ->
                    val tarPos = x - dx to y
                    val tarPosIndex = tarPos.toIndex(width)
                    val tarHeat = grid[tarPosIndex].toString().toInt()
                    tarHeatSum += tarHeat
                    edges.add(
                        HeatEdge(myPosIndex, tarPosIndex, Direction.Left, tarHeatSum,
                            (1..dx).map { myPosIndex - it })
                    )
                }
                // Down
                tarHeatSum = 0
                (1..min(3, height - 1 - y)).forEach { dy ->
                    val tarPos = x to y + dy
                    val tarPosIndex = tarPos.toIndex(width)
                    val tarHeat = grid[tarPosIndex].toString().toInt()
                    tarHeatSum += tarHeat
                    edges.add(
                        HeatEdge(myPosIndex, tarPosIndex, Direction.Down, tarHeatSum,
                            (1..dy).map { myPosIndex + (height * it) }
                        ))
                }
                // Up
                tarHeatSum = 0
                (1..min(3, y)).forEach { dy ->
                    val tarPos = x to y - dy
                    val tarPosIndex = tarPos.toIndex(width)
                    val tarHeat = grid[tarPosIndex].toString().toInt()
                    tarHeatSum += tarHeat
                    edges.add(
                        HeatEdge(
                            myPosIndex,
                            tarPosIndex,
                            Direction.Up,
                            tarHeatSum,
                            (1..dy).map { myPosIndex - (height * it) })
                    )
                }
            }
        }
        val rightEdges = edges.filter { it.dir == Direction.Right }.groupBy { it.from }
        val leftEdges = edges.filter { it.dir == Direction.Left }.groupBy { it.from }
        val downEdges = edges.filter { it.dir == Direction.Down }.groupBy { it.from }
        val upEdges = edges.filter { it.dir == Direction.Up }.groupBy { it.from }
        val edgesByDir = mapOf(
            Direction.Right to rightEdges,
            Direction.Left to leftEdges,
            Direction.Down to downEdges,
            Direction.Up to upEdges
        )
        val openList = LinkedList<PathContext>()
        val visited = mutableMapOf<DirectedPos, PathContext>()
        Direction.entries.forEach {
            visited[DirectedPos(0, it)] = PathContext(0, it, 0, sequenceOf(0))
        }
        edges.filter { it.from == 0 }.forEach {
            val ctx = PathContext(it.to, it.dir, it.heat, sequence {
                yield(0)
                yieldAll(it.positions)
            })
            openList.add(ctx)
            visited[DirectedPos(it.to, it.dir)] = ctx
        }
        var bestContext: PathContext? = null
        val goalPosIndex = ((width - 1) to (height - 1)).toIndex(width)
        while (openList.isNotEmpty()) {
            val current = openList.poll()
            if (visited[DirectedPos(current.pos, current.dir)]!!.heat != current.heat) {
                continue
            }
            if (current.pos == goalPosIndex) {
                if (bestContext == null || current.heat < bestContext.heat) {
                    bestContext = current
                }
                continue
            }
            Direction.entries.filterNot { it == current.dir }.flatMap { edgesByDir[it]!![current.pos] ?: emptyList() }
                .forEach {
                    val directedPos = DirectedPos(it.to, it.dir)
                    val tarContext = PathContext(it.to, it.dir, current.heat + it.heat, sequence {
                        yieldAll(current.history)
                        yieldAll(it.positions)
                    })
                    if (!visited.containsKey(directedPos) || tarContext.heat < visited[directedPos]!!.heat) {
                        if (!current.history.contains(it.to)) {
                            openList.add(tarContext)
                            visited[directedPos] = tarContext
                        }
                    }
                }
        }
        return bestContext?.heat ?: 0
    }

    override fun part2(input: String): Any {
        TODO("Not yet implemented")
    }

    data class HeatEdge(val from: Int, val to: Int, val dir: Direction, val heat: Int, val positions: List<Int>)

    enum class Direction {
        Right,
        Left,
        Down,
        Up
    }

    data class PathContext(val pos: Int, val dir: Direction, val heat: Int, val history: Sequence<Int>)

    data class DirectedPos(val pos: Int, val dir: Direction)
}