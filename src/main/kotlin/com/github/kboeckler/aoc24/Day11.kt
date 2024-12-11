package com.github.kboeckler.aoc24

import java.math.BigInteger

typealias Day11Cache = MutableMap<Pair<Int, BigInteger>, BigInteger>

private fun cache(): Day11Cache = mutableMapOf<Pair<Int, BigInteger>, BigInteger>()

class Day11 : Day {
    override fun part1(input: String): Any {
        return input.split(" ").map { it.toBigInteger() }.sumOf { blinkStoneRecursively(it, 25, cache()) }
    }

    override fun part2(input: String): Any {
        return input.split(" ").map { it.toBigInteger() }.sumOf { blinkStoneRecursively(it, 75, cache()) }
    }

    private fun blinkStoneRecursively(stone: BigInteger, maxSteps: Int, cache: Day11Cache): BigInteger {
        if (cache.containsKey(maxSteps to stone)) {
            return cache[maxSteps to stone]!!
        }
        val stoneValue = splitCountRecursively(maxSteps, stone, cache)
        cache[maxSteps to stone] = stoneValue
        return stoneValue
    }

    private fun splitCountRecursively(maxSteps: Int, stone: BigInteger, cache: Day11Cache): BigInteger {
        return if (maxSteps == 0) {
            BigInteger.ONE
        } else if (stone == BigInteger.ZERO) {
            blinkStoneRecursively(BigInteger.ONE, maxSteps - 1, cache)
        } else {
            val stringedStone = stone.toString()
            if (stringedStone.length % 2 == 0) {
                val firstHalf = stringedStone.substring(0, stringedStone.length / 2)
                val secondHalf = stringedStone.substring(stringedStone.length / 2, stringedStone.length)
                blinkStoneRecursively(
                    firstHalf.toBigInteger(),
                    maxSteps - 1,
                    cache
                ) + blinkStoneRecursively(secondHalf.toBigInteger(), maxSteps - 1, cache)
            } else {
                blinkStoneRecursively(stone * 2024.toBigInteger(), maxSteps - 1, cache)
            }
        }
    }

}