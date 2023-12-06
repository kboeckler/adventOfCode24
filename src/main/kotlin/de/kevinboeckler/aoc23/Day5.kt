package de.kevinboeckler.aoc23

import java.math.BigInteger

class Day5 : Day() {

    override fun part1(input: String): Any {
        val seeds = "(\\d+)".toRegex().findAll(input.substringBefore("\n")).map { it.value.toBigInteger() }
            .map { BigRange(it, 1) }.toList()
        val mappings = "(.+)-to-(.+) map:\\n([\\d\\s]*)".toRegex().findAll(input).map { it.groupValues }
            .flatMap { match ->
                val mappings = numberLinesToMappings(match[3])
                mappings.map {
                    RangeMap(match[1], match[2], it.first, it.second, it.third)
                }
            }
        return seeds.map { search(it, "seed", "location", mappings) }.flatten().minOfOrNull { it.from }!!
    }

    override fun part2(input: String): Any {
        val seeds = "(\\d+)\\s(\\d+)".toRegex().findAll(input.substringBefore("\n"))
            .map { it.groupValues[1].toBigInteger() to it.groupValues[2].toBigInteger() }
            .map { BigRange(it.first, it.second) }
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

        fun intersect(other: BigRange): BigRange? {
            if (other == this) {
                return this
            }
            if (other.end <= this.end && other.from >= this.from) {
                return BigRange(other.from, other.end - other.from + 1.toBigInteger())
            }
            if (other.from < this.from && other.end >= this.from && other.end <= this.end) {
                // intersects left
                return BigRange(this.from, other.end - this.from + 1.toBigInteger())
            }
            if (other.end > this.end && other.from <= this.end && other.from >= this.from) {
                // intersects right
                return BigRange(other.from, this.end - other.from + 1.toBigInteger())
            }
            if (other.from < this.from && other.end > this.end) {
                return this
            }
            return null
        }

    }

    data class RangeMap(
        val sourceType: String,
        val destType: String,
        val destRangeStart: BigInteger,
        val sourceRangeStart: BigInteger,
        val rangeLength: BigInteger
    ) {
        fun maps(target: BigRange): Boolean {
            return target.from in sourceRangeStart..<sourceRangeStart + rangeLength
        }

        fun map(target: BigRange): BigRange {
            val newFrom = target.from - sourceRangeStart + destRangeStart
            return BigRange(newFrom, target.length)
        }

    }

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
            rangeMapsOfSourceType.mapNotNull { inRange.intersect(BigRange(it.sourceRangeStart, it.rangeLength)) }
                .ifEmpty { listOf(inRange) }
        return splitInRanges.flatMap { range ->
            val outRange =
                rangeMapsOfSourceType.filter { it.maps(range) }
                    .map { it.map(range) }
                    .firstOrNull()
                    ?: range
            search(outRange, rangeMapsOfSourceType.first().destType, destType, mappings)
        }.toList()
    }
}
