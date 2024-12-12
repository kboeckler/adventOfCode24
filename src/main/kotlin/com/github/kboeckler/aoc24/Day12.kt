package com.github.kboeckler.aoc24

class Day12 : Day {
    override fun part1(input: String): Any {
        return splitIntoAreas(input)
            .sumOf { it.area() * it.perimeter() }
    }

    override fun part2(input: String): Any {
        return splitIntoAreas(input)
            .sumOf { it.area() * it.sides() }
    }

    private fun splitIntoAreas(input: String): List<Area> {
        val width = input.indexOf("\n")
        val height = input.count { it == '\n' } + 1
        val grid = input.replace("\n", "")
        val areas = mutableListOf<Area>()
        val unvisited = mutableSetOf<Vector2D>()
        (0 until width * height).map { Vector2D(it.toCoords(width)) }.forEach(unvisited::add)
        while (unvisited.isNotEmpty()) {
            val currentArea = mutableListOf<Vector2D>()
            val openList = mutableListOf<Vector2D>()
            val first = unvisited.first()
            openList.add(first)
            unvisited.remove(first)
            while (openList.isNotEmpty()) {
                val current = openList.removeFirst()
                currentArea.add(current)
                Direction.entries.map { it.next(current) }
                    .filter { it.inBoundsOfGrid(width, height) }
                    .filter { unvisited.contains(it) }
                    .filter { grid[it.toIndex(width)] == grid[current.toIndex(width)] }
                    .forEach {
                        openList.add(it)
                        unvisited.remove(it)
                    }
            }
            areas.add(Area(currentArea))
        }
        return areas
    }

    private data class Area(val plots: List<Vector2D>) {

        fun area() = plots.size

        fun perimeter() = outerFences().size

        fun outerFences(): List<Fence> {
            return plots.flatMap { plot ->
                listOf(
                    Fence(plot, plot + Vector2D(1, 0)),
                    Fence(plot, plot + Vector2D(0, 1)),
                    Fence(plot + Vector2D(1, 0), plot + Vector2D(1, 1)),
                    Fence(plot + Vector2D(0, 1), plot + Vector2D(1, 1))
                )
            }.groupBy { it }.values.filter { it.size == 1 }.map { it.first() }
        }

        fun sides(): Int {
            val fences = outerFences()
            var currentIndex = 0
            while (currentIndex <= fences.size) {
                TODO()
            }
            return 0
        }
    }

    private data class Fence(val a: Vector2D, val b: Vector2D) {
        private val direction: Direction = Direction.of(b - a)
    }
}