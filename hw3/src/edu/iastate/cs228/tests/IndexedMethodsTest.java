package edu.iastate.cs228.tests;

import edu.iastate.cs228.hw3.AdaptiveList;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * @author Andrew Muhs
 */
public class IndexedMethodsTest {
    public static AdaptiveList<String> al;
    public static AdaptiveList<String> empty;
    public static ArrayList<String> addAllList;

    @Before
    public void setUp() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("This");
        arrayList.add("is");
        arrayList.add("a");
        arrayList.add("test");

        addAllList = new ArrayList<>();
        al = new AdaptiveList<>(arrayList);
        empty = new AdaptiveList<>();

        addAllList.add("This2");
        addAllList.add("is2");
        addAllList.add("a2");
        addAllList.add("test2");
    }

    @Test(expected = NullPointerException.class)
    public void testIndexedAddAllThrowsNullPointerException() {
        al.addAll(0, null);
    }

    @Test
    public void testIndexedAddAllNullPointerExceptionMessage() {
        try {
            al.addAll(0, null);
        } catch (NullPointerException e) {
            assertEquals("Null collection given.", e.getMessage());
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexedAddAllThrowsIndexOutOfBoundException() {
        al.addAll(6, addAllList);
    }

    @Test
    public void testIndexedAddAllIndexOutOfBoundExceptionMessage() {
        try {
            al.addAll(6, addAllList);
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index: 6, Size: 4", e.getMessage());
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexedAddThrowsIndexOutOfBoundException() {
        al.add(6, "TEST");
    }

    @Test
    public void testIndexedAddIndexOutOfBoundExceptionMessage() {
        try {
            al.add(6, "TEST");
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index: 6, Size: 4", e.getMessage());
        }
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexedRemoveThrowsIndexOutOfBoundException() {
        al.remove(6);
    }

    @Test
    public void testIndexedRemoveIndexOutOfBoundExceptionMessage() {
        try {
            al.remove(6);
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index: 6, Size: 4", e.getMessage());
        }
    }

//    @Test
//    public void testIndexedAddAllAtZero() {
//        String lts = al.toStringLinked();
//        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test)", lts);
//        al.addAll(0, addAllList);
//        lts = al.toStringLinked();
//        assertEquals("A sequence of items from the most recent linked list:\n(This2, is2, a2, test2, This, is, a, test)", lts);
//    }

    @Test
    public void testIndexedAddAllAtEnd() {
        String lts = al.toStringLinked();
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test)", " ");
        al.addAll(4, addAllList);
        lts = al.toStringLinked();
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test, This2, is2, a2, test2)", lts);
    }

    @Test
    public void testIndexedAddAllInMiddle() {
        String lts = al.toStringLinked();
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test)", lts);
        al.addAll(2, addAllList);
        lts = al.toStringLinked();
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, This2, is2, a2, test2, a, test)", lts);
    }

    @Test
    public void testIndexedAddAtZero() {
        String lts = al.toStringLinked();
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test)", lts);
        al.add(0, "TEST");
        lts = al.toStringLinked();
        assertEquals("A sequence of items from the most recent linked list:\n(TEST, This, is, a, test)", lts);
    }

    @Test
    public void testIndexedAddAtEnd() {
        String lts = al.toStringLinked();
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test)", lts);
        al.add(4, "TEST");
        lts = al.toStringLinked();
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test, TEST)", lts);
    }

    @Test
    public void testIndexedAddInMiddle() {
        String lts = al.toStringLinked();
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test)", lts);
        al.add(2, "TEST");
        lts = al.toStringLinked();
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, TEST, a, test)", lts);
    }

    @Test
    public void testIndexedRemoveAtZero() {
        String lts = al.toStringLinked();
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test)", lts);
        al.remove(0);
        lts = al.toStringLinked();
        assertEquals("A sequence of items from the most recent linked list:\n(is, a, test)", lts);
    }

    @Test
    public void testIndexedRemoveAtEnd() {
        String lts = al.toStringLinked();
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test)", lts);
        al.remove(3);
        lts = al.toStringLinked();
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a)", lts);
    }

    @Test
    public void testIndexedRemoveInMiddle() {
        String lts = al.toStringLinked();
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test)", lts);
        al.remove(2);
        lts = al.toStringLinked();
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, test)", lts);
    }
}
