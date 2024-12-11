package com.github.kboeckler.aoc24

import java.math.BigInteger

class Day11 : Day {
    override fun part1(input: String): Any {
        val trees = input.split(" ").map { it.toBigInteger() }.map { Tree(Leaf(it)) }
        var stones = input.split(" ").map { it.toBigInteger() }
        repeat(25) {
            trees.forEach { it.applyRules() }
            stones = stones.flatMap(::applyRules)
        }
        //println(trees)
        return stones.size
    }

    override fun part2(input: String): Any {
        var stones = input.split(" ").map { it.toBigInteger() }
        repeat(75) {
            stones = stones.flatMap(::applyRules)
        }
        return stones.size
    }

    private fun applyRules(stone: BigInteger): List<BigInteger> {
        if (stone == 0.toBigInteger()) {
            return listOf(1.toBigInteger())
        }
        val stringedStone = stone.toString()
        if (stringedStone.length % 2 == 0) {
            val halvesOfStringedStone = stringedStone.withIndex().partition { it.index < stringedStone.length / 2 }
            return listOf(
                halvesOfStringedStone.first.map { it.value }.joinToString("").toBigInteger(),
                halvesOfStringedStone.second.map { it.value }.joinToString("").toBigInteger()
            )
        }
        return listOf(stone * 2024.toBigInteger())
    }

    private data class Tree(var leaves: List<Leaf>) {
        constructor(leaf: Leaf) : this(listOf(leaf))

        fun applyRules() {
            leaves = leaves.map { it.applyRules() }
        }
    }

    private data class Leaf(val tree: Tree?, val value: BigInteger?) {
        constructor(value: BigInteger) : this(null, value)
        constructor(tree: Tree) : this(tree, null)

        fun applyRules(): Leaf {
            if (value != null) {
                val leaves = applyRules(value).map { Leaf(it) }
                if (leaves.size == 1) {
                    return leaves.first()
                } else {
                    return Leaf(Tree(leaves))
                }
            } else {
                // has tree
                tree!!.applyRules()
                return Leaf(tree, null)
            }
        }

        private fun applyRules(stone: BigInteger): List<BigInteger> {
            if (stone == 0.toBigInteger()) {
                return listOf(1.toBigInteger())
            }
            val stringedStone = stone.toString()
            if (stringedStone.length % 2 == 0) {
                val halvesOfStringedStone = stringedStone.withIndex().partition { it.index < stringedStone.length / 2 }
                return listOf(
                    halvesOfStringedStone.first.map { it.value }.joinToString("").toBigInteger(),
                    halvesOfStringedStone.second.map { it.value }.joinToString("").toBigInteger()
                )
            }
            return listOf(stone * 2024.toBigInteger())
        }
    }
}