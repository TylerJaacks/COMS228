package edu.iastate.cs228.tests;

import edu.iastate.cs228.hw2.SelectionSort;

import java.util.Arrays;

public class SelectionSortTest {
    public static void main(String[] args) {
        String[] arr = {"A", "D", "C", "B", "Q"};

        SelectionSort selectionSort = new SelectionSort();
        selectionSort.sortHelper(arr, null);

        System.out.println(Arrays.toString(arr));
    }
}