package edu.iastate.cs228.tests;

import edu.iastate.cs228.hw2.MergeSort;

import java.util.Arrays;

public class MergeSortTest {
    public static void main(String[] args) {
        String[] arr = {"Hello", "World", "This", "Is", "MergeSort", "Algorithm"};

        System.out.println(Arrays.toString(arr));

        MergeSort mergeSort = new MergeSort();

        mergeSort.sortHelper(arr, null);

        System.out.println(Arrays.toString(arr));
    }
}
