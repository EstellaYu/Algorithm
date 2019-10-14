# KD-Tree
Create a symbol-table data type whose keys are two-dimensional points. Use a `2d-tree` to support efficient range search (find all of the points contained in a query rectangle) and nearest-neighbor search (find a closest point to a query point). 
2d-trees have numerous applications, ranging from classifying astronomical objects and computer animation to speeding up neural networks and data mining.

<p align="center">
  <img src = "https://github.com/EstellaYu/Algorithm/blob/master/kdtree/kdtree.gif" width = '700px'>
</p>
<hr>

### 1. Brute-force implementation. 
Write a mutable data type PointST.java that uses a `red–black BST` to represent a symbol table whose keys are two-dimensional points, by implementing the following API:
```java
public class PointST<Value> {

    // construct an empty symbol table of points 
    public PointST()

    // is the symbol table empty? 
    public boolean isEmpty()

    // number of points
    public int size()

    // associate the value val with point p
    public void put(Point2D p, Value val)

    // value associated with point p 
    public Value get(Point2D p)

    // does the symbol table contain point p? 
    public boolean contains(Point2D p)

    // all points in the symbol table 
    public Iterable<Point2D> points()

    // all points that are inside the rectangle (or on the boundary) 
    public Iterable<Point2D> range(RectHV rect)

    // a nearest neighbor of point p; null if the symbol table is empty 
    public Point2D nearest(Point2D p)

    // unit testing (required)
    public static void main(String[] args)

}
```

### 2. 2d-tree implementation. 
Write a mutable data type KdTreeST.java that uses a 2d-tree to implement the same API (but renaming PointST to KdTreeST). A 2d-tree is a generalization of a `BST` to two-dimensional keys. The idea is to build a BST with points in the nodes, using the x- and y-coordinates of the points as keys in strictly alternating sequence, starting with the x-coordinates.
* **Range search**. To find all points contained in a given query rectangle, start at the root and recursively search for points in both subtrees using the following pruning rule: if the query rectangle does not intersect the rectangle corresponding to a node, there is no need to explore that node (or its subtrees). That is, search a subtree only if it might contain a point contained in the query rectangle.
* **Nearest-neighbor search**. To find a closest point to a given query point, start at the root and recursively search in both subtrees using the following pruning rule: if the closest point discovered so far is closer than the distance between the query point and the rectangle corresponding to a node, there is no need to explore that node (or its subtrees). That is, search a node only if it might contain a point that is closer than the best one found so far. The effectiveness of the pruning rule depends on quickly finding a nearby point. To do this, organize the recursive method so that when there are two possible subtrees to go down, you choose first the subtree that is on the same side of the splitting line as the query point; the closest point found while exploring the first subtree may enable pruning of the second subtree.


