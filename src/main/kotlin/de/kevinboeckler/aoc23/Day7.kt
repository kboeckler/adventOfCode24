package de.kevinboeckler.aoc23

class Day7 : Day() {

    override fun part1(input: String): Any {
        return input.lines().asSequence().filter { it.isNotBlank() }.map { it.split(" ") }
            .map { Hand(it[0], it[1].toInt()) }
            .sortedWith(this::compareHands)
            .mapIndexed { i, c -> (i + 1) * c.bid }.sum()
    }

    override fun part2(input: String): Any {
        return input.replace("J", "H").lines().asSequence().filter { it.isNotBlank() }.map { it.split(" ") }
            .map { Hand(it[0], it[1].toInt()) }
            .sortedWith(this::compareHands)
            .mapIndexed { i, c -> (i + 1) * c.bid }.sum()
    }

    private fun compareHands(one: Hand, another: Hand): Int {
        val typeCompare = getHandTypeOf(one).compareTo(getHandTypeOf(another))
        if (typeCompare != 0) {
            return -1 * typeCompare
        }
        val oneHand = mapCardsToInts(one.cards)
        val anotherHand = mapCardsToInts(another.cards)
        return oneHand.mapIndexed { i, v -> v.compareTo(anotherHand[i]) }.firstOrNull { it != 0 } ?: 0
    }

    private fun getHandTypeOf(hand: Hand): HandType {
        val heroes = hand.cards.count { it == 'H' }
        val samePerChar = hand.cards.replace("H", "").groupBy { it }.mapValues { it.value.size }
        if (samePerChar.containsValue(3) && samePerChar.containsValue(2)) {
            return HandType.FullHouse
        }
        if (samePerChar.values.filter { it == 2 }.size == 2) {
            return if (heroes == 1) HandType.FullHouse else HandType.TwoPair
        }
        return when (samePerChar.values.maxOrNull()) {
            null -> HandType.Five
            5 -> HandType.Five
            4 -> if (heroes == 1) HandType.Five else HandType.Four
            3 -> if (heroes == 2) HandType.Five else if (heroes == 1) HandType.Four else HandType.Three
            2 -> if (heroes == 3) HandType.Five else if (heroes == 2) HandType.Four else if (heroes == 1) HandType.Three else HandType.Pair
            1 -> if (heroes == 4) HandType.Five else if (heroes == 3) HandType.Four else if (heroes == 2) HandType.Three else if (heroes == 1) HandType.Pair else HandType.High
            else -> throw IllegalArgumentException("Illegal hand $hand")
        }
    }

    private fun mapCardsToInts(cards: String): List<Int> {
        return cards.map { cardTypes.size - cardTypes.indexOf(it) }
    }

    data class Hand(val cards: String, val bid: Int)

    enum class HandType {
        Five,
        Four,
        FullHouse,
        Three,
        TwoPair,
        Pair,
        High
    }

    private val cardTypes = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'H')

}