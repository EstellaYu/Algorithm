/* *****************************************************************************
 *  Name:    Estella Yu
 *  NetID:   yingxian
 *  Precept: P04
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: P00
 *
 *  Description:  Autocomplete
 *  Implement a data type that provides autocomplete functionality for
 *  a given set of string and weights, using Term and BinarySearchDeluxe.
 *
 *  To do so:
 *  1. sort the terms in lexicographic order;
 *  2. use binary search to find the all query strings that start with a given
 *     prefix; and
 *  3. sort the matching terms in descending order by weight.
 *  4. Organize the program by creating an immutable data type Autocomplete with
 *      the following API:
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Autocomplete {
    private Term[] term; // input array of terms

    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        if (terms == null) throw new IllegalArgumentException("Term[] cannot be null");
        this.term = new Term[terms.length];
        for (int i = 0; i < terms.length; i++) {
            if (terms[i] == null) {
                throw new IllegalArgumentException("terms has null input");
            }
            else term[i] = terms[i];
        }
        Merge.sort(term);
    }

    // Returns all terms that start with the given prefix,
    // in descending order of weight.
    public Term[] allMatches(String prefix) {
        if (prefix == null) throw new IllegalArgumentException("input is null");

        int r = prefix.length();
        int lo = BinarySearchDeluxe
                .firstIndexOf(term, new Term(prefix, 0), Term.byPrefixOrder(r));
        int hi = BinarySearchDeluxe
                .lastIndexOf(term, new Term(prefix, 0), Term.byPrefixOrder(r));

        if (lo == -1) return new Term[0];

        Term[] matches = new Term[hi - lo + 1];
        for (int i = lo; i <= hi; i++) {
            matches[i - lo] = term[i];
        }
        Arrays.sort(matches, Term.byReverseWeightOrder());
        return matches;
    }

    // Returns the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        if (prefix == null) throw new IllegalArgumentException("input is null");
        Term[] matches = allMatches(prefix);
        return matches.length;
    }

    // unit testing (required)
    public static void main(String[] args) {
        // Term[] ar = new Term[5];
        // ar[0] = new Term("aaa", 100);
        // ar[1] = new Term("aac", 200);
        // ar[2] = new Term("aab", 300);
        // ar[4] = new Term("aab", 300);
        // ar[3] = new Term("aab", 500);
        //
        // Autocomplete term = new Autocomplete(ar);
        // Term[] matches = term.allMatches("aab");
        // System.out.println("length of matching term: " + term.numberOfMatches("aab"));
        // System.out.println(Arrays.toString(matches));


        // read in the terms from a file
        String filename = args[0];
        In in = new In(filename);
        int n = in.readInt();
        Term[] terms = new Term[n];
        for (int i = 0; i < n; i++) {
            long weight = in.readLong();           // read the next weight
            in.readChar();                         // scan past the tab
            String query = in.readLine();          // read the next query
            terms[i] = new Term(query, weight);    // construct the term
        }

        // read in queries from standard input and print the top k matching terms
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            StdOut.printf("%d matches\n", autocomplete.numberOfMatches(prefix));
            for (int i = 0; i < Math.min(k, results.length); i++)
                StdOut.println(results[i]);
        }
    }
}
