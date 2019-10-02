/* *****************************************************************************
 *  Name:    ESTELLA yu
 *  NetID:   YINGXIAN
 *  Precept: P04
 *
 *  Partner Name:    NA
 *  Partner NetID:   NA
 *  Partner Precept: P00
 *
 *  Description:  AUTOCOMPLETE TERM
 *
 **************************************************************************** */

import java.util.Arrays;
import java.util.Comparator;

public class Term implements Comparable<Term> {
    private String q; // string of word, query
    private long w; // weight of string

    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) {
        if (query == null) {
            throw new IllegalArgumentException("Query q cannot be null");
        }
        if (weight < 0) {
            throw new IllegalArgumentException("Weight canot be negative");
        }

        this.q = query;
        this.w = weight;
    }

    // 0. Natual order
    //////////////////////////////////////////////////////////////////////////
    // Compares the two terms in lexicographic order, full query
    public int compareTo(Term that) {
        return this.q.compareTo(that.q);
    }

    // 1. Comparator #1 ==> Reverse Weight
    //////////////////////////////////////////////////////////////////////////
    // Compares the two terms in descending order by weight.
    // a.w < b.w ==> -1
    // a.w > b.w ==> 1
    private static class ReverseWeightOrder implements Comparator<Term> {
        public int compare(Term a, Term b) {
            return -Long.compare(a.w, b.w);
        }
    }

    // return new object with ReverseWeightOrder
    public static Comparator<Term> byReverseWeightOrder() {
        return new ReverseWeightOrder();
    }

    // 2. Comparator #2 ==> Natural order with length r
    //////////////////////////////////////////////////////////////////////////
    // Compares the two terms in lexicographic order,
    // but using only the first r characters of each query.
    public static class PrefixOrder implements Comparator<Term> {
        private int r; // lenth of prefix

        // Initialize a PrefixOrderR with r.
        private PrefixOrder(int r) {
            if (r < 0) throw new IllegalArgumentException("r cannot be negative");
            this.r = r;
        }

        public int compare(Term a, Term b) {
            // since the length is shorter than desired string length
            // ==> less important
            if (a.q.length() < r) return 1;
            if (b.q.length() < r) return -1;
            return a.q.substring(0, r).compareTo(b.q.substring(0, r));
        }
    }

    // Return new PrefixOrderR
    public static Comparator<Term> byPrefixOrder(int r) {
        return new PrefixOrder(r);
    }


    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
        return this.w + "\t" + this.q;
    }

    // unit testing (required)
    public static void main(String[] args) {
        Term[] ar = new Term[5];
        ar[0] = new Term("aa", 100);
        ar[1] = new Term("aac", 200);
        ar[2] = new Term("aab", 300);
        ar[4] = new Term("aab", 300);
        ar[3] = new Term("aab", 500);

        System.out.println("Original Order: ");
        System.out.println(Arrays.toString(ar));

        Arrays.sort(ar);
        System.out.println("\nLexicographic Order: ");
        System.out.println(Arrays.toString(ar));

        Arrays.sort(ar, Term.byReverseWeightOrder());
        System.out.println("\nReversedWeight Order: ");
        System.out.println(Arrays.toString(ar));

        Arrays.sort(ar, Term.byPrefixOrder(3));
        System.out.println("\nLexicographic Order (3): ");
        System.out.println(Arrays.toString(ar));
    }

}
