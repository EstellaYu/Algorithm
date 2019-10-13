/* *****************************************************************************
 *  Name:    Estella Yu
 *  NetID:   yingxian
 *  Precept: P04
 *
 *  Partner Name:    NA
 *  Partner NetID:   NA
 *  Partner Precept: P04
 *
 *  Description:  mutable data type KdTreeST.java that uses a 2d-tree
 *  to implement the same API (but renaming PointST to KdTreeST).
 *  A 2d-tree is a generalization of a BST to two-dimensional keys.
 *  The idea is to build a BST with points in the nodes,
 *  using the x- and y-coordinates of the points as keys in strictly
 *  alternating sequence, starting with the x-coordinates.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;

public class KdTreeST<Value> {
    // number of nearest neighbor call
    private static int nncall;

    // bbox for root
    private final RectHV inftyBbox;
    // is the root started with vertical partition
    private final boolean rootVertical;

    private Node root;             // root of KdTree

    private class Node {
        private Point2D p;           // sorted by key
        private Value val;         // associated data
        private Node left;   // left subtrees
        private Node right;  // right subtrees
        private boolean vertical; // horizontal (F) or vertical (T)

        // construct inner class Node
        public Node(Point2D p, Value val, boolean vertical) {
            this.p = p;
            this.val = val;
            this.vertical = vertical;
        }
    }

    // construct an empty symbol table of points
    public KdTreeST() {
        rootVertical = true;
        inftyBbox = new RectHV(Double.NEGATIVE_INFINITY,
                               Double.NEGATIVE_INFINITY,
                               Double.POSITIVE_INFINITY,
                               Double.POSITIVE_INFINITY);
    }

    // is the symbol table empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // number of points
    public int size() {
        if (root == null) return 0;
        return size(root);
    }

    // (recurion) return size of subtree from Node p (including p)
    private int size(Node x) {
        if (x == null) return 0;
        return 1 + size(x.left) + size(x.right);
    }

    // associate the value val with point p
    public void put(Point2D p, Value val) {
        if (p == null || val == null) throw new IllegalArgumentException("input null");
        root = put(p, val, root, rootVertical);
    }

    // (recursion) put Node(p, val) under subtree of x
    // with vertical of x being (boolean) vertical
    private Node put(Point2D p, Value val, Node x, boolean vertical) {
        if (x == null) return new Node(p, val, vertical);
        if (x.p.equals(p)) {
            x.val = val;
        }
        else if ((vertical && p.x() < x.p.x()) || (!vertical && p.y() < x.p.y())) {
            x.left = put(p, val, x.left, !x.vertical);
        }
        else {
            // if (vertical) {
            //     bbox = new RectHV(x.p.x(), bbox.ymin(), bbox.xmax(), bbox.ymax());
            // }
            // else bbox = new RectHV(bbox.xmin(), x.p.y(), bbox.xmax(), bbox.ymax());
            x.right = put(p, val, x.right, !x.vertical);
        }
        return x;
    }


    // value associated with point p
    public Value get(Point2D p) {
        if (p == null) throw new IllegalArgumentException("p cannot be null");
        return get(p, root);
    }

    // (recursion) get value get(Point2D p) from subtree of x
    private Value get(Point2D p, Node x) {
        if (x == null) return null;
        if (x.p.equals(p)) return x.val;
        if ((x.vertical && p.x() < x.p.x()) || (!x.vertical && p.y() < x.p.y())) {
            return get(p, x.left);
        }
        else return get(p, x.right);
    }


    // does the symbol table contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("input p is null");
        return contains(p, root);
    }

    // (recursion) contains (p) in subtree x
    private boolean contains(Point2D p, Node x) {
        if (x == null) return false;
        if (x.p.equals(p)) return true;
        if ((x.vertical && p.x() < x.p.x()) || (!x.vertical && p.y() < x.p.y())) {
            return contains(p, x.left);
        }
        else return contains(p, x.right);
    }

    // all points in the symbol table
    public Iterable<Point2D> points() {
        if (root == null) return new Queue<Point2D>();

        Queue<Point2D> pts = new Queue<Point2D>();
        Queue<Node> ptStore = new Queue<>();
        ptStore.enqueue(root);
        do {
            Node current = ptStore.dequeue();
            pts.enqueue(current.p);
            if (current.left != null) ptStore.enqueue(current.left);
            if (current.right != null) ptStore.enqueue(current.right);
        } while (!ptStore.isEmpty());
        return pts;
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("rects cannot be null");
        Queue<Point2D> pts = new Queue<Point2D>();
        range(pts, rect, root, inftyBbox);
        return pts;
    }

    // (recursion) if there is point under subtree x that's inside rect
    private void range(Queue<Point2D> pts, RectHV rect, Node x, RectHV bbox) {
        if (x == null) return;
        if (!bbox.intersects(rect)) return;
        if (rect.contains(x.p)) pts.enqueue(x.p);

        range(pts, rect, x.left, calcBBox(x, bbox, true));
        range(pts, rect, x.right, calcBBox(x, bbox, false));
    }

    // calculate new bbox
    private RectHV calcBBox(Node x, RectHV bbox, boolean leftBottom) {
        if (x.vertical) {
            if (leftBottom) {
                return new RectHV(bbox.xmin(), bbox.ymin(), x.p.x(), bbox.ymax());
            }
            else return new RectHV(x.p.x(), bbox.ymin(), bbox.xmax(), bbox.ymax());
        }
        else {
            if (leftBottom) {
                return new RectHV(bbox.xmin(), bbox.ymin(), bbox.xmax(), x.p.y());
            }
            else return new RectHV(bbox.xmin(), x.p.y(), bbox.xmax(), bbox.ymax());
        }
    }

    // a nearest neighbor of point p; null if the symbol table is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("p cannot be null");
        if (isEmpty()) return null;
        Point2D champ = root.p;
        champ = nearest(p, champ, root, inftyBbox);
        return champ;
    }

    // (recursion) if here is a champ in subtree x
    private Point2D nearest(Point2D p, Point2D champ, Node x, RectHV bbox) {
        nncall++;
        if (x == null) return champ;
        double dist = p.distanceSquaredTo(champ);
        if (bbox.distanceSquaredTo(p) > dist) return champ;

        if (x.p.distanceSquaredTo(p) < dist) champ = x.p;

        if (p.x() < x.p.x() || p.y() < x.p.y()) {
            champ = nearest(p, champ, x.left, calcBBox(x, bbox, true));
            champ = nearest(p, champ, x.right, calcBBox(x, bbox, false));
        }
        else {
            champ = nearest(p, champ, x.right, calcBBox(x, bbox, false));
            champ = nearest(p, champ, x.left, calcBBox(x, bbox, true));
        }
        return champ;
    }

    // number of nearest neighbor call
    public int nncall() {
        return nncall;
    }

    // unit testing (required)
    public static void main(String[] args) {
        KdTreeST<Integer> rbt = new KdTreeST<>();
        System.out.println("isEmpty: " + rbt.isEmpty());
        Point2D p = new Point2D(0.5, 0.5);
        rbt.put(p, 1);
        rbt.put(new Point2D(1, 1.5), 1);
        rbt.put(p, 2);

        System.out.println("size() : " + rbt.size());
        System.out.println("get(p) : " + rbt.get(p));
        System.out.println("contains: " + rbt.contains(new Point2D(1, 1)));
        // System.out.println("nearest : " + rbt.nearest(p));
        System.out.println("range   : " + rbt.range(new RectHV(0, 0, 1, 1)));
        Iterable<Point2D> points = rbt.points();
        for (Point2D pt : points) {
            System.out.print(pt.toString() + "  ");
        }
        System.out.println();

        // 2nd KDTree
        String filename = args[0];
        In in = new In(filename);
        KdTreeST<Integer> kdtree = new KdTreeST<>();
        for (int i = 0; !in.isEmpty(); i++) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D pt = new Point2D(x, y);
            kdtree.put(pt, i);
        }

        long startTime = System.currentTimeMillis();
        System.out.println("nearest : " + kdtree.nearest(p));
        long endTime = System.currentTimeMillis();
        System.out.println("nearest neightbor call: " + kdtree.nncall());
        System.out.println("That took " + (endTime - startTime) + " milliseconds");

        // Point2D[] pts = new Point2D[n];
        // for (Point2D pt : kdtree.points()) {
        //     System.out.print(pt.toString() + ' ');
        // }
        System.out.println();
    }

}

