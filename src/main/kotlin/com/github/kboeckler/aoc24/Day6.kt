package com.github.kboeckler.aoc24

import java.util.function.Predicate

class Day6 : Day {

    override fun part1(input: String): Any {
        val area = input.lines()
        val guardPos = findGuard(area)
        val path = findPath(
            guardPos,
            { isInsideBounds(it, area) },
            { area[it.second][it.first] == '#' })
        return path!!.distinct().size
    }

    override fun part2(input: String): Any {
        val area = input.lines()
        val guardPos = findGuard(area)
        val path = findPath(
            guardPos,
            { isInsideBounds(it, area) },
            { area[it.second][it.first] == '#' })
        return path!!.drop(1).distinct().count { pos ->
            doesNewObstructionCreateInfinitePath(area, pos, guardPos)
        }
    }

    private fun doesNewObstructionCreateInfinitePath(
        area: List<String>, newObstructionPos: Pair<Int, Int>, guardPos: Pair<Int, Int>
    ): Boolean {
        return findPath(
            guardPos,
            { isInsideBounds(it, area) },
            { area[it.second][it.first] == '#' || it == newObstructionPos }) == null
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

    private fun findPath(
        guardPos: Pair<Int, Int>,
        inBoundsPredicate: Predicate<Pair<Int, Int>>,
        shouldRotatePredicate: Predicate<Pair<Int, Int>>
    ): List<Pair<Int, Int>>? {
        val path = mutableListOf(guardPos)
        var currentPos = guardPos
        var currentDir = Direction.UP
        val visited = mutableMapOf<Pair<Int, Int>, MutableSet<Direction>>()
        visited.computeIfAbsent(currentPos, { mutableSetOf() }).add(currentDir)
        var currentNextPos = currentDir.next(currentPos)
        while (inBoundsPredicate.test(currentNextPos)) {
            if (visited[currentNextPos]?.contains(currentDir) == true) {
                return null
            }
            if (shouldRotatePredicate.test(currentNextPos)) {
                currentDir = currentDir.rotateRight()
            } else {
                currentPos = currentNextPos
                path.add(currentPos)
                visited.computeIfAbsent(currentPos, { mutableSetOf() }).add(currentDir)
            }
            currentNextPos = currentDir.next(currentPos)
        }
        return path
    }

    private fun isInsideBounds(pos: Pair<Int, Int>, area: List<String>): Boolean {
        return pos.first in 0..area.lastIndex && pos.second in 0..area.first().lastIndex
    }

}