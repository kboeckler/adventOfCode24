package de.kevinboeckler.aoc23

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day15Test {

    @Test
    fun part1_simple() {
        assertEquals(52, Day15().part1("HASH"))
    }

    @Test
    fun part1() {
        assertEquals(1320, Day15().part1("rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7"))
    }

    @Test
    fun part2() {
        assertEquals(145, Day15().part2("rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7"))
    }
}