package de.kevinboeckler.aoc23

import java.math.BigInteger

class Day5 : Day() {

    val todo = true

    override fun part1(input: String): Any {
        if (todo) {
            return "?"
        }
        val seeds = "(\\d+)".toRegex().findAll(input.substringBefore("\n")).map { it.value.toBigInteger() }
            .map { BigRange(it, 1) }.toList()
        val mappings = "(.+)-to-(.+) map:\\n([\\d\\s]*)".toRegex().findAll(input).map { it.groupValues }
            .flatMap { match ->
                val mappings = numberLinesToMappings(match[3])
                mappings.map {
                    RangeMap(match[1], match[2], it.first, it.second, it.third)
                }
            }.onEach { println(it) }
        return seeds.map { search(it, "seed", "location", mappings) }.flatten().minOfOrNull { it.from }!!
    }

    override fun part2(input: String): Any {
        if (todo) {
            return "?"
        }
        val seeds = "(\\d+)\\s(\\d+)".toRegex().findAll(input.substringBefore("\n"))
            .map { it.groupValues[1].toBigInteger() to it.groupValues[2].toBigInteger() }
            .flatMap { intsFromTo(it.first, it.second) }
            // TODO create big ranges so it makes sense
            .map { BigRange(it, 1) }
        val mappings = "(.+)-to-(.+) map:\\n([\\d\\s]*)".toRegex().findAll(input).map { it.groupValues }
            .flatMap { match ->
                val mappings = numberLinesToMappings(match[3])
                mappings.map {
                    RangeMap(match[1], match[2], it.first, it.second, it.third)
                }
            }
        return seeds.map { search(it, "seed", "location", mappings) }.flatten().minOfOrNull { it.from }!!
    }

    data class BigRange(val from: BigInteger, val length: BigInteger) {
        constructor(from: Int, length: Int) : this(from.toBigInteger(), length.toBigInteger())
        constructor(from: BigInteger, length: Int) : this(from, length.toBigInteger())

        private val end: BigInteger = this.from + this.length - 1.toBigInteger()

        fun subRangesOf(other: BigRange): List<BigRange> {
            if (other == this) {
                return listOf(this)
            }
            if (other.end <= this.end && other.from >= this.from) {
                return listOf(BigRange(other.from, other.end - other.from + 1.toBigInteger()))
            }
            if (other.from < this.from && other.end >= this.from && other.end <= this.end) {
                return listOf(BigRange(other.from, this.from - other.from), this)
            }
            if (other.end > this.end && other.from <= this.end && other.from >= this.from) {
                return listOf(this, BigRange(this.end + 1.toBigInteger(), other.end - this.end))
            }
            if (other.from < this.from && other.end > this.end) {
                return listOf(
                    BigRange(other.from, this.from - other.from),
                    this,
                    BigRange(this.end + 1.toBigInteger(), other.end - this.end)
                )
            }
            return emptyList()
        }

    }

    data class RangeMap(
        val sourceType: String,
        val destType: String,
        val destRangeStart: BigInteger,
        val sourceRangeStart: BigInteger,
        val rangeLength: BigInteger
    )

    private fun numberLinesToMappings(numberLines: String): List<Triple<BigInteger, BigInteger, BigInteger>> {
        return "(\\d+)\\s(\\d+)\\s(\\d+)".toRegex().findAll(numberLines)
            .map { m ->
                Triple(
                    m.groupValues[1].toBigInteger(),
                    m.groupValues[2].toBigInteger(),
                    m.groupValues[3].toBigInteger()
                )
            }.toList()
    }

    private fun search(
        inRange: BigRange,
        sourceType: String,
        destType: String,
        mappings: Sequence<RangeMap>
    ): List<BigRange> {
        val mappingsBySourceType = mappings.groupBy { it.sourceType }
        if (sourceType == destType) {
            return listOf(inRange)
        }
        val rangeMapsOfSourceType = mappingsBySourceType[sourceType]!!
        val splitInRanges =
            rangeMapsOfSourceType.flatMap { inRange.subRangesOf(BigRange(it.sourceRangeStart, it.rangeLength)) }
                .distinct()
        return splitInRanges.flatMap { range ->
            // TODO range.from is wrong right now
            val outRange =
                rangeMapsOfSourceType.filter { range.from in it.sourceRangeStart..<it.sourceRangeStart + it.rangeLength }
                    .map { it.destRangeStart + range.from - it.sourceRangeStart }.map { BigRange(it, 1) }
                    .firstOrNull()
                    ?: inRange
            search(outRange, rangeMapsOfSourceType.first().destType, destType, mappings)
        }.toList()
    }

    private fun intsFromTo(from: BigInteger, range: BigInteger): List<BigInteger> {
        val ints = mutableListOf<BigInteger>()
        var i = from
        while (i < from + range) {
            ints.add(i)
            i++
        }
        return ints
    }
}
