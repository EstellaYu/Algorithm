/* *****************************************************************************
 *  Name:    Estella Yu
 *  NetID:   yingxian
 *  Precept: P04
 *
 *  Description: stats result for threshold
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {

    // the double array to hold the experimental probability
    private double[] th;
    // number of experiments to be performed
    private int trails;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be >0");
        }
        if (trials <= 0) {
            throw new IllegalArgumentException("trails must be >0");
        }

        this.trails = trials;
        this.th = new double[trials];

        for (int i = 0; i < trails; i++) {
            Percolation exp = new Percolation(n);
            while (!exp.percolates()) {
                int row = StdRandom.uniform(n);
                int col = StdRandom.uniform(n);
                exp.open(row, col);
            }
            th[i] = exp.numberOfOpenSites() / Math.pow(n, 2);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(th);
    }


    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(th);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return (mean() - 1.96 * stddev() / Math.sqrt(trails));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return (mean() + 1.96 * stddev() / Math.sqrt(trails));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trails = Integer.parseInt(args[1]);
        Stopwatch watch = new Stopwatch();
        PercolationStats stats = new PercolationStats(n, trails);

        StdOut.println("main()           = " + stats.mean());
        StdOut.println("stddev()         = " + stats.stddev());
        StdOut.println("confidenceLow()  = " + stats.confidenceLow());
        StdOut.println("confidenceHigh() = " + stats.confidenceHigh());
        StdOut.println("elapsed time     = " + watch.elapsedTime());

    }
}
