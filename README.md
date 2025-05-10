# Bingo Game Simulator

## Overview

The `Bingo.java` program simulates a Bingo game by reading a Bingo card from a file, randomly drawing numbers, marking the card, and checking for winning conditions. It provides a visual representation of the Bingo card before and after the game, lists all numbers picked, and announces the type of win (if any).

## Features

- Reads a Bingo card from an input file (`bingo.in`)
- Simulates random number draws (1–75) with no duplicates
- Marks numbers on the card as they are picked
- Checks for win conditions: horizontal, vertical, and diagonal
- Displays the card before and after the game along with results

## Understanding the Algorithm

### Filling the Bingo Card
- The `fillUp` method uses a `Scanner` to read the Bingo card from `bingo.in` into a 2D array representing the card.

### Marking a Number
- The `markNumber` method uses a nested loop to compare the picked number with entries on the card.
- If a match is found, the corresponding position is marked as `true`.

### Preventing Duplicate Picks
- A boolean array `pickedNumbers[76]` tracks all picked numbers.
- Numbers are randomly generated in the range 1–75.
- If a number has already been picked, a new number is generated until a unique one is found.
- This ensures each number is only picked once.

### Determining a Win
The `checkForWin` method evaluates three types of Bingo wins:

- **Horizontal Win:** All numbers in any row are marked.
- **Vertical Win:** All numbers in any column are marked.
- **Diagonal Win:** All numbers in either diagonal are marked (top-left to bottom-right or top-right to bottom-left).

If none of these conditions are met, the program continues until a win is achieved.

## How to Compile and Run

```bash
javac Bingo.java
java Bingo
```
 ## License
 No license has been provided for this project
