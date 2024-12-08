package com.github.kboeckler.aoc24

import com.github.kboeckler.aoc24.Day8.Vector2D
import kotlin.math.max

class Day8 : Day {
    override fun part1(input: String): Any {
        val width = input.indexOf("\n")
        val height = input.count { it == '\n' } + 1
        val grid = input.replace("\n", "")

        return solve(grid, width, height) { antennaPair ->
            antiNodes(antennaPair, 1, 2)
        }
    }

    override fun part2(input: String): Any {
        val width = input.indexOf("\n")
        val height = input.count { it == '\n' } + 1
        val grid = input.replace("\n", "")

        return solve(grid, width, height) { antennaPair ->
            antiNodes(antennaPair, 0, max(width, height))
        }
    }

    private fun solve(
        grid: String,
        width: Int,
        height: Int,
        mapper: (Pair<Vector2D, Vector2D>) -> Iterable<Vector2D>
    ): Int {
        return grid.filter { it != '.' }.toSet().flatMap { freq ->
            findAllPairsOfThatFrequency(grid, freq)
        }.map { mapIndexPairsToVectorPairs(it, width) }
            .flatMap { mapper.invoke(it) }.filter { it.inBoundsOfGrid(width, height) }.distinct().size
    }

    private fun findAllPairsOfThatFrequency(grid: String, freq: Char): List<Pair<Int, Int>> {
        val cellsWithFreq = grid.withIndex().filter { it.value == freq }.map { it.index }
        return cellsWithFreq.indices.flatMap { idxCells ->
            (idxCells + 1 until cellsWithFreq.size).map { otherIdxCells ->
                cellsWithFreq[idxCells] to cellsWithFreq[otherIdxCells]
            }
        }
    }

    private fun mapIndexPairsToVectorPairs(pair: Pair<Int, Int>, width: Int): Pair<Vector2D, Vector2D> =
        Vector2D(pair.first.toCoords(width)) to Vector2D(pair.second.toCoords(width))

    private fun antiNodes(
        antennaPair: Pair<Vector2D, Vector2D>,
        startInclusive: Int,
        endExclusive: Int
    ): List<Vector2D> {
        val vectorFirst = antennaPair.first - antennaPair.second
        val vectorSecond = antennaPair.second - antennaPair.first
        return (startInclusive until endExclusive).flatMap {
            listOf(
                antennaPair.first + it * vectorFirst,
                antennaPair.second + it * vectorSecond
            )
        }
    }

    private data class Vector2D(val x: Int, val y: Int) {
        constructor(pair: Pair<Int, Int>) : this(pair.first, pair.second)

        fun inBoundsOfGrid(width: Int, height: Int) = this.x >= 0 && this.y >= 0 && this.x < width && this.y < height
    }

    private operator fun Vector2D.plus(other: Vector2D) = Vector2D(this.x + other.x, this.y + other.y)

    private operator fun Vector2D.minus(other: Vector2D) = Vector2D(this.x - other.x, this.y - other.y)

    private operator fun Int.times(vector: Vector2D) = Vector2D(this * vector.x, this * vector.y)
}