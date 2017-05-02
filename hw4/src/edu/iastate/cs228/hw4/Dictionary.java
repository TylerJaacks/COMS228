package edu.iastate.cs228.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Tyler Jaacks
 *         <p>
 *         An application class
 */
public class Dictionary {
    public static void main(String[] args) {
        File file = new File(args[0]);
        Scanner lineScanner;
        Scanner wordScanner;
        String line;
        String word;
        EntryTree entryTree;
        Object[] keys;
        Object value;

        try {
            lineScanner = new Scanner(file);
            wordScanner = new Scanner(file);
            entryTree = new EntryTree();

            while (lineScanner.hasNextLine()) {
                line = lineScanner.nextLine();

                while (wordScanner.hasNext()) {
                    word = wordScanner.next();

                    if (word.toLowerCase().equals("add")) {
                        word = wordScanner.next();
                        keys = toArray(word);

                        value = wordScanner.next();

                        entryTree.add(keys, value);
                    }

                    if (word.toLowerCase().equals("remove")) {
                        word = wordScanner.next();
                        keys = toArray(word);

                        entryTree.remove(keys);
                    }

                    if (word.toLowerCase().equals("prefix")) {
                        word = wordScanner.next();
                        keys = toArray(word);

                        entryTree.prefix(keys);
                    }

                    if (word.toLowerCase().equals("search")) {
                        word = wordScanner.next();
                        keys = toArray(word);

                        entryTree.search(keys);
                    }

                    if (word.toLowerCase().equals("showtree")) {
                        entryTree.showTree();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found try again.");
        }
    }

    private static Object[] toArray(String string) {
        Object[] objectArr = new Object[string.length()];

        for (int i = 0; i < string.length(); i++) {
            objectArr[i] = string.charAt(i) + "";
        }

        return objectArr;
    }
}
