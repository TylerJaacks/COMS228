package edu.iastate.cs228.tests;

import edu.iastate.cs228.hw4.EntryTree;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Tests {
    /**
     * A simple one add operation.
     */
    @Test
    public void AddTest1() {
        EntryTree<Character, String> entryTree = new EntryTree<>();
        Character[] characters = {'e', 'd', 'i', 't'};
        String value = "change";

        entryTree.add(characters, value);

        assertEquals("null->null e->null d->null i->null t->change ", entryTree.toString());
    }

    /**
     * A simple one add operation.
     */
    @Test
    public void AddTest2() {
        EntryTree<Character, String> entryTree = new EntryTree<>();
        Character[] characters = {'e', 'd', 'i', 't'};
        String value = "revise";

        entryTree.add(characters, value);
        entryTree.showTree();

        assertEquals("null->null e->null d->null i->null t->revise ", entryTree.toString());
    }

    /**
     * A simple one add operation.
     */
    @Test
    public void AddTest3() {
        EntryTree<Character, String> entryTree = new EntryTree<>();
        Character[] characters1 = {'e', 'd', 'i', 't', 'e', 'd'};
        Character[] characters2 = {'e', 'd', 'i', 'c', 't'};
        String value1 = "revised";
        String value2 = "order";

        entryTree.add(characters1, value1);
        entryTree.showTree();

        entryTree.add(characters2, value2);
        entryTree.showTree();

        assertEquals("null->null e->null d->null i->null t->null e->null d->revised c->null t->order ", entryTree.toString());
    }

    /**
     * A simple one remove operation.
     */
    @Test
    public void RemoveTest1() {
        EntryTree<Character, String> entryTree = new EntryTree<>();
        Character[] characters = {'e', 'd', 'i', 't'};
        String value = "change";

        entryTree.add(characters, value);
        entryTree.showTree();

        entryTree.remove(characters);
        entryTree.showTree();

        assertEquals("", "");
    }

    /**
     * A simple one remove operation.
     */
    @Test
    public void RemoveTest2() {
        EntryTree<Character, String> entryTree = new EntryTree<>();
        Character[] characters = {'e', 'd', 'i', 't'};
        String value = "revise";

        entryTree.add(characters, value);
        entryTree.showTree();

        entryTree.remove(characters);
        entryTree.showTree();

        assertEquals("", "");
    }

    /**
     * A simple two remove operation.
     */
    @Test
    public void RemoveTest3() {
        EntryTree<Character, String> entryTree = new EntryTree<>();
        Character[] characters1 = {'e', 'd', 'i', 't', 'e', 'd'};
        Character[] characters2 = {'e', 'd', 'i', 'c', 't'};
        String value1 = "revised";
        String value2 = "order";

        entryTree.add(characters1, value1);
        entryTree.showTree();

        entryTree.add(characters2, value2);
        entryTree.showTree();

        entryTree.remove(characters1);
        entryTree.showTree();

        entryTree.remove(characters2);
        entryTree.showTree();

        assertEquals("", "");
    }

    /**
     * A simple one search operation.
     */
    @Test
    public void SearchTest1() {
        EntryTree<Character, String> entryTree = new EntryTree<>();
        Character[] characters = {'e', 'd', 'i', 't'};
        String value = "change";

        entryTree.add(characters, value);
        entryTree.showTree();

        assertEquals(value, entryTree.search(characters));
    }

    /**
     * A simple one search operation.
     */
    @Test
    public void SearchTest2() {
        EntryTree<Character, String> entryTree = new EntryTree<>();
        Character[] characters = {'e', 'd', 'i', 't'};
        String value = "revise";

        entryTree.add(characters, value);
        entryTree.showTree();

        assertEquals(value, entryTree.search(characters));
    }

    /**
     * A simple one search operation.
     */
    @Test
    public void SearchTest3() {
        EntryTree<Character, String> entryTree = new EntryTree<>();
        Character[] characters1 = {'e', 'd', 'i', 't', 'e', 'd'};
        Character[] characters2 = {'e', 'd', 'i', 'c', 't'};
        String value1 = "revised";
        String value2 = "order";

        entryTree.add(characters1, value1);
        entryTree.showTree();

        entryTree.add(characters2, value2);
        entryTree.showTree();

        assertEquals("order", entryTree.search(characters2));
    }

    /**
     * A simple one prefix operation.
     */
    @Test
    public void PrefixTest1() {
        EntryTree<Character, String> entryTree = new EntryTree<>();
        Character[] characters = {'e', 'd', 'i', 't'};
        String value = "change";

        entryTree.add(characters, value);
        entryTree.showTree();

        assertEquals(characters, entryTree.prefix(characters));
    }

    /**
     * A simple one prefix operation.
     */
    @Test
    public void PrefixTest2() {
        EntryTree<Character, String> entryTree = new EntryTree<>();
        Character[] characters = {'e', 'd', 'i', 't'};
        String value = "revise";

        entryTree.add(characters, value);
        entryTree.showTree();

        assertEquals(characters, entryTree.prefix(characters));
    }

    /**
     * A simple one prefix operation.
     */
    @Test
    public void PrefixTest3() {
        EntryTree<Character, String> entryTree = new EntryTree<>();
        Character[] characters1 = {'e', 'd', 'i', 't', 'e', 'd'};
        Character[] characters2 = {'e', 'd', 'i', 't', 't'};
        String value1 = "revised";
        String value2 = "order";

        entryTree.add(characters1, value1);
        entryTree.showTree();

        entryTree.add(characters2, value2);
        entryTree.showTree();

        assertEquals(characters1, entryTree.search(characters1));
    }
}