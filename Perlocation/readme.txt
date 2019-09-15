/* *****************************************************************************
 *  Name:     Estella Yu
 *  NetID:    yingxian
 *  Precept:  P04
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Operating system: MAC OX
 *  Compiler: IntelliJ
 *  Text editor / IDE: IntelliJ
 *
 *  Have you taken (part of) this course before: no
 *  Have you taken (part of) the Coursera course Algorithms, Part I or II: no
 *
 *  Hours to complete assignment (optional): 3
 *
 **************************************************************************** */

Programming Assignment 1: Percolation



/* *****************************************************************************
 *  Describe how you implemented Percolation.java. How did you check
 *  whether the system percolates?
 **************************************************************************** */
1. use a "boolean[] array opened" to keep track of the open sites
2. add dummy nodes to the top and the bottom, respectively
3. connection:
    1) if the top row is opened, union(current, top)
    2) if the bottom row is opened, union(current, bottom)
    3) check connectivity to adjacent neighbors (top, down, left, right), if
        any of them is opened, union(current, opened neighbor)
4. check perlocation: isconnected(top, bottom)


/* *****************************************************************************
 *  Perform computational experiments to estimate the running time of
 *  PercolationStats.java for various values of n and T when implementing
 *  Percolation.java with QuickFindUF.java (not QuickUnionUF.java). Use a
 *  "doubling" hypothesis, where you successively increase either n or T by
 *  a constant multiplicative factor (not necessarily 2).
 *
 *  To do so, fill in the two tables below. Each table must have 5-10
 *  data points, ranging in time from around 0.25 seconds for the smallest
 *  data point to around 30 seconds for the largest one. Do not include
 *  data points that take less than 0.25 seconds.
 **************************************************************************** */

(keep T constant)
 T = 100
 multiplicative factor (for n) = 2

 n          time (seconds)       ratio         log ratio
--------------------------------------------------------
2           0.005
4           0.007
8           0.020                               1.514
16          0.110               5.5             2.459
32          1.237               11.245          3.491
64          17.141              13.857          3.793
128         270.819             15.799          3.982


(keep n constant)
 n = 100
 multiplicative factor (for T) = 2

 T          time (seconds)       ratio         log ratio
--------------------------------------------------------
2           1.989
4           4.033                2.028          1.020
8           7.77                 1.927          0.946
16          16.113               2.074          1.052
32          31.941               1.982          0.987


/* *****************************************************************************
 *  Using the empirical data from the above two tables, give a formula
 *  (using tilde notation) for the running time (in seconds) of
 *  PercolationStats.java as function of both n and T, such as
 *
 *       ~ 5.3*10^-8 * n^5.1 * T^1.5
 *
 *  Briefly explain how you determined the formula for the running time.
 *  Recall that with tilde notation, you include both the coefficient
 *  and exponents of the leading term (but not lower-order terms).
 *  Round each coefficient and exponent to two significant digits.
 **************************************************************************** */

QuickFindUF running time (in seconds) as a function of n and T:

    ~   A * n^3.5 * T^1
       _______________________________________



/* *****************************************************************************
 *  Repeat the previous two questions, but using WeightedQuickUnionUF
 *  (instead of QuickFindUF).
 **************************************************************************** */

(keep T constant)
 T = 100

 n          time (seconds)       ratio         log ratio
--------------------------------------------------------
50          0.517
100         2.027                3.921          1.971
200         8.057                3.975          1.991
400         32.525               4.037          2.013
800         148.071              4.551          2.186


(keep n constant)
 n = 100

 T          time (seconds)       ratio         log ratio
--------------------------------------------------------
50          1.002
100         2.185               2.181           1.125
200         4.206               1.925           0.945
400         8.009               1.904           0.929
800         15.948              1.991           0.993
...


WeightedQuickUnionUF running time (in seconds) as a function of n and T:

    ~   A * n^2 * T^1
       _______________________________________



/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */
the probability varies in every experiments


/* *****************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **************************************************************************** */


/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */
N/A



/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */
