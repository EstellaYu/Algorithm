/* *****************************************************************************
 *  Name:     Estella Yu
 *  NetID:    yingxian
 *  Precept:  P04
 *
 *  Partner Name: N.A
 *  Partner NetID: N.A
 *  Partner Precept: N.A
 *
 *  Hours to complete assignment (optional): 5
 *
 **************************************************************************** */

Programming Assignment 5: Kd-Trees


/* *****************************************************************************
 *  Describe the Node data type you used to implement the
 *  2d-tree data structure.
 **************************************************************************** */
The Node data type has the following instance variables:
    1. Point2D p (2d pt)
    2. Value val (value of pt)
    3. Node left (left child node)
    4. Node right (right child node)
    5. int size (size of subtree, including current node)
    6. RectHV bbox (bounding box of current node)

/* *****************************************************************************
 *  Describe your method for range search in a kd-tree.
 **************************************************************************** */
    envoke an empty queue (to store possible Piont2D candidate inside rect)
    for current Node x:
        1. NULL: if (x == null) return
        2. NO HOPE: if (!x.bbox.intersect(rect)) return
                    (bounding box and the rect does not intersect)
        3. DO THE WORK: if (rect.contain(x.p)) queue.enqueu(x.p)
        4. RECURSE LEFT & RIGHT:
            range(rect, x.left, queue)
            range(rect, x.right, queue)

/* *****************************************************************************
 *  Describe your method for nearest neighbor search in a kd-tree.
 **************************************************************************** */
    assign champ (nearest neighbor) as root
    for current Node x:
        1. NULL: if (x == null) champ does not change, return current champ
        2. NO HOPE: if (x.bbx.distanceTo(p) > distance(p, champ)) return current champ
            (distance of bbox of x is larger than the current shortest distance with champ)
        3. DO THE WORK: if distance(p, x.p) < distance(p, champ) champ = x.p
        4. RECURSE LEFT & RIGHT:
            if p is on the left/bottom of x , RECURSE left then right
            else RECURSE right then left

/* *****************************************************************************
 *  How many nearest-neighbor calculations can your PointST implementation
 *  perform per second for input1M.txt (1 million points), where the query
 *  points are random points in the unit square?
 *
 *  Show the raw data you used to determine the operations per second.
 *  Use at least 1 second of CPU time for each data point.
 *  (Do not count the time to read the points or to build the 2d-tree.)
 *
 *  Repeat the question but with your KdTreeST implementation.
 *
 *  Fill in the table below, using one digit after the decimal point
 *  for each entry.
 **************************************************************************** */


               # calls to        CPU time         # calls to nearest()
               nearest()         (seconds)        per second
              ------------------------------------------------------
PointST:        no recursion so only 1 call to nearest()

KdTreeST:       469                0.251            1868.53

Note: more calls per second indicates better performance.


/* *****************************************************************************
 *  Did you fill out the mid-semester feedback form?
 *  If not, please do so now: https://forms.gle/GoAXskXWrMiGNrCz7
 **************************************************************************** */


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
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 **************************************************************************** */




/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on  how helpful the class meeting was and on how much you learned
 * from doing the assignment, and whether you enjoyed doing it.
 **************************************************************************** */
