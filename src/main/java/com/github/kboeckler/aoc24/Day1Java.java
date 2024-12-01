package com.github.kboeckler.aoc24;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Day1Java implements Day {

  @Override
  @Nullable
  public Object part1(@NotNull String input) {
    var allIds = input.lines().flatMap(this::splitToInteger).toList();
    var firstIds = takeEvenItems(allIds).stream().sorted().toList();
    var secondIds = takeUnevenItems(allIds).stream().sorted().toList();
    return mapIndexed(firstIds, (idx, value) -> value - secondIds.get(idx)).stream()
        .map(diff -> Math.abs(diff))
        .reduce(Integer::sum)
        .orElse(0);
  }

  @Override
  @Nullable
  public Object part2(@NotNull String input) {
    var allIds = input.lines().flatMap(this::splitToInteger).toList();
    var firstIds = takeEvenItems(allIds).stream().sorted().toList();
    var secondIds = takeUnevenItems(allIds).stream().sorted().toList();
    return firstIds.stream()
        .map(
            firstId ->
                (int)
                    (firstId
                        * secondIds.stream().filter(secondId -> secondId.equals(firstId)).count()))
        .reduce(Integer::sum)
        .orElse(0);
  }

  private Stream<Integer> splitToInteger(String line) {
    List<Integer> items = new ArrayList<>();
    Matcher matcher = Pattern.compile("(\\d+)").matcher(line);
    while (matcher.find()) {
      items.add(Integer.valueOf(matcher.group(1)));
    }
    return items.stream();
  }

  private <T> List<T> takeEvenItems(List<T> elements) {
    return takeItemsMod2(elements, 0);
  }

  private <T> List<T> takeUnevenItems(List<T> elements) {
    return takeItemsMod2(elements, 1);
  }

  private <T> List<T> takeItemsMod2(List<T> elements, Integer mod) {
    return filterIndexed(elements, (idx, value) -> idx % 2 == mod);
  }

  @FunctionalInterface
  private interface IndexedFilter<T> extends BiFunction<Integer, T, Boolean> {}

  private <T> List<T> filterIndexed(List<T> inputList, IndexedFilter<T> filterStatement) {
    List<T> filtered = new ArrayList<>();
    ListIterator<T> li = inputList.listIterator();
    while (li.hasNext()) {
      var idx = li.nextIndex();
      var value = li.next();
      if (filterStatement.apply(idx, value)) {
        filtered.add(value);
      }
    }
    return filtered;
  }

  @FunctionalInterface
  private interface IndexedMap<T, U, R> extends BiFunction<T, U, R> {}

  private <T, R> List<R> mapIndexed(List<T> inputList, IndexedMap<Integer, T, R> mapStatement) {
    List<R> mapped = new ArrayList<>();
    ListIterator<T> li = inputList.listIterator();
    while (li.hasNext()) {
      var idx = li.nextIndex();
      var value = li.next();
      mapped.add(mapStatement.apply(idx, value));
    }
    return mapped;
  }
}
