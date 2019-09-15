/* *****************************************************************************
 *  Name:    Estella Yu
 *  NetID:   yingxian
 *  Precept: P04
 *
 *  Description: Percolation
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.QuickUnionUF;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {

    // size of union find
    private final int n;
    // boolean of if the block opened
    private boolean[] opened;
    // unionfind to connect opened blocks
    // for visual
    private QuickUnionUF uf_isFull;
    // for perlocation
    private QuickUnionUF uf;

    // number of open site
    private int cnt;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be >0");
        }

        this.n = n;
        this.cnt = 0;
        this.opened = new boolean[n * n];
        this.uf = new QuickUnionUF(n * n + 2);
        // this one is only connected to the top, for visualize purpose
        this.uf_isFull = new QuickUnionUF(n * n + 1);
        // Note -- notataion
        // [n^2] : dummy top node
        // [n^2 + 1]: dummy bottom node
        // position translation: pos = n * row + col
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row >= n || col >= n) {
            throw new IllegalArgumentException("row or col out of range");
        }

        int pos = n * row + col;

        if (!isOpen(row, col)) {
            opened[pos] = true;
            cnt++;
        }

        // top row
        if (row == 0) {
            uf.union(pos, n * n);
            uf_isFull.union(pos, n * n);
        }
        // bottom row
        if (row == n - 1) {
            uf.union(pos, n * n + 1);
        }

        // check top
        if (row > 0 && isOpen(row - 1, col)) {
            uf.union(pos, pos - n);
            uf_isFull.union(pos, pos - n);
        }
        // check bottom
        if (row < n - 1 && isOpen(row + 1, col)) {
            uf.union(pos, pos + n);
            uf_isFull.union(pos, pos + n);
        }
        // check left
        if (col > 0 && isOpen(row, col - 1)) {
            uf.union(pos, pos - 1);
            uf_isFull.union(pos, pos - 1);
        }
        // check right
        if (col < n - 1 && isOpen(row, col + 1)) {
            uf.union(pos, pos + 1);
            uf_isFull.union(pos, pos + 1);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row >= n || col >= n) {
            throw new IllegalArgumentException("row or col out of range");
        }

        return opened[n * row + col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row >= n || col >= n) {
            throw new IllegalArgumentException("row or col out of range");
        }
        return uf_isFull.connected(n * n, n * row + col);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return cnt;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(n * n, n * n + 1);
    }

    // unit testing (required)
    public static void main(String[] args) {
        In in = new In(args[0]);      // input file
        int m = in.readInt();         // N-by-N percolation system

        Percolation block = new Percolation(m);

        while (!in.isEmpty()) {
            int row = in.readInt();
            int col = in.readInt();
            StdOut.println("Site (" + row + "," + col + ")");
            StdOut.println("Originally opened?  " + block.isOpen(row, col));
            block.open(row, col);
            StdOut.println("now Full?           " + block.isFull(row, col));
            StdOut.println("current open sites: " + block.numberOfOpenSites());
            StdOut.println("perlocates?         " + block.percolates());
            StdOut.println("\n");
        }


    }

}

