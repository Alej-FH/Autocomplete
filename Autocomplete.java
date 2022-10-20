import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class Autocomplete {

    private Term[] term; // array of terms

    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        if (terms == null) throw new IllegalArgumentException();

        this.term = new Term[terms.length];

        // copy
        for (int i = 0; i < terms.length; i++) {
            if (terms[i] == null) throw new IllegalArgumentException();
            this.term[i] = terms[i];
        }

        // sorts Term[]
        Quick.sort(this.term);
    }

    // Returns all terms that start with the given prefix,
    // in descending order of weight.
    public Term[] allMatches(String prefix) {
        if (prefix == null) throw new IllegalArgumentException();


        // comparators for weight and order [short hand]
        Comparator<Term> compRev = Term.byReverseWeightOrder();
        Comparator<Term> compPre = Term.byPrefixOrder(prefix.length());


        // first term in Term[] that preceds prefix
        int firstIndx =
                BinarySearchDeluxe.firstIndexOf(this.term,
                                                new Term(prefix, 0), compPre);

        // the last term in Term[] that follows prefix
        int lastIndx =
                BinarySearchDeluxe.lastIndexOf(this.term,
                                               new Term(prefix, 0), compPre);

        // number of terms related to prefix
        int a = numberOfMatches(prefix);

        // array for terms related to prefix
        Term[] matchT = new Term[a];


        // prefix exists
        if (firstIndx != -1 && lastIndx != -1) {
            // terms from Term[] copied to matchT[]
            for (int i = 0; i < matchT.length; i++) {
                matchT[i] = this.term[firstIndx++];
            }
        }


        // sorts
        Arrays.sort(matchT, compRev);

        return matchT;
    }

    // Returns the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        if (prefix == null) throw new IllegalArgumentException();

        // comparator by prefix order
        Comparator<Term> comp = Term.byPrefixOrder(prefix.length());


        // first occurance of prefix
        int a = BinarySearchDeluxe
                .firstIndexOf(this.term, new Term(prefix, 0),
                              comp);
        // last occurance of prefix
        int b = BinarySearchDeluxe
                .lastIndexOf(this.term, new Term(prefix, 0),
                             comp);

        return 1 + b - a;


    }

    // unit testing (required)
    public static void main(String[] args) {
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
