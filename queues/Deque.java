/* *****************************************************************************
 *  Name:    Estella Yu
 *  NetID:   yingxian
 *  Precept: P04
 *
 *  Description:  deque -- double-ended queue
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.In;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    // first node -- left
    private Node first;
    // last node -- right
    private Node last;
    // size of deque
    private int n = 0;

    private class Node {
        // node item
        private Item item;
        // node next
        private Node next;
        // node prev
        private Node prev;
    }

    // // // construct an empty deque
    // public Deque() {
    //     first = null;
    //     last = null;
    //     n = 0;
    // }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("cannot insert null item");
        }

        Node newnode = new Node();
        newnode.item = item;

        if (isEmpty()) {
            first = newnode;
            last = first;
        }
        else {
            Node oldfirst = first;
            first = newnode;
            first.next = oldfirst;
            oldfirst.prev = first;
        }
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("cannot insert null item");
        }

        Node newnode = new Node();
        newnode.item = item;

        if (isEmpty()) {
            first = newnode;
            last = first;
        }
        else {
            Node oldlast = last;
            last = newnode;
            oldlast.next = last;
            last.prev = oldlast;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("deque empty, cannot remove");
        }

        Node oldfirst = first;
        first = first.next;
        first.prev = null;
        n--;
        return oldfirst.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("deque empty, cannot remove");
        }
        Node oldlast = last;
        last = last.prev;
        last.next = null;
        n--;
        return oldlast.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ItemIterator();
    }

    private class ItemIterator implements Iterator<Item> {
        // current node
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("not supported");
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("no next item");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        In in = new In(args[0]);

        // add item from front
        while (!in.isEmpty()) {
            String s = in.readString();
            System.out.println(s);
            deque.addFirst(s);
            for (String str : deque) {
                System.out.print(str + " ");
            }
            System.out.println(" size: " + deque.size());
        }
        // remove front
        String rf = deque.removeFirst();
        System.out.println(rf);
        for (String str : deque) {
            System.out.print(str + " ");
        }
        System.out.println(" size: " + deque.size());

        // remove last
        String rl = deque.removeLast();
        System.out.println(rl);
        for (String str : deque) {
            System.out.print(str + " ");
        }
        System.out.println(" size: " + deque.size());

        //    add last
        deque.addLast("Estella");
        for (String str : deque) {
            System.out.print(str + " ");
        }
        System.out.println(" size: " + deque.size());
    }

}
