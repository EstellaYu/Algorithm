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
 multiplicative factor (for n) =

 n          time (seconds)       ratio         log ratio
--------------------------------------------------------
...
...
...
...
...
...


(keep n constant)
 n = 100
 multiplicative factor (for T) =

 T          time (seconds)       ratio         log ratio
--------------------------------------------------------
...
...
...
...
...
...
...


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

    ~
       _______________________________________



/* *****************************************************************************
 *  Repeat the previous two questions, but using WeightedQuickUnionUF
 *  (instead of QuickFindUF).
 **************************************************************************** */

(keep T constant)
 T =

 n          time (seconds)       ratio         log ratio
--------------------------------------------------------
...
...
...
...
...


(keep n constant)
 n =

 T          time (seconds)       ratio         log ratio
--------------------------------------------------------
...
...
...
...
...


WeightedQuickUnionUF running time (in seconds) as a function of n and T:

    ~
       _______________________________________



/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */




/* *****************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **************************************************************************** */


/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */




/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */
