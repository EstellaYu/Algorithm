/* *****************************************************************************
 *  Name:    Estella Yu
 *  NetID:   yingxian
 *  Precept: P04
 *
 *  Partner Name:    NA
 *  Partner NetID:   NA
 *  Partner Precept: P00
 *
 *  Description:  uses a red–black BST to represent a symbol table
 * whose keys are two-dimensional points
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.Stack;

public class PointST<Value> {
    // number of nearest neighbor call
    private static int nnCall;
    // red black tree
    private RedBlackBST<Point2D, Value> rbt = new RedBlackBST<Point2D, Value>();

    // construct an empty symbol table of points
    public PointST() {
    }

    // is the symbol table empty?
    public boolean isEmpty() {
        return rbt.isEmpty();
    }

    // number of points
    public int size() {
        return rbt.size();
    }

    // associate the value val with point p
    public void put(Point2D p, Value val) {
        if (p == null || val == null) {
            throw new IllegalArgumentException("p or val cannot be null");
        }
        rbt.put(p, val);
    }

    // value associated with point p
    public Value get(Point2D p) {
        if (p == null) throw new IllegalArgumentException("p cannot be null");
        return rbt.get(p);
    }

    // does the symbol table contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("p cannot be null");
        return rbt.contains(p);
    }

    // all points in the symbol table
    public Iterable<Point2D> points() {
        return rbt.keys();
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("rect cannot be null");
        Stack<Point2D> within = new Stack<Point2D>();
        for (Point2D key : rbt.keys()) {
            if (rect.contains(key)) {
                within.push(key);
            }
        }
        return within;
    }

    // a nearest neighbor of point p; null if the symbol table is empty
    public Point2D nearest(Point2D p) {
        nnCall++;
        if (p == null) throw new IllegalArgumentException("p cannot be null");
        if (rbt.isEmpty()) return null;
        if (rbt.contains(p)) return p;
        Point2D champ = new Point2D(100, 100);
        for (Point2D key : rbt.keys()) {
            if (p.distanceSquaredTo(key) < p.distanceSquaredTo(champ)) champ = key;
        }
        return champ;
    }

    // print number of nearest neightbor call
    public int nnCall() {
        return nnCall;
    }

    // unit testing (required)
    public static void main(String[] args) {
        PointST<Integer> rbt = new PointST<Integer>();
        System.out.println("isEmpty: " + rbt.isEmpty());
        Point2D p = new Point2D(0.5, 0.5);
        rbt.put(p, 1);
        rbt.put(new Point2D(1, 1.5), 1);
        rbt.put(p, 2);

        System.out.println("size() : " + rbt.size());
        System.out.println("get(p) : " + rbt.get(p));
        System.out.println("contains: " + rbt.contains(new Point2D(1, 1)));
        System.out.println("nearest : " + rbt.nearest(p));
        System.out.println("range   : " + rbt.range(new RectHV(0, 0, 1, 1)));
        Iterable<Point2D> points = rbt.points();
        for (Point2D pt : points) {
            System.out.print(pt.toString() + "  ");
        }
        System.out.println();

        // 2nd PointST
        String filename = args[0];
        In in = new In(filename);
        PointST<Integer> kdtree = new PointST<>();
        for (int i = 0; !in.isEmpty(); i++) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D pt = new Point2D(x, y);
            kdtree.put(pt, i);
        }

        // Point2D[] pts = new Point2D[n];
        // for (Point2D pt : kdtree.points()) {
        //     System.out.print(pt.toString() + ' ');
        // }
        System.out.println("nearest neightbor call: " + kdtree.nnCall());
    }
}
