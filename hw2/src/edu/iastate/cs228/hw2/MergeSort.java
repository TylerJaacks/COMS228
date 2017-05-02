package edu.iastate.cs228.hw2;

import java.util.Comparator;

/**
 * @author tylerjaacks
 */

public class MergeSort extends SorterWithStatistics {
    public static void merge(String[] words, String[] left, String[] right, Comparator<String> comp) {
        int a = 0;
        int b = 0;
        for (int i = 0; i < words.length; i++) {
            if (b >= right.length || (a < left.length && comp.compare(left[a], right[b]) < 0)) {
                words[i] = left[a];
                a++;
            } else {
                words[i] = right[b];
                b++;
            }
        }
    }

    //This method will be called by the base class sort() method to
    // actually perform the sort.
    @Override
    public void sortHelper(String[] words, Comparator<String> comp) {
        if (words.length >= 2) {
            String[] left = new String[words.length / 2];
            String[] right = new String[words.length - words.length / 2];

            for (int i = 0; i < left.length; i++) {
                left[i] = words[i];
            }

            for (int i = 0; i < right.length; i++) {
                right[i] = words[i + words.length / 2];
            }

            sortHelper(left, comp);
            sortHelper(right, comp);
            merge(words, left, right, comp);
        }
    }
}
