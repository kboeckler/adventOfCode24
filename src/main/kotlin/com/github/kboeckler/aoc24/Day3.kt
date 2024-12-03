package com.github.kboeckler.aoc24

import kotlin.sequences.map
import kotlin.sequences.mapNotNull
import kotlin.sequences.sum

class Day3 : Day {
    override fun part1(input: String): Any? {
        return input.let {
            Regex("(mul\\(\\d{1,3}\\,\\d{1,3}\\))").findAll(it)
        }.map { it.value }.mapNotNull {
            Regex("mul\\((\\d*)\\,(\\d*)\\)").matchEntire(it)
        }.map { it.groupValues[1].toInt() to it.groupValues[2].toInt() }.map { it.first * it.second }.sum()
    }

    override fun part2(input: String): Any? {
        val mulStatements = input.let {
            Regex("(mul\\(\\d{1,3}\\,\\d{1,3}\\))").findAll(it).toList()
        }
        val instructionStatements = input.let {
            Regex("(do\\(\\)|don't\\(\\))").findAll(it).toList()
        }
        return mulStatements.filterIndexed { idx, mul ->
            instructionStatements.lastOrNull { instr ->
                if (idx == 0) true else instr.range.first > mulStatements[idx - 1].range.last && instr.range.last < mul.range.first
            }.also { println("${mul.value}: ${it?.value ?: "noe"}") }?.value != ("don't()" ?: true)
        }.onEach { println(it.value) }.map { it.value }.mapNotNull {
            Regex("mul\\((\\d*)\\,(\\d*)\\)").matchEntire(it)
        }.map { it.groupValues[1].toInt() to it.groupValues[2].toInt() }.map { it.first * it.second }.sum()
    }
}