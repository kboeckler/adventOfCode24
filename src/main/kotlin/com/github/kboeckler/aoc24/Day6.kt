package com.github.kboeckler.aoc24

class Day6 : Day {
    override fun part1(input: String): Any {
        val area = input.lines()
        val guardPos = findGuard(area)
        val path = findPath(guardPos, area)
        return path.distinct().size
    }

    override fun part2(input: String): Any {
        TODO("Not yet implemented")
    }

    private fun findGuard(area: List<String>): Pair<Int, Int> {
        return area.indices.firstNotNullOf { y ->
            area[y].indices.map { x ->
                x to y
            }.firstOrNull { pos ->
                area[pos.second][pos.first] == '^'
            }
        }
    }

    private fun findPath(guardPos: Pair<Int, Int>, area: List<String>): List<Pair<Int, Int>> {
        val path = mutableListOf<Pair<Int, Int>>(guardPos)
        var currentPos = guardPos
        var currentDir = Direction.UP
        var currentNextPos = currentDir.next(currentPos)
        while (isInsideBounds(currentNextPos, area)) {
            if (area[currentNextPos.second][currentNextPos.first] == '#') {
                currentDir = currentDir.rotateRight()
            } else {
                currentPos = currentNextPos
                path.add(currentPos)
            }
            currentNextPos = currentDir.next(currentPos)
        }
        return path
    }

    private fun isInsideBounds(pos: Pair<Int, Int>, area: List<String>): Boolean {
        return pos.first in 0..area.lastIndex && pos.second in 0..area.first().lastIndex
    }

    private enum class Direction {
        UP, RIGHT, DOWN, LEFT;

        fun next(pos: Pair<Int, Int>): Pair<Int, Int> {
            return when (this) {
                UP -> pos.first to pos.second - 1
                Direction.RIGHT -> pos.first + 1 to pos.second
                Direction.DOWN -> pos.first to pos.second + 1
                Direction.LEFT -> pos.first - 1 to pos.second
            }
        }

        fun rotateRight(): Direction {
            return when (this) {
                UP -> Direction.RIGHT
                Direction.RIGHT -> DOWN
                Direction.DOWN -> LEFT
                Direction.LEFT -> UP
            }
        }
    }
}