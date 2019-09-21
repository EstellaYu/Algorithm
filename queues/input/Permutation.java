/* *****************************************************************************
 *  Name:    Estella Yu
 *  NetID:   yingxian
 *  Precept: P08
 *
 *  Description:  Client code for reading some words
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        int cnt = 0;

        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            randomizedQueue.enqueue(s);
        }
        if (k > randomizedQueue.size()) {
            throw new java.util.NoSuchElementException("k cannot be lager than queue size");
        }
        for (String str : randomizedQueue) {
            System.out.println(randomizedQueue.dequeue());
            cnt++;
            if (cnt == k) break;
        }

    }
}
