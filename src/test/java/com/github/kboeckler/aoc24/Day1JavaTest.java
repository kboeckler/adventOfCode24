package com.github.kboeckler.aoc24;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day1JavaTest {

  @Test
  void part1() {
    Assertions.assertEquals(
        142, new Day1Java().part1("1abc2\n" + "pqr3stu8vwx\n" + "a1b2c3d4e5f\n" + "treb7uchet"));
  }

  @Test
  void part2() {
    Assertions.assertEquals(
        281,
        new Day1Java()
            .part2(
                "two1nine\n"
                    + "eightwothree\n"
                    + "abcone2threexyz\n"
                    + "xtwone3four\n"
                    + "4nineeightseven2\n"
                    + "zoneight234\n"
                    + "7pqrstsixteen"));
  }
}
