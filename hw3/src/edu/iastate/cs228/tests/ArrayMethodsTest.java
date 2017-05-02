package edu.iastate.cs228.tests;

import edu.iastate.cs228.hw3.AdaptiveList;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


/**
 * @author Andrew Muhs
 */
public class ArrayMethodsTest {
    public static AdaptiveList<String> al;
    public static AdaptiveList<String> empty;

    @Before
    public void setUp() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("This");
        arrayList.add("is");
        arrayList.add("a");
        arrayList.add("test");
        al = new AdaptiveList<>(arrayList);
        empty = new AdaptiveList<>();
    }

    @Test
    public void testArrayGetWithData() {
        assertEquals("is", al.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testArrayGetThrowsIndexOutOfBoundsException() {
        empty.get(1);
    }

    @Test
    public void testArrayGetExceptionMessage() {
        try {
            empty.get(1);
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index: 1, Size: 0", e.getMessage());
            assertEquals("Index: 1, Size: 0", e.getMessage());
        }
    }

    @Test
    public void testArraySetWithData() {
        assertEquals("is", al.set(1, "IS"));
        assertEquals("IS", al.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testArraySetThrowsIndexOutOfBoundsException() {
        empty.set(1, "IS");
    }

    @Test
    public void testArraySetExceptionMessage() {
        try {
            empty.set(1, "IS");
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index: 1, Size: 0", e.getMessage());
        }
    }

    @Test
    public void testArraySetNullValue() {
        assertEquals("is", al.set(1, null));
        assertEquals(null, al.get(1));
    }
}
