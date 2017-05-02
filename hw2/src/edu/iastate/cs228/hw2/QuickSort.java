package edu.iastate.cs228.hw2;

import java.util.Comparator;

/**
 * @author tylerjaacks
 */
public class QuickSort extends SorterWithStatistics {
    private static void quickSortRec(String[] arr, int first, int last, Comparator<String> comp) {
        if (first >= last) {
            return;
        }

        int mid = partition(arr, first, last, comp);

        quickSortRec(arr, first, mid, comp);
        quickSortRec(arr, mid + 1, last, comp);
    }

    private static int partition(String[] arr, int first, int last, Comparator<String> comp) {
        String pivot = arr[first];
        int left = first;
        int right = last;

        while (true) {
            while (comp.compare(arr[left], pivot) < 0) {
                left++;
            }
            while (comp.compare(arr[right], pivot) > 0) {
                right--;
            }
            if (left < right) {
                String t = arr[left];
                arr[left++] = arr[right];
                arr[right--] = t;
            } else {
                break;
            }
        }

        return right;
    }

    //This method will be called by the base class sort() method to
    // actually perform the sort.
    @Override
    public void sortHelper(String[] words, Comparator<String> comp) {
        quickSortRec(words, 0, words.length - 1, comp);
    }
}