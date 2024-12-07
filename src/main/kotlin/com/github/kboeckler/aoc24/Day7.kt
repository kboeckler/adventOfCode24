package com.github.kboeckler.aoc24

import java.math.BigInteger

class Day7 : Day {
    override fun part1(input: String): Any {
        return input.lines().map(::createEquationPair)
            .filter { equation ->
                solveRecursively(
                    equation.first,
                    equation.second,
                    listOf(Operator.PLUS, Operator.MULTIPLY)
                )
            }.sumOf { it.first }
    }

    override fun part2(input: String): Any {
        return input.lines().map(::createEquationPair)
            .filter { equation ->
                solveRecursively(
                    equation.first,
                    equation.second,
                    listOf(Operator.PLUS, Operator.MULTIPLY, Operator.CONCAT)
                )
            }.sumOf { it.first }
    }

    private fun createEquationPair(equation: String): Pair<BigInteger, List<BigInteger>> {
        val matches = Regex("(\\d+)").findAll(equation)
        return matches.first().value.toBigInteger() to matches.drop(1).map { it.value.toBigInteger() }.toList()
    }

    private fun solveRecursively(result: BigInteger, numbers: List<BigInteger>, operators: List<Operator>): Boolean {
        return if (numbers.size == 1) {
            numbers.first() == result
        } else if (numbers.first() > result) {
            false
        } else {
            operators.any { operator ->
                solveRecursively(
                    result,
                    listOf(operator.calc(numbers[0], numbers[1])) + numbers.drop(2),
                    operators
                )
            }
        }
    }

    enum class Operator() {
        PLUS, MULTIPLY, CONCAT;

        fun calc(a: BigInteger, b: BigInteger): BigInteger {
            return when (this) {
                PLUS -> a + b
                MULTIPLY -> a * b
                CONCAT -> "$a$b".toBigInteger()
            }
        }
    }
}