package edu.iastate.cs228.hw2;

import java.util.Comparator;

/**
 * @author tylerjaacks
 */

public class SelectionSort extends SorterWithStatistics {
    //This method will be called by the base class sort() method to
    // actually perform the sort.
    @Override
    public void sortHelper(String[] words, Comparator<String> comp) {
        for (int i = 0; i < words.length - 1; i++) {
            int min = i;

            for (int j = i + 1; j < words.length; j++) {
                if (comp.compare(words[min], words[j]) > 0) {
                    min = j;
                }
            }

            String temp = words[min];
            words[min] = words[i];
            words[i] = temp;
        }
    }
}
