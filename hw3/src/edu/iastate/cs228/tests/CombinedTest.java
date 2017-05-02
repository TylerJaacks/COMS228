package edu.iastate.cs228.tests;

import edu.iastate.cs228.hw3.AdaptiveList;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @author Andrew Muhs
 */
public class CombinedTest {
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
        addAllList.add("This2");
        addAllList.add("is2");
        addAllList.add("a2");
        addAllList.add("test2");
        al = new AdaptiveList<>(arrayList);
        empty = new AdaptiveList<>();
    }

    @Test
    public void testListAddOnEmptyList() {
        assertEquals(0, empty.size());
        assertTrue(empty.isEmpty());
        String lts = empty.toStringLinked();
        String ats = empty.toStringArray();
        assertEquals("A sequence of items from the most recent linked list:\n()", lts);
        assertEquals("A sequence of items from the most recent array:\n[]", ats);

        assertTrue(empty.add("TEST"));

        assertEquals(1, empty.size());
        assertFalse(empty.isEmpty());
        lts = empty.toStringLinked();
        ats = empty.toStringArray();
        assertEquals("A sequence of items from the most recent linked list:\n(TEST)", lts);
        assertEquals("A sequence of items from the most recent array:\n[]", ats);

        assertTrue(empty.getlinkedUTD());
        assertFalse(empty.getarrayUTD());

        assertEquals("TEST", empty.get(0));
        assertTrue(empty.getlinkedUTD());
        assertTrue(empty.getarrayUTD());
    }

    @Test
    public void testListAddOnFullList() {
        assertEquals(4, al.size());
        assertFalse(al.isEmpty());
        String lts = al.toStringLinked();
        String ats = al.toStringArray();
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test)", lts);
        assertEquals("A sequence of items from the most recent array:\n[]", ats);

        assertTrue(al.add("TEST"));

        assertEquals(5, al.size());
        assertFalse(al.isEmpty());
        lts = al.toStringLinked();
        ats = al.toStringArray();
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test, TEST)", lts);
        assertEquals("A sequence of items from the most recent array:\n[]", ats);

        assertTrue(al.getlinkedUTD());
        assertFalse(al.getarrayUTD());

        assertEquals("TEST", al.get(4));
        assertTrue(al.getlinkedUTD());
        assertTrue(al.getarrayUTD());

        assertEquals("This", al.set(0, "THIS"));
        assertFalse(al.getlinkedUTD());
        assertTrue(al.getarrayUTD());
    }
}
