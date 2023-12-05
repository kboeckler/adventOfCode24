package de.kevinboeckler.aoc23

import java.math.BigInteger

class Day5 : Day() {
    override fun part1(input: String): Any {
        val seeds = "(\\d+)".toRegex().findAll(input.substringBefore("\n")).map { it.value.toBigInteger() }.toList()
        val mappings = "(.+)-to-(.+) map:\\n([\\d\\s]*)".toRegex().findAll(input).map { it.groupValues }
            .flatMap { match ->
                val mappings = numberLinesToMappings(match[3])
                mappings.map {
                    RangeMap(match[1], match[2], it.first, it.second, it.third)
                }
            }
        return seeds.minOfOrNull { search(it, "seed", "location", mappings) }!!
    }

    override fun part2(input: String): Any {
        val seeds = "(\\d+)\\s(\\d+)".toRegex().findAll(input.substringBefore("\n"))
            .map { it.groupValues[1].toBigInteger() to it.groupValues[2].toBigInteger() }
            .flatMap { intsFromTo(it.first, it.second) }
        val mappings = "(.+)-to-(.+) map:\\n([\\d\\s]*)".toRegex().findAll(input).map { it.groupValues }
            .flatMap { match ->
                val mappings = numberLinesToMappings(match[3])
                mappings.map {
                    RangeMap(match[1], match[2], it.first, it.second, it.third)
                }
            }
        return seeds.minOfOrNull { search(it, "seed", "location", mappings) }!!
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

    private fun search(id: BigInteger, sourceType: String, destType: String, mappings: Sequence<RangeMap>): BigInteger {
        val mappingsBySourceType = mappings.groupBy { it.sourceType }
        if (sourceType == destType) {
            return id
        }
        val rangeMapsOfSourceType = mappingsBySourceType[sourceType]!!
        val destId = rangeMapsOfSourceType.filter { id in it.sourceRangeStart..<it.sourceRangeStart + it.rangeLength }
            .map { it.destRangeStart + id - it.sourceRangeStart }.firstOrNull() ?: id
        return search(destId, rangeMapsOfSourceType.first().destType, destType, mappings)
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
