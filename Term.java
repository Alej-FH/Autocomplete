import java.util.Comparator;


public class Term implements Comparable<Term> {
    private String q; // the query
    private long w; // weight of a term


    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) {
        if (query == null || weight < 0) throw new IllegalArgumentException();


        // initializes q and w
        this.q = query;
        this.w = weight;

    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() {
        return new WeightCompare();


    }

    private static class WeightCompare implements Comparator<Term> {
        public int compare(Term t1, Term t2) {
            // compares the w of terms
            if (t1.w == t2.w) return 0;

            if (t1.w > t2.w) return -1;

            return 1;
        }

    }

    // Compares the two terms in lexicographic order,
    // but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r) {
        if (r < 0) throw new IllegalArgumentException();

        return new PrefixCompare(r);

    }

    private static class PrefixCompare implements Comparator<Term> {
        private int r; // r characters of a query

        // initializes to r
        private PrefixCompare(int r) {
            this.r = r;
        }

        // compares a substring of length r of two terms
        public int compare(Term t1, Term t2) {

            // arguements of r length
            String pfx1;
            String pfx2;

            // is length of Term less than r?
            if (t1.q.length() < r) pfx1 = t1.q;
                // updates term prefix to be of size r from term
            else pfx1 = t1.q.substring(0, r);


            // is length of Term less than r?
            if (t2.q.length() < r) pfx2 = t2.q;
            else pfx2 = t2.q.substring(0, r);


            // compares the two strings of r characters.
            return pfx1.compareTo(pfx2);


        }

    }

    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that) {
        return this.q.compareTo(that.q);

    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
        return (w + "\t" + q);

    }

    public static void main(String[] args) {
        // terms
        Term t1 = new Term("Hello", 4);

        Term t2 = new Term("Goodbye", 6);


        System.out.println(Term.byReverseWeightOrder());
        System.out.println(Term.byPrefixOrder(2));

        // string representation
        System.out.println(t1.toString());
        System.out.println(t2.toString());


    }
}
