package edu.iastate.cs228.tests;

import edu.iastate.cs228.hw3.AdaptiveList;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


/**
 * @author Andrew Muhs
 */
public class UpdateListsTest {
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
    @Ignore("Update array method is private. 'testUpdateArray' ignored.")
    public void testUpdateArray() {
        String lts = al.toStringLinked();
        String ats = al.toStringArray();
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test)", lts);
        assertEquals("A sequence of items from the most recent array:\n[]", ats);
        assertFalse(al.getarrayUTD());
//        al.updateArray();
        lts = al.toStringLinked();
        ats = al.toStringArray();
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test)", lts);
        assertEquals("A sequence of items from the most recent array:\n[This, is, a, test]", ats);
        assertTrue(al.getarrayUTD());
    }

    @Test
    @Ignore("Update array method is private. 'testUpdateArrayWithEmptyList' ignored.")
    public void testUpdateArrayWithEmptyList() {
        String lts = empty.toStringLinked();
        String ats = empty.toStringArray();
        assertEquals("A sequence of items from the most recent linked list:\n()", lts);
        assertEquals("A sequence of items from the most recent array:\n[]", ats);
        assertFalse(empty.getarrayUTD());
//        empty.updateArray();
        lts = empty.toStringLinked();
        ats = empty.toStringArray();
        assertEquals("A sequence of items from the most recent linked list:\n()", lts);
        assertEquals("A sequence of items from the most recent array:\n[]", ats);
        assertTrue(empty.getarrayUTD());
    }

    @Test
    @Ignore("Update linked method is private. 'testUpdateLinkedRuntimeMessage' ignored.")
    public void testUpdateLinkedRuntimeMessage() {
        try {
//            al.updateLinked();
        } catch (RuntimeException e) {
            assertEquals("arrayUTD is false", e.getMessage());
        }
    }

    @Test
    @Ignore("Update linked method is private. 'testUpdateLinked' ignored.")
    public void testUpdateLinked() {
        String lts = al.toStringLinked();
        String ats = al.toStringArray();
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test)", lts);
        assertEquals("A sequence of items from the most recent array:\n[]", ats);
        assertTrue(al.getlinkedUTD());
        al.get(0);
        al.set(0, "SET");
//        al.updateLinked();
        lts = al.toStringLinked();
        ats = al.toStringArray();
        assertEquals("A sequence of items from the most recent linked list:\n(SET, is, a, test)", lts);
        assertEquals("A sequence of items from the most recent array:\n[SET, is, a, test]", ats);
        assertTrue(al.getlinkedUTD());
    }

    @Test
    @Ignore("Update linked method is private. 'testUpdateLinkedWithEmptyList' ignored.")
    public void testUpdateLinkedWithEmptyList() {
        String lts = empty.toStringLinked();
        String ats = empty.toStringArray();
        assertEquals("A sequence of items from the most recent linked list:\n()", lts);
        assertEquals("A sequence of items from the most recent array:\n[]", ats);
        assertTrue(empty.getlinkedUTD());
        empty.add("ADDED");
        empty.set(0, "SET");
//        empty.updateLinked();
        lts = empty.toStringLinked();
        ats = empty.toStringArray();
        assertEquals("A sequence of items from the most recent linked list:\n(SET)", lts);
        assertEquals("A sequence of items from the most recent array:\n[SET]", ats);
        assertTrue(empty.getlinkedUTD());
    }
}
