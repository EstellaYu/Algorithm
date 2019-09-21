/* *****************************************************************************
 *  Name:    Estella Yu
 *  NetID:   yingxian
 *  Precept: P08
 *
 *  Description:  --- Randomized Queue
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] s; // array to store item
    private int capacity; // array capacity, subject to resize
    private int size; // array current size


    // construct an empty randomized queue
    public RandomizedQueue() {
        capacity = 8; // subject to resize
        s = (Item[]) new Object[capacity];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // resize the array
    private void resize(int cap) {
        Item[] copy = (Item[]) new Object[cap];
        capacity = cap;
        for (int i = 0; i < size; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("input cannot be null");
        }
        if (size == capacity) {
            resize(capacity * 2);
        }
        s[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("queue currently empty");
        }
        if (size == capacity / 4) resize(capacity / 2);
        int ind = StdRandom.uniform(0, size);

        Item forReturn = s[ind];
        s[ind] = s[size - 1]; // swap s[size - 1] with s[ind], then decrement size
        s[size--] = null; // set to null to prevent loidering
        return forReturn;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("queue currently empty");
        }
        return s[StdRandom.uniform(0, size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int[] drawindex; // index array generated to store random index
        private int cnt = 0; // current index to read

        // RandomizedQueue Iterator
        private RandomizedQueueIterator() {
            drawindex = new int[size];
            // initialize an int array with [0, 1, 2, ...]
            for (int i = 0; i < size; i++) {
                drawindex[i] = i;
            }
            // generate random index array
            for (int i = 0; i < size; i++) {
                int ind = StdRandom.uniform(i, size);
                if (i != ind) {
                    int temp = drawindex[i];
                    drawindex[i] = drawindex[ind];
                    drawindex[ind] = temp;
                }
            }
        }

        public boolean hasNext() {
            return cnt < size;
        }

        public void remove() {
            throw new UnsupportedOperationException("not supported");
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("no next item");
            }
            return s[drawindex[cnt++]];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        // initialize ramdomized Queue
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        In in = new In(args[0]);
        // read in all Strings and store in rQ
        while (!in.isEmpty()) {
            String s = in.readString();
            System.out.println("new String: " + s);
            randomizedQueue.enqueue(s);
            for (String str : randomizedQueue) {
                System.out.print(str + " ");
            }
            System.out.println(" size: " + randomizedQueue.size());
        }

        // sample
        System.out.println("Sample: " + randomizedQueue.sample());
        for (String str : randomizedQueue) {
            System.out.print(str + " ");
        }
        System.out.println(" size: " + randomizedQueue.size());

        // remove twice
        System.out.println("Remove: " + randomizedQueue.dequeue());
        for (String str : randomizedQueue) {
            System.out.print(str + " ");
        }
        System.out.println(" size: " + randomizedQueue.size());

        System.out.println("Remove: " + randomizedQueue.dequeue());
        for (String str : randomizedQueue) {
            System.out.print(str + " ");
        }
        System.out.println(" size: " + randomizedQueue.size());
    }

}
