package com.github.kboeckler.aoc24;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day1JavaTest {

  @Test
  void part1() {
    Assertions.assertEquals(
        11,
        new Day1Java()
            .part1(
                """
                3   4
                4   3
                2   5
                1   3
                3   9
                3   3"""));
  }

  @Test
  void part2() {
    Assertions.assertEquals(
        31,
        new Day1Java()
            .part2(
                """
                            3   4
                            4   3
                            2   5
                            1   3
                            3   9
                            3   3"""));
  }
}
