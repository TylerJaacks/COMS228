package edu.iastate.cs228.tests;

import edu.iastate.cs228.hw3.AdaptiveList;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * @author Andrew Muhs
 */
public class ToArrayTest {
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
    public void testToArrayTypeObject() {
        Object[] arr = al.toArray();
        assertEquals("[This, is, a, test]", Arrays.toString(arr));
        assertTrue(arr instanceof Object[]);
    }

    @Test
    public void testToArrayTypeT() {
        String[] arr = al.toArray(new String[4]);
        assertEquals("[This, is, a, test]", Arrays.toString(arr));
        assertTrue(arr instanceof String[]);
    }

    @Test
    public void testToArrayTypeTWithWrongSize() {
        String[] arr = al.toArray(new String[3]);
        assertEquals("[This, is, a, test]", Arrays.toString(arr));
        assertTrue(arr instanceof String[]);
    }
}
