package com.github.kboeckler.aoc24

class Day3 : Day {

    // matches all mul(a,b) statements in a string
    private fun matchMul() = Regex("(mul\\(\\d{1,3},\\d{1,3}\\))")

    // matches a and b in a mul(a,b) string
    private fun matchDigitsInMul() = Regex("mul\\((\\d*),(\\d*)\\)")

    // matches all do() or don't() statements in a string
    private fun matchDoOrDont() = Regex("(do\\(\\)|don't\\(\\))")

    override fun part1(input: String): Any {
        return input.let {
            matchMul().findAll(it)
        }.map { it.value }.mapNotNull {
            matchDigitsInMul().matchEntire(it)
        }.map { it.groupValues[1].toInt() to it.groupValues[2].toInt() }
            .map { it.first * it.second }.sum()
    }

    override fun part2(input: String): Any {
        val mulStatements = input.let {
            matchMul().findAll(it).toList()
        }
        val instructionStatements = input.let {
            matchDoOrDont().findAll(it).toList()
        }
        return mulStatements.asSequence().filter { mul ->
            instructionStatements.lastOrNull { instr ->
                instr.range.last < mul.range.first
            }?.value != "don't()"
        }.map { it.value }.mapNotNull {
            matchDigitsInMul().matchEntire(it)
        }.map { it.groupValues[1].toInt() to it.groupValues[2].toInt() }
            .sumOf { it.first * it.second }
    }
}