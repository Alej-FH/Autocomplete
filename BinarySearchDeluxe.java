import java.util.Comparator;

public class BinarySearchDeluxe {


    // Returns the index of the first key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null)
            throw new IllegalArgumentException();

        if (a.length == 0) return -1;

        int lo = 0;
        int hi = a.length - 1;

        if (comparator.compare(a[0], key) == 0) return 0;

        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;

            int comp = comparator.compare(key, a[mid]);


            if (comp < 0) hi = mid - 1;
            else if (comp > 0) lo = mid + 1;
            else if (comparator.compare(a[mid - 1], a[mid]) == 0) hi = mid - 1;

            else return mid;
        }

        return -1;


    }

    // Returns the index of the last key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null)
            throw new IllegalArgumentException();

        int lo = 0;
        int hi = a.length - 1;

        if (comparator.compare(a[hi], key) == 0) return hi;

        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            int comp = comparator.compare(key, a[mid]);


            if (comp < 0) hi = mid - 1;
            else if (comp > 0) lo = mid + 1;
            else if (comparator.compare(a[mid + 1], a[mid]) == 0) lo = mid + 1;
            else return mid;

        }
        return -1;


    }

    // unit testing (required)
    public static void main(String[] args) {
        String[] a = { "B", "E", "A", "N", "I", "E" };
        int ind1 = BinarySearchDeluxe.firstIndexOf(a, "I",
                                                   String.CASE_INSENSITIVE_ORDER);
        int ind2 = BinarySearchDeluxe.lastIndexOf(a, "E",
                                                  String.CASE_INSENSITIVE_ORDER);


        System.out.println("First Apperance of " + a[ind1] + " : " + ind1);
        System.out.println("Last Apperance of " + a[ind2] + " : " + ind2);

    }
}
