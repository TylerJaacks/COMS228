package edu.iastate.cs228.tests;

import edu.iastate.cs228.hw3.AdaptiveList;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


/**
 * @author Andrew Muhs
 */
public class ConstructorTest {
    public static AdaptiveList<String> al;

    /**
     * This tests the constructor, size, and isEmpty. The next will do the same;
     */
    @Test
    public void testConstructorWithValidCollection() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("This");
        arr.add("is");
        arr.add("a");
        arr.add("test");
        al = new AdaptiveList<>(arr);
        assertEquals(4, al.size());
        assertFalse(al.isEmpty());
        String lts = al.toStringLinked();
        String ats = al.toStringArray();
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test)", lts);
        assertEquals("A sequence of items from the most recent array:\n[]", ats);
    }

    /**
     * This tests the constructor, size, and isEmpty.
     */
    @Test
    public void testConstructorWithEmptyCollection() {
        ArrayList<String> arr = new ArrayList<>();
        al = new AdaptiveList<>(arr);
        assertEquals(0, al.size());
        assertTrue(al.isEmpty());
        String lts = al.toStringLinked();
        String ats = al.toStringArray();
        assertEquals("A sequence of items from the most recent linked list:\n()", lts);
        assertEquals("A sequence of items from the most recent array:\n[]", ats);
    }

    /**
     * This tests the constructor, size, and isEmpty.
     */
    @Test
    public void testConstructorWithoutCollection() {
        al = new AdaptiveList<>();
        assertEquals(0, al.size());
        assertTrue(al.isEmpty());
        String lts = al.toStringLinked();
        String ats = al.toStringArray();
        assertEquals("A sequence of items from the most recent linked list:\n()", lts);
        assertEquals("A sequence of items from the most recent array:\n[]", ats);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorThrowsException() {
        al = new AdaptiveList<>(null);
    }

    @Test
    public void testConstructorExceptionMessage() {
        try {
            al = new AdaptiveList<>(null);
        } catch (NullPointerException e) {
            assertEquals("Null collection given.", e.getMessage());
        }
    }
}
