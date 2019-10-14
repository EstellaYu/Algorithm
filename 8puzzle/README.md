# 8 Puzzle Solver
Write a program to solve the 8-puzzle problem (and its natural generalizations) using the A* search algorithm.

<p align="center">
  <img src = "https://github.com/EstellaYu/Algorithm/blob/master/8puzzle/puzzle8.gif" width = '700px'>
</p>
<hr>

### The problem
The [8-puzzle](https://en.wikipedia.org/wiki/15_puzzle) is a sliding puzzle that is played on a 3-by-3 grid with 8 square tiles labeled 1 through 8, plus a blank square. The goal is to rearrange the tiles so that they are in row-major order, using as few moves as possible.

### 1. Board data type. 
To begin, create a data type that models an n-by-n board with sliding tiles. Implement an immutable data type Board with the following API:
``` java
public class Board {

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles)
                                           
    // string representation of this board
    public String toString()

    // tile at (row, col) or 0 if blank
    public int tileAt(int row, int col)

    // board size n
    public int size()

    // number of tiles out of place
    public int hamming()

    // sum of Manhattan distances between tiles and goal
    public int manhattan()

    // is this board the goal board?
    public boolean isGoal()

    // does this board equal y?
    public boolean equals(Object y)

    // all neighboring boards
    public Iterable<Board> neighbors()

    // is this board solvable?
    public boolean isSolvable()

    // unit testing (required)
    public static void main(String[] args)

}
```

### 2. A* search. 
Now, we describe a solution to the 8-puzzle problem that illustrates a general artificial intelligence methodology known as the `A* search algorithm`. We define a search node of the game to be a board, the number of moves made to reach the board, and the previous search node. 
1. First, insert the initial search node (the initial board, 0 moves, and a null previous search node) into a priority queue. 
2. Then, delete from the priority queue the search node with the minimum priority, and insert onto the priority queue all neighboring search nodes (those that can be reached in one move from the dequeued search node). 
3. Repeat this procedure until the search node dequeued corresponds to the goal board.

Implement A* search to solve n-by-n slider puzzles. Create an immutable data type Solver with the following API:
```java
public class Solver {

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial)

    // min number of moves to solve initial board
    public int moves()

    // sequence of boards in a shortest solution
    public Iterable<Board> solution()

    // test client (see below) 
    public static void main(String[] args)

}
```





