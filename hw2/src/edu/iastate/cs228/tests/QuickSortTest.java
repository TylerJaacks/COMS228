package edu.iastate.cs228.tests;

import edu.iastate.cs228.hw2.QuickSort;

import java.util.Arrays;

public class QuickSortTest {
    public static void main(String[] args) {
        String[] arr = {"A", "D", "C", "B", "Q"};

        QuickSort quickSort = new QuickSort();
        quickSort.sortHelper(arr, null);

        System.out.println(Arrays.toString(arr));
    }
}
