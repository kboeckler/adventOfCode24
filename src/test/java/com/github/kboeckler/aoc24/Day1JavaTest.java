package com.github.kboeckler.aoc24;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day1JavaTest {

  @Test
  void part1() {
    Assertions.assertEquals(
        "part1",
        new Day1Java()
            .part1(
                "two1nine\n"
                    + "eightwothree\n"
                    + "abcone2threexyz\n"
                    + "xtwone3four\n"
                    + "4nineeightseven2\n"
                    + "zoneight234\n"
                    + "7pqrstsixteen"));
  }
}
