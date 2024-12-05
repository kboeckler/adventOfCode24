package com.github.kboeckler.aoc24

import kotlin.math.max
import kotlin.math.min

class Day5 : Day {
    override fun part1(input: String): Any {
        val inputParts = input.split("\n\n")
        val rules = parseRules(inputParts)
        val updates = parseUpdates(inputParts)
        var result = 0
        for (update in updates) {
            if (update.fulfillsRules(rules)) {
                result += update.middlePage()
            }
        }
        return result
    }

    override fun part2(input: String): Any {
        val inputParts = input.split("\n\n")
        val rules = parseRules(inputParts)
        val updates = parseUpdates(inputParts)
        var result = 0
        for (update in updates) {
            if (!update.fulfillsRules(rules)) {
                result += update.getCorrectedUpdate(rules).middlePage()
            }
        }
        return result
    }

    private fun parseRules(inputParts: List<String>): List<Rule> {
        val rulesRaws = inputParts[0]
        val rules = mutableListOf<Rule>()
        for (ruleRaw in rulesRaws.lines()) {
            val ruleParts = ruleRaw.split("|")
            rules.add(Rule(ruleParts[0].toInt(), ruleParts[1].toInt()))
        }
        return rules
    }

    private fun parseUpdates(inputParts: List<String>): List<Update> {
        val updatesRaws = inputParts[1]
        val updates = mutableListOf<Update>()
        for (updateRaw in updatesRaws.lines()) {
            val updateParts = updateRaw.split(",")
            val updatePages = mutableListOf<Int>()
            for (updatePartPart in updateParts) {
                updatePages.add(updatePartPart.toInt())
            }
            updates.add(Update(updatePages))
        }
        return updates
    }

    private data class Rule(val first: Int, val second: Int) {
        fun firstInvalidPagePair(update: Update): Pair<Int, Int>? {
            val firstPagePos = update.pages.indexOf(first)
            val secondPagePos = update.pages.indexOf(second)
            if (firstPagePos == -1 || secondPagePos == -1) {
                return null
            }
            if (firstPagePos <= secondPagePos) {
                return null
            }
            return min(firstPagePos, secondPagePos) to max(firstPagePos, secondPagePos)
        }
    }

    private data class Update(val pages: List<Int>) {
        fun fulfillsRules(rules: List<Rule>): Boolean {
            for (rule in rules) {
                if (rule.firstInvalidPagePair(this) != null) {
                    return false
                }
            }
            return true
        }

        fun getCorrectedUpdate(rules: List<Rule>): Update {
            val newPages = pages.toMutableList()
            var pagePairToRepair = Update(newPages).firstInvalidPage(rules)
            while (pagePairToRepair != null) {
                val swap1 = newPages[pagePairToRepair.pagePair.first]
                val swap2 = newPages[pagePairToRepair.pagePair.second]
                newPages[pagePairToRepair.pagePair.first] = swap2
                newPages[pagePairToRepair.pagePair.second] = swap1
                pagePairToRepair = Update(newPages).firstInvalidPage(rules)
            }
            return Update(newPages)
        }

        private data class InvalidPagePairByRule(val pagePair: Pair<Int, Int>, val rule: Rule)

        fun firstInvalidPage(rules: List<Rule>): InvalidPagePairByRule? {
            val invalidPagePairByRules = mutableListOf<InvalidPagePairByRule>()
            for (rule in rules) {
                val firstInvalidPagePair = rule.firstInvalidPagePair(this)
                if (firstInvalidPagePair != null) {
                    invalidPagePairByRules.add(InvalidPagePairByRule(firstInvalidPagePair, rule))
                }
            }
            return invalidPagePairByRules.minByOrNull { it.pagePair.first }
        }

        fun middlePage(): Int {
            return pages[pages.size / 2]
        }
    }
}