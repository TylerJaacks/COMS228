package edu.iastate.cs228.tests;

import edu.iastate.cs228.hw3.AdaptiveList;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * @author Andrew Muhs
 */
public class IteratorTest {
    public static AdaptiveList<String> al;
    public static AdaptiveList<String> empty;
    ListIterator<String> iterator;
    ListIterator<String> iteratorPair;
    ListIterator<String> iterator4;

    @Before
    public void setUp() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("This");
        arrayList.add("is");
        arrayList.add("a");
        arrayList.add("test");
        al = new AdaptiveList<>(arrayList);
        empty = new AdaptiveList<>();
        iterator = al.listIterator();
        iteratorPair = al.listIterator(0);
        iterator4 = al.listIterator(al.size());
    }

    @Test
    public void testHasNext() {
        assertTrue(iterator.hasNext());
    }

    @Test
    public void testNext() {
        assertEquals("This", iterator.next());
        assertEquals("is", iterator.next());
        assertEquals("a", iterator.next());
        assertEquals("test", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNextThrowsNoSuchElementException() {
        while (iterator.hasNext())
            iterator.next();
        iterator.next();
    }

    @Test
    public void testNextNoSuchElementExceptionMessage() {
        try {
            while (iterator.hasNext())
                iterator.next();
            iterator.next();
        } catch (NoSuchElementException e) {
            assertEquals("No more elements in the list", e.getMessage());
        }
    }

    @Test
    public void testHasPrevious() {
        assertFalse(iterator.hasPrevious());
    }

    @Test
    public void testHasPreviousAfterNext() {
        iterator.next();
        assertTrue(iterator.hasPrevious());
        assertEquals("This", iterator.previous());
    }

    @Test
    public void testPrevious() {
        assertEquals("test", iterator4.previous());
        assertEquals("a", iterator4.previous());
        assertEquals("is", iterator4.previous());
        assertEquals("This", iterator4.previous());
        assertFalse(iterator4.hasPrevious());
    }

    @Test(expected = NoSuchElementException.class)
    public void testPreviousThrowsNoSuchElementException() {
        while (iterator4.hasPrevious())
            iterator.previous();
        iterator.previous();
    }

    @Test
    public void testPreviousNoSuchElementExceptionMessage() {
        try {
            while (iterator4.hasPrevious())
                iterator.previous();
            iterator.previous();
        } catch (NoSuchElementException e) {
            assertEquals("No more elements in the list", e.getMessage());
        }
    }

    @Test
    public void testConstructors() {
        while (iterator.hasNext() && iteratorPair.hasNext())
            assertEquals(iterator.next(), iteratorPair.next());
    }

    @Test
    public void testNextIndex() {
        int index = 0;
        while (iterator.hasNext()) {
            assertEquals(index, iterator.nextIndex());
            iterator.next();
            index++;
        }
    }


    @Test
    public void testPreviousIndex() {
        int index = -1;
        while (iterator.hasNext()) {
            assertEquals(index, iterator.previousIndex());
            iterator.next();
            index++;
        }
    }

    @Test
    public void testRemove() {
        assertEquals("This", iterator.next());
        iterator.remove();
        assertFalse(iterator.hasPrevious());
        assertEquals("is", iterator.next());
        assertEquals(3, al.size());
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveThrowsIllegalStateException() {
        iterator.remove();
    }


    @Test(expected = IllegalStateException.class)
    public void testRemoveThrowsIllegalStateExceptionAfterFirstRemove() {
        iterator.next();
        iterator.remove();
        iterator.remove();
    }

    @Test
    public void testRemoveIllegalStateExceptionMessage() {
        try {
            iterator.remove();
        } catch (IllegalStateException e) {
            assertEquals("Neither next nor previous have been called", e.getMessage());
        }
    }

    @Test
    public void testAddGoingForward() {
        assertEquals("This", iterator.next());
        iterator.add("TEST");
        assertTrue(iterator.hasPrevious());
        assertEquals("is", iterator.next());
        assertEquals(5, al.size());
    }

    @Test
    public void testAddGoingForwardAtEnd() {
        while (iterator.hasNext()) {
            iterator.next();
        }
        iterator.add("TEST");
        assertEquals(5, al.size());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testAddGoingBackwardAtBeginning() {
        while (iterator4.hasPrevious()) {
            iterator4.previous();
        }
        iterator4.add("TEST");
        assertEquals(5, al.size());
        assertTrue(iterator4.hasPrevious());
    }

    @Test
    public void testAddGoingBackward() {
        assertEquals("test", iterator4.previous());
        iterator4.add("TEST");
        assertTrue(iterator4.hasNext());
        assertEquals("TEST", iterator4.previous());
        assertEquals(5, al.size());
    }

    @Test(expected = IllegalStateException.class)
    public void testAddThrowsIllegalStateException() {
        iterator.add("TEST");
    }


    @Test(expected = IllegalStateException.class)
    public void testAddThrowsIllegalStateExceptionAfterFirstAdd() {
        iterator.next();
        iterator.add("TEST");
        iterator.add("TEST2");
    }

    @Test
    public void testAddIllegalStateExceptionMessage() {
        try {
            iterator.add("TEST");
        } catch (IllegalStateException e) {
            assertEquals("Neither next nor previous have been called", e.getMessage());
        }
    }

    //////////////////////

    @Test
    public void testSetGoingForward() {
        assertEquals("This", iterator.next());
        iterator.set("TEST");
        assertEquals("is", iterator.next());
        assertEquals(4, al.size());
        Object[] arr = al.toArray();
        assertEquals("[TEST, is, a, test]", Arrays.toString(arr));
    }

    @Test
    public void testSetGoingBackward() {
        assertEquals("test", iterator4.previous());
        iterator4.set("TEST");
        assertEquals("a", iterator4.previous());
        assertEquals(4, al.size());
        Object[] arr = al.toArray();
        assertEquals("[This, is, a, TEST]", Arrays.toString(arr));
    }

    @Test(expected = IllegalStateException.class)
    public void testSetThrowsIllegalStateException() {
        iterator.set("TEST");
    }


    @Test
    public void testAddAfterSet() {
        iterator.next();
        iterator.set("TEST");
        iterator.add("TEST2");
        assertEquals(5, al.size());
        Object[] arr = al.toArray();
        assertEquals("[TEST, TEST2, is, a, test]", Arrays.toString(arr));
    }

    @Test
    public void testSetIllegalStateExceptionMessage() {
        try {
            iterator.set("TEST");
        } catch (IllegalStateException e) {
            assertEquals("Neither next nor previous have been called", e.getMessage());
        }
    }
}
