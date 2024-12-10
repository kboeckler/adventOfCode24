package com.github.kboeckler.aoc24

class Day10 : Day {
    override fun part1(input: String): Any {
        val grid = input.replace("\n", "")
        val width = input.indexOf("\n")
        val height = input.count { it == '\n' } + 1

        val trailheads = grid.withIndex().filter { it.value == '0' }.map { headWithIndex ->
            Vector2D(headWithIndex.index.toCoords(width))
        }
        val tops = grid.withIndex().filter { it.value == '9' }.map { headWithIndex ->
            Vector2D(headWithIndex.index.toCoords(width))
        }
        return trailheads.sumOf { findTrails(it, tops, grid, width, height) }
    }

    override fun part2(input: String): Any {
        TODO("Not yet implemented")
    }

    private fun findTrails(head: Vector2D, tops: List<Vector2D>, grid: String, width: Int, height: Int): Int {
        val targets = tops.toSet()
        var trails = 0
        val visited = mutableSetOf<Vector2D>()
        val openList = mutableListOf<Vector2D>()
        openList.add(head)
        while (openList.isNotEmpty()) {
            val current = openList[0]
            openList.removeAt(0)
            Direction.values().forEach { dir ->
                val nextPos = dir.next(current)
                if (nextPos.inBoundsOfGrid(width, height)) {
                    val heightNext = "${grid[nextPos.toIndex(width)]}".toInt()
                    val heightCurrent = "${grid[current.toIndex(width)]}".toInt()
                    val slope = heightNext - heightCurrent
                    if (slope == 1 && !visited.contains(nextPos)) {
                        if (targets.contains(nextPos)) {
                            trails++
                        } else {
                            openList.add(nextPos)
                        }
                        visited.add(nextPos)
                    }
                }
            }
        }
        return trails
    }
}