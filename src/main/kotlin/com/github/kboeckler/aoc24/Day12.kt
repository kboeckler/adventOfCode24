package com.github.kboeckler.aoc24

class Day12 : Day {
    override fun part1(input: String): Any {
        return splitIntoAreas(input)
            .sumOf { it.area() * it.perimeter() }
    }

    override fun part2(input: String): Any {
        TODO("Not yet implemented")
    }

    private fun splitIntoAreas(grid: String): List<Area> {
        TODO("Needs to be done")
    }

    private data class Area(val plots: List<Vector2D>) {

        fun area() = plots.size

        fun perimeter(): Int {
            val fencesSinglePlot = plots.flatMap { plot ->
                listOf(
                    plot to plot + Vector2D(1, 0),
                    plot to plot + Vector2D(0, 1),
                    plot + Vector2D(1, 0) to plot + Vector2D(1, 1),
                    plot + Vector2D(0, 1) to plot + Vector2D(1, 1)
                )
            }.toMap() // Nicht toMap
            TODO("Needs to be done")
        }
    }
}