package de.kevinboeckler.aoc23

import kotlin.math.pow

class Day4 : Day() {
    override fun part1(input: String): Any {
        return parseCards(input)
            .map { winningAmount(it) }
            .map { 2.toDouble().pow(it - 1) }.sumOf { it.toInt() }
    }

    override fun part2(input: String): Any {
        val originalCards = parseCards(input)
        val idToCard = originalCards.groupBy { it.id }.mapValues { it.value[0] }
        val cardsToProcess = originalCards.toMutableList()
        var currentPointer = 0
        while (currentPointer < cardsToProcess.size) {
            val card = cardsToProcess[currentPointer++]
            (card.id + 1..card.id + winningAmount(card))
                .map { idToCard[it]!! }
                .forEach { cardsToProcess.add(it) }
        }
        return currentPointer
    }

    data class Card(val id: Int, val numbers: Set<Int>, val myNumbers: Set<Int>)

    private fun parseCards(input: String) = input.lines().asSequence()
        .map {
            "Card *(\\d+):([\\d\\s]*)\\|([\\d\\s]*)".toRegex().matchEntire(it)!!
        }
        .map {
            Card(
                it.groupValues[1].toInt(),
                toInts(it.groupValues[2]).toSet(),
                toInts(it.groupValues[3]).toSet()
            )
        }

    private fun winningAmount(card: Card): Int {
        return card.myNumbers.count { card.numbers.contains(it) }
    }

    private fun toInts(intString: String) = "(\\d+)".toRegex().findAll(intString).map {
        it.value.toInt()
    }

}
