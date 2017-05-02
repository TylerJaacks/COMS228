package edu.iastate.cs228.tests;

import edu.iastate.cs228.hw3.AdaptiveList;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


/**
 * @author Andrew Muhs
 */
public class ListMethodsTest {
    public static AdaptiveList<String> al;
    public static AdaptiveList<String> empty;
    public static ArrayList<String> addAllList;
    public static ArrayList<String> containsAllGood;
    public static ArrayList<String> containsAllBad;

    @Before
    public void setUp() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("This");
        arrayList.add("is");
        arrayList.add("a");
        arrayList.add("test");
        containsAllGood = new ArrayList<>();
        containsAllGood.add("This");
        containsAllGood.add("is");
        containsAllGood.add("a");
        containsAllGood.add("test");
        containsAllBad = new ArrayList<>();
        containsAllBad.add("This");
        containsAllBad.add("is");
        containsAllBad.add("b");
        containsAllBad.add("test");
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
    }


    @Test
    public void testListAddWithNullValue() {
        assertEquals(4, al.size());
        assertFalse(al.isEmpty());
        String lts = al.toStringLinked();
        String ats = al.toStringArray();
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test)", lts);
        assertEquals("A sequence of items from the most recent array:\n[]", ats);

        assertTrue(al.add(null));

        assertEquals(5, al.size());
        assertFalse(al.isEmpty());
        lts = al.toStringLinked();
        ats = al.toStringArray();
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test, -)", lts);
        assertEquals("A sequence of items from the most recent array:\n[]", ats);

        assertTrue(al.getlinkedUTD());
        assertFalse(al.getarrayUTD());
    }

    @Test
    public void testListAddAllOnEmptyList() {
        assertEquals(0, empty.size());
        assertTrue(empty.isEmpty());
        String lts = empty.toStringLinked();
        String ats = empty.toStringArray();
        assertEquals("A sequence of items from the most recent linked list:\n()", lts);
        assertEquals("A sequence of items from the most recent array:\n[]", ats);

        assertTrue(empty.addAll(addAllList));

        assertEquals(4, empty.size());
        assertFalse(empty.isEmpty());
        lts = empty.toStringLinked();
        ats = empty.toStringArray();
        assertEquals("A sequence of items from the most recent linked list:\n(This2, is2, a2, test2)", lts);
        assertEquals("A sequence of items from the most recent array:\n[]", ats);

        assertTrue(empty.getlinkedUTD());
        assertFalse(empty.getarrayUTD());
    }

    @Test
    public void testListAddAllOnFullList() {
        assertEquals(4, al.size());
        assertFalse(al.isEmpty());
        String lts = al.toStringLinked();
        String ats = al.toStringArray();
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test)", lts);
        assertEquals("A sequence of items from the most recent array:\n[]", ats);

        assertTrue(al.addAll(addAllList));

        assertEquals(8, al.size());
        assertFalse(al.isEmpty());
        lts = al.toStringLinked();
        ats = al.toStringArray();
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test, This2, is2, a2, test2)", lts);
        assertEquals("A sequence of items from the most recent array:\n[]", ats);

        assertTrue(al.getlinkedUTD());
        assertFalse(al.getarrayUTD());
    }

    @Test(expected = NullPointerException.class)
    public void testListAddAllWithNullValueThrowsNullPointerException() {
        empty.addAll(null);
    }

    @Test
    public void testListAddAllWithNullValueThrowsNullPointerExceptionMessage() {
        try {
            empty.addAll(null);
        } catch (NullPointerException e) {
            assertEquals("Null collection given.", e.getMessage());
        }
    }

    @Test
    public void testListRemoveOnEmptyList() {
        assertEquals(0, empty.size());
        assertTrue(empty.isEmpty());
        String lts = empty.toStringLinked();
        String ats = empty.toStringArray();
        assertEquals("A sequence of items from the most recent linked list:\n()", lts);
        assertEquals("A sequence of items from the most recent array:\n[]", ats);

        assertFalse(empty.remove("TEST"));

        assertEquals(0, empty.size());
        assertTrue(empty.isEmpty());
        lts = empty.toStringLinked();
        ats = empty.toStringArray();
        assertEquals("A sequence of items from the most recent linked list:\n()", lts);
        assertEquals("A sequence of items from the most recent array:\n[]", ats);

        assertTrue(empty.getlinkedUTD());
        assertFalse(empty.getarrayUTD());
    }

    @Test
    public void testListRemoveOnFullList() {
        assertEquals(4, al.size());
        assertFalse(al.isEmpty());
        String lts = al.toStringLinked();
        String ats = al.toStringArray();
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test)", lts);
        assertEquals("A sequence of items from the most recent array:\n[]", ats);

        assertFalse(al.remove("TEST"));

        assertEquals(4, al.size());
        assertFalse(al.isEmpty());
        lts = al.toStringLinked();
        ats = al.toStringArray();
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test)", lts);
        assertEquals("A sequence of items from the most recent array:\n[]", ats);

        assertTrue(al.getlinkedUTD());
        assertFalse(al.getarrayUTD());
    }


    @Test
    public void testListRemoveOnFullList2() {
        assertEquals(4, al.size());
        assertFalse(al.isEmpty());
        String lts = al.toStringLinked();
        String ats = al.toStringArray();
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test)", lts);
        assertEquals("A sequence of items from the most recent array:\n[]", ats);

        assertTrue(al.remove("test"));

        assertEquals(3, al.size());
        assertFalse(al.isEmpty());
        lts = al.toStringLinked();
        ats = al.toStringArray();
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a)", lts);
        assertEquals("A sequence of items from the most recent array:\n[]", ats);

        assertTrue(al.getlinkedUTD());
        assertFalse(al.getarrayUTD());
    }


    @Test
    public void testListContainsEmptyList() {
        assertFalse(empty.contains("This"));
    }

    @Test
    public void testListContainsFullList() {
        assertTrue(al.contains("This"));
    }

    @Test
    public void testListContainsAllEmptyListGoodAL() {
        assertFalse(empty.containsAll(containsAllGood));
    }

    @Test
    public void testListContainsAllEmptyListBadAL() {
        assertFalse(empty.containsAll(containsAllBad));
    }

    @Test
    public void testListContainsAllFullListGoodAL() {
        assertTrue(al.containsAll(containsAllGood));
    }

    @Test
    public void testListContainsAllFullListBaddAL() {
        assertFalse(al.containsAll(containsAllBad));
    }

    @Test
    public void testIndexOfOnEmptyList() {
        assertEquals(-1, empty.indexOf("test"));
    }

    @Test
    public void testIndexOfOnFullListBadObject() {
        assertEquals(-1, al.indexOf("TEST"));
    }

    @Test
    public void testIndexOfOnFullListGoodObject() {
        assertEquals(3, al.indexOf("test"));
    }

    @Test
    public void testIndexOfOnFullListGoodObjectMultipleInstances() {
        al.add("test");
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test, test)", al.toStringLinked());
        assertEquals(3, al.indexOf("test"));
    }

    @Test
    public void testLastIndexOfOnEmptyList() {
        assertEquals(-1, empty.lastIndexOf("test"));
    }

    @Test
    public void testLastIndexOfOnFullListBadObject() {
        assertEquals(-1, al.lastIndexOf("TEST"));
    }

    @Test
    public void testLastIndexOfOnFullListGoodObject() {
        assertEquals(3, al.lastIndexOf("test"));
    }

    @Test
    public void testLastIndexOfOnFullListGoodObjectMultipleInstances() {
        al.add("test");
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test, test)", al.toStringLinked());
        assertEquals(4, al.lastIndexOf("test"));
    }

    @Test
    public void testRemoveAllOnFullList() {
        al.add("test");
        al.add("TEST");
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test, test, TEST)", al.toStringLinked());
        assertTrue(al.removeAll(containsAllGood));
        assertEquals("A sequence of items from the most recent linked list:\n(TEST)", al.toStringLinked());
    }

    @Test
    public void testRemoveAllOnFullListBadCollection() {
        ArrayList<String> s = new ArrayList<>();
        s.add("Nope");
        s.add("Nada");
        s.add("No");

        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test)", al.toStringLinked());
        assertFalse(al.removeAll(s));
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test)", al.toStringLinked());
    }

    @Test
    public void testRemoveAllOnEmptyList() {
        assertEquals("A sequence of items from the most recent linked list:\n()", empty.toStringLinked());
        assertFalse(empty.removeAll(containsAllGood));
        assertEquals("A sequence of items from the most recent linked list:\n()", empty.toStringLinked());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveAllThrowsNullPointerException() {
        al.removeAll(null);
    }

    @Test
    public void testRemoveAllNullPointerExceptionMessage() {
        try {
            al.removeAll(null);
        } catch (NullPointerException e) {
            assertEquals("Null collection given.", e.getMessage());
        }
    }

    @Test
    public void testRetainAllOnFullList() {
        al.add("test");
        al.add("TEST");
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test, test, TEST)", al.toStringLinked());
        assertTrue(al.retainAll(containsAllGood));
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test, test)", al.toStringLinked());
    }

    @Test
    public void testRetainAllOnFullListPartialCollection() {
        ArrayList<String> s = new ArrayList<>();
        s.add("test");


        al.add("test");
        al.add("TEST");
        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test, test, TEST)", al.toStringLinked());
        assertTrue(al.retainAll(s));
        assertEquals("A sequence of items from the most recent linked list:\n(test, test)", al.toStringLinked());
    }

    @Test
    public void testRetainAllOnFullListBadCollection() {
        ArrayList<String> s = new ArrayList<>();
        s.add("Nope");
        s.add("Nada");
        s.add("No");

        assertEquals("A sequence of items from the most recent linked list:\n(This, is, a, test)", al.toStringLinked());
        assertTrue(al.retainAll(s));
        assertEquals("A sequence of items from the most recent linked list:\n()", al.toStringLinked());
    }

    @Test
    public void testRetainAllOnEmptyList() {
        assertEquals("A sequence of items from the most recent linked list:\n()", empty.toStringLinked());
        assertFalse(empty.retainAll(containsAllGood));
        assertEquals("A sequence of items from the most recent linked list:\n()", empty.toStringLinked());
    }

    @Test(expected = NullPointerException.class)
    public void testRetainAllThrowsNullPointerException() {
        al.retainAll(null);
    }

    @Test
    public void testRetainAllNullPointerExceptionMessage() {
        try {
            al.retainAll(null);
        } catch (NullPointerException e) {
            assertEquals("Null collection given.", e.getMessage());
        }
    }
}
