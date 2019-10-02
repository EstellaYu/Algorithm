/* *****************************************************************************
 *  Name:    Estella Yu
 *  NetID:   yingixan
 *  Precept: P04
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: P00
 *
 *  Description:  BinarySearchDeluxe
 * When binary searching a sorted array that contains more than one key that
 * equal to the search key, the client may want to know the index of either
 * the first or the last such key.
 *
 **************************************************************************** */

import java.util.Arrays;
import java.util.Comparator;

public class BinarySearchDeluxe {

    // ///////////////////////////////////////////////////////////////////
    // TEXT FUNCTION -- PRINT ELEMENTS
    // /////////////////////////////////////////////////////////////////

    // private static <Key> void printArray(Key[] ar, int lo, int hi) {
    //     for (int i = lo; i <= hi; i++) {
    //         System.out.print(ar[i].toString() + "\t");
    //     }
    //     System.out.println("\n" + lo + " " + hi);
    // }

    /////////////////////////////////////////////////////////////////////
    // 1. First Index
    /////////////////////////////////////////////////////////////////////
    // Returns the index of the first key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key,
                                         Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new IllegalArgumentException("input cannot be null");
        }
        int lo = 0;
        int hi = a.length - 1;
        return firstIndexRecursion(a, key, lo, hi, comparator);
    }

    // recursion function to find first index
    private static <Key> int firstIndexRecursion(Key[] a, Key key, int lo, int hi,
                                                 Comparator<Key> comparator) {
        // printArray(a, lo, hi);
        int mid = lo + (hi - lo) / 2;
        if (lo == hi) {
            if (comparator.compare(a[mid], key) == 0) return mid;
            else return -1;
        }
        else {
            if (comparator.compare(a[mid], key) < 0) {
                return firstIndexRecursion(a, key, mid + 1, hi, comparator);
            }
            else {
                return firstIndexRecursion(a, key, lo, mid, comparator);
            }
        }
    }

    /////////////////////////////////////////////////////////////////////
    // 2. Last Index
    /////////////////////////////////////////////////////////////////////
    // Returns the index of the last key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new IllegalArgumentException("input cannot be null");
        }
        int lo = 0;
        int hi = a.length - 1;
        return lastIndexRecursion(a, key, lo, hi, comparator);
    }

    // recursion function to find first index
    private static <Key> int lastIndexRecursion(Key[] a, Key key, int lo, int hi,
                                                Comparator<Key> comparator) {
        // printArray(a, lo, hi);
        int mid = lo + (hi - lo) / 2;
        if (lo == mid) {
            if (comparator.compare(a[hi], key) == 0) return hi;
            if (comparator.compare(a[mid], key) == 0) return mid;
            return -1;
        }
        else {
            if (comparator.compare(a[mid], key) > 0) {
                return lastIndexRecursion(a, key, lo, mid - 1, comparator);
            }
            else {
                return lastIndexRecursion(a, key, mid, hi, comparator);
            }
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Term[] ar = new Term[5];
        ar[0] = new Term("aa", 100);
        ar[1] = new Term("aac", 200);
        ar[2] = new Term("aab", 300);
        ar[4] = new Term("aab", 300);
        ar[3] = new Term("aab", 500);

        Arrays.sort(ar, Term.byReverseWeightOrder());
        int firstInd = firstIndexOf(ar, new Term("aab", 300),
                                    Term.byReverseWeightOrder());
        Arrays.sort(ar);
        int lastInd = lastIndexOf(ar, new Term("aac", 300), Term::compareTo);

        // Integer[] ar = new Integer[50];
        // for (int i = 0; i < ar.length; i++) {
        //     ar[i] = i;
        // }
        // ar[3] = 2;
        // System.out.println(Arrays.toString(ar));
        // int firstInd = firstIndexOf(ar, 3, Integer::compareTo);
        // int lastInd = lastIndexOf(ar, 2, Integer::compareTo);
        System.out.println("first index: " + firstInd);
        System.out.println("last index: " + lastInd);

    }
}
