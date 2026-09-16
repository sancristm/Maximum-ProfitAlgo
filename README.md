# Maximum Profit Algorithm

This project contains a Java implementation of the maximum-profit optimization problem. It calculates the best possible total profit when each item belongs to a category and each category can be introduced using its cheapest item.

## Overview

Given a list of item categories and their prices, the goal is to maximize profit by selecting the best order in which to introduce categories.

The key observation is:

- The cheapest item in each category should be used to introduce that category.
- Once all categories have been introduced, every remaining item can be sold at its full value.
- To maximize profit, the category introduction prices are sorted from smallest to largest and assigned multipliers 1, 2, 3, ...

## Algorithm

For every category:

1. Track the minimum price seen for that category.
2. Sort all category-introducing prices in ascending order.
3. Add each minimum price multiplied by its position index.
4. Add every non-introducing item’s price multiplied by the total number of categories.

This yields the optimal maximum profit.

## Time Complexity

- Building the category minimum map: O(n)
- Sorting the category minima: O(k log k)
- Final profit calculation: O(k)

Overall: O(n log n), where n is the number of items and k is the number of categories.

## Files

- `Main.java` — standalone implementation with input parsing and console output
- `Solution.java` — alternative implementation that also writes to `OUTPUT_PATH` when provided by the environment

## Example

Input:

```text
3
1 2 1
5 4 3
```

Output:

```text
13
```

## Running the project the target is java 8

Compile the project:

```bash
javac Main.java
```

Run it:

```bash
java Main < input.txt
```

You can also use the `Solution` class in the same way when running in a HackerRank-style environment with `OUTPUT_PATH` support enabled.
