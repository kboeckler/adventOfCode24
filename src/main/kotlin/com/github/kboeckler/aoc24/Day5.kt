package com.github.kboeckler.aoc24

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
        fun firstInvalidPage(update: Update): Int {
            val firstPagePos = update.pages.indexOf(first)
            val secondPagePos = update.pages.indexOf(second)
            if (firstPagePos == -1 || secondPagePos == -1) {
                return -1
            }
            if (firstPagePos <= secondPagePos) {
                return -1
            }
            return min(firstPagePos, secondPagePos)
        }
    }

    private data class Update(val pages: List<Int>) {
        fun fulfillsRules(rules: List<Rule>): Boolean {
            for (rule in rules) {
                if (rule.firstInvalidPage(this) != -1) {
                    return false
                }
            }
            return true
        }

        fun getCorrectedUpdate(rules: List<Rule>): Update {
            val newPages = pages.toMutableList()
            Update(newPages).firstInvalidPage(rules)
            while (true) {
                TODO("here")
            }
            return this
        }

        private data class InvalidPageByRule(val page: Int, val rule: Rule)

        fun firstInvalidPage(rules: List<Rule>): InvalidPageByRule {
            val invalidPageByRules = mutableListOf<InvalidPageByRule>()
            for (rule in rules) {
                val firstInvalidPage = rule.firstInvalidPage(this)
                if (firstInvalidPage == -1) {
                    invalidPageByRules.add(InvalidPageByRule(firstInvalidPage, rule))
                }
            }
            return invalidPageByRules.sortedBy { it.page }.first()
        }

        fun middlePage(): Int {
            return pages[pages.size / 2]
        }
    }
}