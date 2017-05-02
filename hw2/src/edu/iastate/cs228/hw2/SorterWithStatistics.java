package edu.iastate.cs228.hw2;

import java.util.Comparator;

/**
 * @author tylerjaacks
 */

public abstract class SorterWithStatistics implements Sorter {
    private int wordsSorted;
    private Stopwatch timer;
    private long averageTime;
    private long elapsedTime;
    private int wordListLength;
    private long wordsPerSecond;
    private int totalWordsSorted;
    private long totalElapsedTime;

    /***
     * Default constructor
     */
    public SorterWithStatistics() {
        wordsSorted = 0;
        elapsedTime = 0L;
        wordListLength = 0;
        totalWordsSorted = 0;
        totalElapsedTime = 0L;
        timer = new Stopwatch();
    }

    /***
     * Public interface to sortHelper that keeps track of performance
     * statistics, including counting words sorted and timing sort instances.
     *
     * @param words
     *            input array to be sorted.
     * @param comp
     *            Comparator used to sort the input array.
     */
    public void sort(String[] words, Comparator<String> comp) {
        wordListLength = words.length;

        timer.start();
        sortHelper(words, comp);
        timer.stop();

        totalWordsSorted += wordListLength;
        wordsSorted = wordListLength;

        totalElapsedTime += timer.getElapsedTime();
        elapsedTime = timer.getElapsedTime();

        averageTime = getTotalTimeToSortWords() / getTotalWordsSorted();
        wordsPerSecond = (long) (getTimeToSortWords() / (elapsedTime / (float) 1000000000.0));
    }

    /**
     * Sorts the array words.
     *
     * @param words input array to be sorted.
     * @param comp  Comparator used to sort the input array.
     */
    protected abstract void sortHelper(String[] words, Comparator<String> comp);

    /**
     * Returns number of words sorted in last sort. Throws IllegalStateException
     * if nothing has been sorted.
     *
     * @return number of words sorted in last sort.
     */
    public int getWordsSorted() {
        return wordsSorted;
    }

    /**
     * Returns time the last sort took. Throws IllegalStateException if nothing
     * has been sorted.
     *
     * @return time last sort took.
     */
    public long getTimeToSortWords() {
        return elapsedTime;
    }

    /**
     * Returns total words sorted by this instance.
     *
     * @return total number of words sorted.
     */
    public int getTotalWordsSorted() {
        return totalWordsSorted;
    }

    /**
     * Returns the total amount of time spent sorting by this instance.
     *
     * @return total time spent sorting.
     */
    public long getTotalTimeToSortWords() {
        return totalElapsedTime;
    }

    /**
     * @return a summary of statistics for the last sorting run.
     * <p>
     * See the project description for details about what to include.
     * This method should NOT generate output directly.
     */
    public String getReport() {
        String report = new String();

        report += ("Word List Length: " + wordListLength);
        report += (" Total Words Counted: " + totalWordsSorted);
        report += (" Total Time Elapsed: " + totalElapsedTime);
        report += (" Average Time to Sort: " + averageTime);
        report += (" Words per Second: " + wordsPerSecond);

        return report;
    }
}
