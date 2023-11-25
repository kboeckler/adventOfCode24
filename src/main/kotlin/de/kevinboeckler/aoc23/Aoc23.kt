package de.kevinboeckler.aoc23

import org.reflections.Reflections
import java.io.File

fun main() {
    Reflections("de.kevinboeckler.aoc23")
        .getSubTypesOf(Day::class.java)
        .filterNot { it.equals(EmptyDay::class.java) }
        .sortedBy { it.simpleName }
        .forEach(::runSolution)
}

private fun runSolution(solutionClass: Class<out Day>) {
    val errors = mutableListOf<Throwable>()
    val solution: Day = try {
        solutionClass.getDeclaredConstructor().newInstance()!!
    } catch (err: Throwable) {
        errors.add(err)
        EmptyDay()
    }
    val input = try {
        solution.readInput()
    } catch (err: Throwable) {
        errors.add(err)
        ""
    }
    val result1: Any = try {
        solution.part1(input)
    } catch (err: Throwable) {
        errors.add(err)
        "_"
    }
    val result2: Any = try {
        solution.part2(input)
    } catch (err: Throwable) {
        errors.add(err)
        "_"
    }
    println("%s: %s, %s".format(solution.name(), result1, result2))
    errors.forEach { err -> err.printStackTrace() }
}

abstract class Day(name: String?) {

    private val name: String?

    init {
        this.name = name
    }

    constructor() : this(null) {
    }

    fun name(): String {
        return this.name ?: this.javaClass.simpleName
    }

    abstract fun part1(input: String): Any
    abstract fun part2(input: String): Any
    open fun readInput(): String {
        return File("src/main/resources/%s.txt".format(name())).readText()
    }
}

class EmptyDay : Day() {
    override fun part1(input: String): Any {
        return "_"
    }

    override fun part2(input: String): Any {
        return "_"
    }

    override fun readInput(): String {
        return ""
    }

}
