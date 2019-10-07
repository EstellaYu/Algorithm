/* *****************************************************************************
 *  Name:    Estella Yu
 *  NetID:   yingxian
 *  Precept: P04
 *
 *  Partner Name:    N.A
 *  Partner NetID:   N.A
 *  Partner Precept: P00
 *
 *  Description:  create a data type that models an n-by-n board with
 * sliding tiles.
 * Implement an immutable data type Board with the following API:
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

public class Board {
    private final int n; // tile per row/col
    private final int[][] board; // the board array
    private final int hamming; // haming
    private final int manhattan; // manhattan dist;
    private final int zeroInd; // get where 0 is in the board

    // constructor (~ n^2)
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.n = tiles.length;
        this.board = tiles;
        int ham = 0;
        int man = 0;
        int zero = n * n;
        for (int i = 0; i < n * n; i++) {
            int num = board[i / n][i % n];
            if (num == 0) {
                zero = i;
            }
            if (num != 0 && num != i + 1) {
                ham++;
                man += Math.abs((num - 1) / n - i / n); // row mismatch
                man += Math.abs((num - 1) % n - i % n); // col mismatch
            }
        }
        zeroInd = zero;
        hamming = ham;
        manhattan = man;
    }

    // string representation of this board (~ n^2)
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(n + "\n");
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                str.append(" " + board[row][col]);
            }
            str.append("\n");
        }
        return str.toString();
    }

    // tile at (row, col) or 0 if blank (~ const)
    public int tileAt(int row, int col) {
        if (!(row < n && row >= 0 && col < n && col >= 0)) {
            throw new IllegalArgumentException("row or col out of range");
        }
        return board[row][col];
    }

    // board size n (~ const)
    public int size() {
        return n;
    }

    // number of tiles out of place (~ const)
    public int hamming() {
        return hamming;
    }

    // sum of Manhattan distances between tiles and goal (~ const)
    public int manhattan() {
        return manhattan;
    }

    // is this board the goal board? (~ const)
    public boolean isGoal() {
        return hamming == 0;
    }

    // does this board equal y? (~ n^2)
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if (that.n != this.n) return false;
        for (int i = 0; i < n * n; i++) {
            if (that.tileAt(i / n, i % n) != board[i / n][i % n]) return false;
        }
        return true;
    }

    // all neighboring boards (~ n^2)
    public Iterable<Board> neighbors() {
        Stack<Board> neighbors = new Stack<Board>();

        // at row edge
        if (zeroInd / n == 0 || zeroInd / n == (n - 1)) {
            if (zeroInd / n == 0) neighbors.push(exch(this, zeroInd, zeroInd + n));
            else neighbors.push(exch(this, zeroInd, zeroInd - n));
        }
        else {
            neighbors.push(exch(this, zeroInd, zeroInd + n));
            neighbors.push(exch(this, zeroInd, zeroInd - n));
        }

        // at col edge
        if (zeroInd % n == 0 || zeroInd % n == (n - 1)) {
            if (zeroInd % n == 0) neighbors.push(exch(this, zeroInd, zeroInd + 1));
            else neighbors.push(exch(this, zeroInd, zeroInd - 1));
        }
        else {
            neighbors.push(exch(this, zeroInd, zeroInd + 1));
            neighbors.push(exch(this, zeroInd, zeroInd - 1));
        }
        return neighbors;
    }

    // generate new neighbor board, by exchanging elements in ind1(a) and ind2(b)
    private Board exch(Board oldBoard, int a, int b) {
        int nboard = oldBoard.n;
        int[][] boardcopy = new int[n][n];
        for (int i = 0; i < n * n; i++) {
            boardcopy[i / nboard][i % nboard] = oldBoard.board[i / nboard][i % nboard];
        }

        int temp = boardcopy[a / nboard][a % nboard];
        boardcopy[a / nboard][a % nboard] = boardcopy[b / nboard][b % nboard];
        boardcopy[b / nboard][b % nboard] = temp;
        Board newBoard = new Board(boardcopy);
        // System.out.println("switched: " + a + ", " + b);
        // System.out.println(newBoard.toString());
        return newBoard;
    }

    // is this board solvable? (~ n^4 or better)
    public boolean isSolvable() {
        int inversion = 0;
        for (int i = 0; i < n * n; i++) {
            int numI = this.tileAt(i / n, i % n);
            for (int j = 0; j < i; j++) {
                int numJ = this.tileAt(j / n, j % n);
                if (numI * numJ != 0 && numJ > numI) inversion++;
            }
        }

        // odd n
        if (n % 2 != 0) return (inversion % 2 == 0);
            // even n
        else return (inversion + zeroInd / n) % 2 == 1;
    }

    // unit testing (required)
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        }
        Board board = new Board(tiles);
        System.out.println("size: " + board.size());
        System.out.println(board.toString());

        System.out.println("solvable? : " + board.isSolvable());
        System.out.println("isGOal? : " + board.isGoal());
        System.out.println("hamming: " + board.hamming());
        System.out.println("manhattan: " + board.manhattan());
        System.out.println("tile at [1][1]: " + board.tileAt(1, 1));

        System.out.println("neighbors: ");
        Iterable<Board> neighbors = board.neighbors();
        int cnt = 0;
        for (Board nei : neighbors) {
            System.out.println("neighbor " + cnt + "\n" + nei.toString());
            cnt++;
        }

        In in2 = new In("puzzle2x2-03.txt");
        int n2 = in2.readInt();
        int[][] tiles2 = new int[n2][n2];
        for (int i = 0; i < n2; i++) {
            for (int j = 0; j < n2; j++)
                tiles2[i][j] = in2.readInt();
        }
        Board board2 = new Board(tiles2);
        System.out.println("equals? :" + board.equals(board2));
    }

}
