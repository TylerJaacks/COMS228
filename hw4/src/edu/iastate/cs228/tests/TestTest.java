package edu.iastate.cs228.tests;

import edu.iastate.cs228.hw4.EntryTree;

public class TestTest {
    static EntryTree<Character, String> entryTree;

    public static void main(String[] args) {
        entryTree = new EntryTree<>();
        entryTree.showTree();

        Test1();
        Test2();
        Test3();
        Test4();

    }

    public static void Test1() {
        Character[] characters = {'e', 'd', 'i', 't'};
        String value = "change";

        entryTree.add(characters, value);

        entryTree.showTree();
    }

    public static void Test2() {
        Character[] characters = {'e', 'd', 'i', 't'};
        String value = "revise";

        entryTree.add(characters, value);

        entryTree.showTree();
    }

    public static void Test3() {
        Character[] characters = {'e', 'd', 'i', 't', 'e', 'd'};
        String value = "revised";

        entryTree.add(characters, value);

        entryTree.showTree();
    }

    public static void Test4() {
        Character[] characters = {'e', 'd', 'i', 'c', 't'};
        String value = "order";

        entryTree.add(characters, value);

        entryTree.showTree();
    }

    public static void Test5() {
        Character[] characters = {'e', 'd', 'i', 'c', 't'};
        String value = "order";

        //entryTree.remove(characters);

        entryTree.showTree();
    }
}