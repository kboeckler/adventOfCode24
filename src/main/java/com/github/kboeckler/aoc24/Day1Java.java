package com.github.kboeckler.aoc24;

import org.jetbrains.annotations.NotNull;

public class Day1Java extends Day {

  @NotNull
  @Override
  public Object part1(@NotNull String input) {
    return input
        .lines()
        .map(line -> line.replaceAll("[a-zA-Z]", ""))
        .map(numberStr -> "" + numberStr.charAt(0) + numberStr.charAt(numberStr.length() - 1))
        .map(Integer::valueOf)
        .reduce(Integer::sum)
        .orElse(0);
  }

  @NotNull
  @Override
  public Object part2(@NotNull String input) {
    return input
        .lines()
        .map(
            line ->
                line.replaceAll("one", "o1e")
                    .replaceAll("two", "t2o")
                    .replaceAll("three", "t3e")
                    .replaceAll("four", "f4r")
                    .replaceAll("five", "f5e")
                    .replaceAll("six", "s6x")
                    .replaceAll("seven", "s7n")
                    .replaceAll("eight", "e8t")
                    .replaceAll("nine", "n9e"))
        .map(line -> line.replaceAll("[a-zA-Z]", ""))
        .map(numberStr -> "" + numberStr.charAt(0) + numberStr.charAt(numberStr.length() - 1))
        .map(Integer::valueOf)
        .reduce(Integer::sum)
        .orElse(0);
  }
}
