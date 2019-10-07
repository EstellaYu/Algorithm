/* *****************************************************************************
 *  Name:    Estella Yu
 *  NetID:   yingxian
 *  Precept: P04
 *
 *  Partner Name:    N.A
 *  Partner NetID:   N.A
 *  Partner Precept: P04
 *
 *  Description:  Puzzle 8 solver
 *  implement A* Algorithem to find the solution of the puzzle board
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.HashSet;
import java.util.Set;

public class Solver {
    // number of moves since initial
    private int move = 0;
    // MinPQ used to keep track of the min Priority node
    private MinPQ<Node> sol = new MinPQ<Node>();
    private Node min; // goal node;
    // already inserted elements
    private Set<Board> alreadyInserted = new HashSet<Board>();

    private class Node implements Comparable<Node> {
        private Node parent; // previous node
        private int priority; // priority used for MinPQ
        private Board board; // board in node

        // used to compared node
        public int compareTo(Node n) {
            int pri = Integer.compare(this.priority, n.priority);
            int man = Integer.compare(this.board.manhattan(), n.board.manhattan());
            int ham = Integer.compare(this.board.hamming(), n.board.hamming());
            if (pri != 0) return pri;
            else if (man != 0) return man;
            else return ham;
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (!initial.isSolvable())
            throw new IllegalArgumentException("Unsolvable puzzle");

        Node root = new Node();
        root.parent = null;
        root.board = initial;
        root.priority = move + initial.manhattan();
        sol.insert(root);
        alreadyInserted.add(initial);
        this.min = solverRecursion();
    }

    // recursively find the min with smallest priority
    private Node solverRecursion() {
        Node currentMin = sol.delMin();

        if (currentMin.board.isGoal()) return currentMin;

        move++;
        for (Board nei : currentMin.board.neighbors()) {
            Node node = new Node();
            node.board = nei;
            node.parent = currentMin;
            node.priority = 100 * nei.manhattan() + move;

            if (nei.isGoal()) return node;

            else if (!alreadyInserted.contains(nei)) {
                sol.insert(node);
                alreadyInserted.add(nei);
            }
        }
        return solverRecursion();
    }

    // System.out.println(
    //         "priority: " + node.priority
    //                 + ", manhattan dist: " + node.board.manhattan()
    //                 + ", move: " + move + ", isgoal?: " + node.board.isGoal());
    // System.out.println(alreadyInserted);
    // System.out.println(node.board.toString() + node.board.isGoal());

    // min number of moves to solve initial board
    public int moves() {
        Iterable<Board> solution = solution();
        int cnt = -1;
        for (Board board : solution) {
            cnt++;
        }
        return cnt;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        Stack<Board> solution = new Stack<Board>();
        Node node = min;
        while (node.parent != null) {
            solution.push(node.board);
            node = node.parent;
        }
        solution.push(node.board);
        return solution;
    }

    // test client (see below)
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
        Solver solver = new Solver(board);
        Iterable<Board> solution = solver.solution();
        System.out.println("Minimum number of moves: " + solver.moves());
        for (Board bd : solution) {
            System.out.println(bd);
        }
    }

}
